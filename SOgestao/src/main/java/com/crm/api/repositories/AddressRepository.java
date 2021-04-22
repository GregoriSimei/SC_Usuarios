package com.crm.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crm.api.models.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long>{

}
