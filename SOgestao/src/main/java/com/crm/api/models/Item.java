package com.crm.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@Column(name = "inId")
	private String inId;
	
	@Column(name = "outId")
	private String outId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "avgprice")
	private double avgPrice;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getInId() {
		return inId;
	}
	
	public void setInId(String inId) {
		this.inId = inId;
	}
	
	public String getOutId() {
		return outId;
	}
	
	public void setOutId(String outId) {
		this.outId = outId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getAvgPrice() {
		return avgPrice;
	}
	
	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}
}
