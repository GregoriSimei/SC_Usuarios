package com.crm.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crm.api.models.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>{

}
