package com.crm.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.crm.api.models.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long>{

}
