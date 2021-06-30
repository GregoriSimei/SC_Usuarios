package com.crm.api.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Deposit;
import com.crm.api.models.Document;
import com.crm.api.models.Invoice;
import com.crm.api.models.Item;
import com.crm.api.models.ItemSale;
import com.crm.api.models.Movement;
import com.crm.api.models.Person;
import com.crm.api.models.PromissoryNote;
import com.crm.api.models.Sale;
import com.crm.api.models.Session;
import com.crm.api.models.User;
import com.crm.api.service.DepositService;
import com.crm.api.service.InvoiceService;
import com.crm.api.service.ItemSaleService;
import com.crm.api.service.ItemService;
import com.crm.api.service.NoteService;
import com.crm.api.service.PersonService;
import com.crm.api.service.SaleService;
import com.crm.api.service.SessionService;
import com.crm.api.service.UserService;

@Service
@Configurable
public class SaleBusiness {
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemSaleService itemSaleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private NoteBusiness noteBusiness;
	
	@Autowired
	private NoteService noteService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private DepositService depositService;
	
	@Autowired
	private MovementBusiness movementBusiness;
	
	private static String IN_PROGRESS = "In Progress";
	private static String PAYMENT_PENDING = "Payment Pending";
	private static String CANCELED = "Canceled";
	private static String PAID_OUT = "Paid Out";
	private static String DELIVERY_PENDING = "Delivery Pending";
	private static String DELIVERED = "Delivered";
	private static String DEVOLUTION = "Devolution";
	
	public Sale save(Sale sale) {
		sale = sale.getId() != null ?
				this.update(sale):
				this.create(sale);
		
		sale = sale != null?
			this.saveAll(sale):
			null;
		
		return sale;
	}

	private Sale saveAll(Sale sale) {
		sale = this.saveItems(sale);
		sale.setModification(new Date());
		sale = this.saleService.save(sale);
		return sale;
	}

	private Sale saveItems(Sale sale) {
		List<ItemSale> items = sale.getItems();
		items = this.itemSaleService.saveAll(items);
		sale.setItems(items);
		return sale;
	}

	private Sale create(Sale sale) {
		boolean checkFields = this.checkFields(sale);
		boolean checkItems = checkFields ? this.checkitems(sale): false;
		boolean checkClient = checkFields ? this.checkClient(sale) : false;
		boolean checkUser = checkFields ? this.checkUser(sale) : false;
		
		sale = checkItems && checkClient && checkUser ?
				this.generateSale(sale):
				null;
		
		return sale;
	}

	private boolean checkUser(Sale sale) {
		User user = sale.getUser();
		user = this.getUser(user);
		return user != null;
	}

	private User getUser(User user) {
		Long id = user.getId();
		return this.userService.findUserById(id);
	}

	private boolean checkClient(Sale sale) {
		Person client = sale.getClient();
		client = this.getClient(client);
		return client != null;
	}

	private Person getClient(Person client) {
		Long id = client.getId();
		return this.personService.findById(id);
	}

	private boolean checkitems(Sale sale) {
		List<ItemSale> items = sale.getItems();
		
		for(ItemSale item: items) {
			Long id = item.getItem().getId();
			Item itemDB = this.itemService.findById(id);
			
			if(itemDB == null) {
				return false;
			}
		}
		
		return true;
	}

	private Sale generateSale(Sale sale) {
		sale = this.setItems(sale);
		Session session = this.generateSession();
		sale.setSession(session);
		sale.setStatus(IN_PROGRESS);
		sale.setStart(new Date());
		sale = this.calculateTotal(sale);
		
		return sale;
	}

	private Sale setItems(Sale sale) {
		List<ItemSale> items = sale.getItems();
		
		for(ItemSale item: items) {
			Long id = item.getItem().getId();
			item.setItem(
					this.itemService.findById(id)
				);
		}
		
		sale.setItems(items);
		
		return sale;
	}

	private Sale calculateTotal(Sale sale) {
		List<ItemSale> items = sale.getItems();
		
		double value = 0.0;
		
		for(ItemSale item : items) {
			value += item.getItem().getPrice() * item.getQtd();
		}
		
		sale.setTotal(value);
		
		return sale;
	}

	private Session generateSession() {
		return this.sessionService.generate();
	}

	private boolean checkFields(Sale sale) {
		boolean checkSaleFields = this.checkSaleFields(sale);
		boolean checkItemsFields = this.checkItemsFields(sale);
		
		return checkSaleFields &&
			   checkItemsFields;
	}

