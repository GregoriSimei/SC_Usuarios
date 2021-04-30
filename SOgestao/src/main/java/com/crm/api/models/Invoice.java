package com.crm.api.models;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "Invoices")
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@Column(name = "key")
	private String key;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "serial")
	private String serial;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "createAt")
	private String createAt;
	
	@Column(name = "value")
	private double value;
	
	@OneToMany
	@JoinColumn(name = "movs_id")
	@Fetch(FetchMode.JOIN)
	private List<Movement> Movements;
	
	@OneToOne
	@JoinColumn(name = "branch_id")
	private Branch Branch;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public List<Movement> getMovements() {
		return Movements;
	}

	public void setMovements(List<Movement> movements) {
		Movements = movements;
	}

	public Branch getBranch() {
		return Branch;
	}

	public void setBranch(Branch branch) {
		Branch = branch;
	}

	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
