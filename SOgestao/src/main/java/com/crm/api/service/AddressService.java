package com.crm.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.crm.api.models.Address;
import com.crm.api.repositories.AddressRepository;

@Service
@Configurable
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;

	public boolean checkFields(Address address) {
		return address.getCity() != null &&
			   address.getDistrict() != null &&
			   address.getNumber() != null &&
			   address.getStreet() != null &&
			   address.getZipcode() != null;
	}

	public Address save(Address address) {
		address = this.addressRepository.save(address);
		return address;
	}

}