	private boolean checkItemsFields(Sale sale) {
		boolean validation = true;
		
		List<ItemSale> items = sale.getItems();
		
		for(ItemSale item : items) {
			Item itemTest = item.getItem();
			validation = itemTest != null;
			validation = validation ?
					itemTest.getId() != null:
					false;
			
			if(!validation) {
				break;
			}
		}
		
		return validation;
	}

	private boolean checkSaleFields(Sale sale) {
		return this.saleService.checkFields(sale);
	}
	
	private boolean checkSession(Sale sale) {
		Session session = sale.getSession();
		session = this.getSession(session);
		return session.getStatus().contentEquals("Active") ||
			   session.getStatus().contentEquals("Finished");
	}	

	private Session getSession(Session session) {
		Long id = session.getId();
		return this.sessionService.getById(id);
	}

	private Sale getSale(Sale sale) {
		Long id = sale.getId();
		return this.saleService.findById(id);
	}

	private Sale update(Sale sale) {
		boolean checkFields = this.checkFields(sale);
		boolean checkStatus = this.checkStatus(sale);
		boolean checkItems = checkFields ? this.checkitems(sale): false;
		boolean checkSaleExist = this.getSale(sale) != null ? true:false;
		boolean checkUpdateStatus = this.checkStatusToUpdate(sale);
		boolean checkSesion = this.checkSession(sale);
		boolean checkClient = checkFields ? this.checkClient(sale) : false;
		boolean checkUser = checkFields ? this.checkUser(sale) : false;
		
		sale = checkFields &&
			   checkStatus &&
			   checkItems  &&
			   checkUpdateStatus &&
			   checkSaleExist &&
			   checkSesion &&
			   checkClient &&
			   checkUser ?
					   this.updateManager(sale):
					   null;
			   
		sale = sale != null ? 
				this.saveAll(sale):
				sale;
			   
		return sale;
	}

	private Sale updateManager(Sale sale) {
		
		String status = sale.getStatus();
		
		if(status.contentEquals(IN_PROGRESS)) {
			sale = this.updateInProgress(sale);
		}
		else if(status.contentEquals(PAYMENT_PENDING)) {
			sale = this.updatePaymentPending(sale);
		}
		else if(status.contentEquals(CANCELED)) {
			sale = this.updateCanceled(sale);
		}
		else if(status.contentEquals(PAID_OUT)){
			sale = this.updatePaidOut(sale);
		}
		else if(status.contentEquals(DELIVERY_PENDING)){
			sale = this.updateDeliveryPending(sale);
		}
		else if(status.contentEquals(DELIVERED)){
			sale = this.updateDelivered(sale);
		}
		else {
			sale = null;
		}
		
		sale = sale != null ? this.prepareToUpdate(sale) : null;
		
		return sale;
	}

	private Sale prepareToUpdate(Sale sale) {
		sale = this.setItems(sale);
		sale = this.calculateTotal(sale);
		return sale;
	}

	private Sale updateInProgress(Sale sale) {
		return sale;
	}

	private Sale updatePaymentPending(Sale sale) {
		boolean checkNote = this.checkNote(sale);
		PromissoryNote note = sale.getNote();
		note.setTotal(sale.getTotal());
		note = this.noteBusiness.create(note);
		sale.setNote(note);
		sale = note != null ? 
				this.generateInvoice(sale):
				null;
		sale = sale != null ?
				this.finishSession(sale):
				null;
		
		return sale;
	}

	private Sale finishSession(Sale sale) {
		Session session = sale.getSession();
		session = this.sessionService.finish(session);
		sale.setSession(session);
		return sale;
	}

	private Sale generateInvoice(Sale sale) {
		Invoice invoice = new Invoice();
		invoice.setClient(sale.getClient());
		invoice.setItems(sale.getItems());
		invoice.setValue(sale.getTotal());
		invoice.setCompany("Coisas & Coisas");
		invoice = this.invoiceService.save(invoice);
		
		PromissoryNote note = sale.getNote();
		note.setInvoice(invoice);

		sale.setNote(note);

		return sale;
	}

	private boolean checkNote(Sale sale) {
		PromissoryNote note = sale.getNote();
		return note != null;
	}

	private Sale updateCanceled(Sale sale) {
		sale = this.getSale(sale);
		sale.setStatus(CANCELED);
		sale = this.cancelNote(sale);
		sale = this.closeSession(sale);
		return sale;
	}
	

	private Sale closeSession(Sale sale) {
		Session session = sale.getSession();
		session = session != null ?
				this.sessionService.cancel(session):
				null;
		sale.setSession(session);
		return sale;
	}

