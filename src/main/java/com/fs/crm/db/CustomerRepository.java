package com.fs.crm.db;

import org.springframework.data.repository.CrudRepository;

import com.fs.crm.bom.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
