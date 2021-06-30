package com.crm.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.crm.api.models.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long>{

}
