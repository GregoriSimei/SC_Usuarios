package com.crm.api.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.crm.api.models.Movement;

public interface MovementRepository extends CrudRepository<Movement, Long>{

	List<Movement> findByDepositId(long id);
}
