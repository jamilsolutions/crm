package com.fs.crm.service;

import java.util.List;
import java.util.Optional;

import com.fs.crm.bom.Customer;

public interface CustomerService {
	
    public Customer save( Customer cliente) throws Exception;
	
	public String delete(Long id)  throws Exception;
	
    public Optional<Customer> find(Long id);
	
	public List<Customer> list();
}
