package com.crm.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crm.api.models.UserRequest;

@Repository
public interface UserRequestRepository extends CrudRepository<UserRequest, Long>{

}