	private Sale cancelNote(Sale sale) {
		PromissoryNote note = sale.getNote();
		note = note != null? 
				this.noteService.cancel(note):
				null;
		
		sale.setNote(note);
		return sale;
	}

	private Sale updatePaidOut(Sale sale) {
		Session session = sale.getSession();
		session = session != null ?
				this.sessionService.cancel(session):
				null;
		sale.setSession(session);
		return sale;
	}
	
	
	private Sale updateDeliveryPending(Sale sale) {
		boolean checkPayment = this.checkPayment(sale);
		if(checkPayment) {
			this.generateMovements(sale);
			sale = this.getSale(sale);
			sale.setStatus(DELIVERY_PENDING);
		}
		else {
			sale = null;
		}
		
		return sale;
	}
	

	private boolean checkPayment(Sale sale) {
		sale = this.getSale(sale);
		PromissoryNote note = sale.getNote();
		String status = note != null ?
				note.getStatus():
				"Empty";
		return status.contentEquals("Open");
	}

	private void generateMovements(Sale sale) {
		List<ItemSale> items = sale.getItems();
		
		for(ItemSale item : items) {
			Deposit deposit = this.getDeposit(item);
			Movement movement = this.createMovement(item.getItem(), deposit, sale.getUser(), item.getQtd());
			this.movementBusiness.createMovement(movement);
		}
	}

	private Movement createMovement(Item item, Deposit deposit, User user, int qtd) {
		Movement movement = new Movement();
		movement.setDeposit(deposit);
		movement.setItem(item);
		movement.setUser(user);
		movement.setQtd(qtd);
		movement.setType("Output");
		movement.setSubType("Bill of Sale");
		movement.setDescription("Vendido pelo usuario " + user.getUsername());
		Document doc = this.generateDocumentSale(movement);
		movement.setDoc(doc);
		
		return movement;
	}

	private Document generateDocumentSale(Movement movement) {
		Document document = new Document();
		document.setTitle(movement.getSubType());
		document.setContent("Venda de " + movement.getQtd() + " unidade(s) de " + movement.getItem().getName());
		return document;
	}

	private Deposit getDeposit(ItemSale itemSale) {
		List<Deposit> deposits = this.depositService.findAll();
		boolean validation = false;
		Item itemToTest = itemSale.getItem();
		Deposit depositFound = null;
		
		for(Deposit deposit : deposits) {
			List<Item> items = deposit.getItems();
			
			for(Item item: items) {
				validation = item.getId() == itemToTest.getId();
				if(validation) {
					depositFound = deposit;
					break;
				}
			}
			if(validation) {
				break;
			}
		}
		
		return depositFound;
	}

	private Sale updateDelivered(Sale sale) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean checkStatus(Sale sale) {
		return sale.getStatus().contentEquals(IN_PROGRESS) ||
			   sale.getStatus().contentEquals(PAYMENT_PENDING) ||
			   sale.getStatus().contentEquals(CANCELED) || 
			   sale.getStatus().contentEquals(DELIVERY_PENDING) ||
			   sale.getStatus().contentEquals(DELIVERED) ||
			   sale.getStatus().contentEquals(DEVOLUTION);
	}
	
	private boolean checkStatusToUpdate(Sale sale) {
		String newStatus = sale.getStatus();
		
		Sale oldSale = this.getSale(sale);
		String oldStatus = oldSale.getStatus();
		
		boolean validation = false;
		
		if(oldStatus.contentEquals(IN_PROGRESS)) {
			validation = newStatus.contentEquals(PAYMENT_PENDING) ||
						 newStatus.contentEquals(CANCELED) ||
						 newStatus.contentEquals(IN_PROGRESS);
		}
		else if(oldStatus.contentEquals(PAYMENT_PENDING)) {
			validation = newStatus.contentEquals(CANCELED) ||
					 	 newStatus.contentEquals(PAID_OUT);
		}
		else if(oldStatus.contentEquals(CANCELED)) {
			validation = false;
		}
		else if(oldStatus.contentEquals(PAID_OUT)){
			validation = newStatus.contentEquals(CANCELED) ||
					 	 newStatus.contentEquals(DELIVERY_PENDING);
		}
		else if(oldStatus.contentEquals(DELIVERY_PENDING)){
			validation = newStatus.contentEquals(CANCELED) ||
				 	 	 newStatus.contentEquals(DELIVERED);
		}
		else if(oldStatus.contentEquals(DELIVERED)){
			validation = newStatus.contentEquals(CANCELED) ||
				 	 	 newStatus.contentEquals(DEVOLUTION);
		}
		
		return validation;
	}
}
