package com.crm.api.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "Sales")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@OneToMany
	@JoinColumn(name = "sl_id")
	@Fetch(FetchMode.JOIN)
	private List<ItemSale> items;
	
	@Column(name = "status")
	private String status;
	
	@OneToOne
	@JoinColumn(name = "pay_id")
	private Payment payment;
	
	@OneToOne
	@JoinColumn(name = "ss_id")
	private Session session;
	
	@Column(name = "total")
	private double total;
	
	@Column(name = "start")
	private Date start;
	
	@Column(name = "update")
	private Date update;
	
	@ManyToOne
	@JoinColumn(name = "cl_id")
	private Person person;
	
	@ManyToOne
	@JoinColumn(name = "us_id")
	private User user;

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<ItemSale> setItem(ItemSale item){
		this.items.add(item);
		return this.items;
	}

	public List<ItemSale> getItems() {
		return items;
	}

	public void setItems(List<ItemSale> items) {
		this.items = items;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
