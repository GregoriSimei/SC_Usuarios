package com.crm.api.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "promissorynote")
public class PromissoryNote {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "total")
	private double total;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "current")
	private double current;
	
	@Column(name = "fees")
	private double fees;
	
	@Column(name = "assessment")
	private double assessment;
	
	@Column(name = "duedate")
	private Date dueDate;
	
	@Column(name = "creation")
	private Date creation;
	
	@Column(name = "modification")
	private Date modification;
	
	@OneToMany
	@JoinColumn(name = "pn_id")
	private List<NoteMovement> movements;
	
	@OneToOne
	@JoinColumn(name = "in_id")
	private Invoice invoice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getCurrent() {
		return current;
	}

	public void setCurrent(double current) {
		this.current = current;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public double getAssessment() {
		return assessment;
	}

	public void setAssessment(double assessment) {
		this.assessment = assessment;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public List<NoteMovement> getMovements() {
		return movements;
	}

	public void setMovements(List<NoteMovement> movements) {
		this.movements = movements;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date create) {
		this.creation = create;
	}

	public Date getModification() {
		return modification;
	}

	public void setModification(Date modification) {
		this.modification = modification;
	}
}
