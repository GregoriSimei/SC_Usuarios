package com.crm.api.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;

@DiscriminatorValue(value = "PRODUCT")
public class Product extends Item{
	
	@Column(name = "variable")
	private Boolean variable;

	public Boolean getVariable() {
		return variable;
	}

	public void setVariable(Boolean variable) {
		this.variable = variable;
	}

}
