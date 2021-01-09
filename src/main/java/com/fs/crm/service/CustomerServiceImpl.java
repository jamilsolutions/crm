package com.fs.crm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fs.crm.bom.Customer;
import com.fs.crm.db.CustomerRepository;
import com.fs.crm.util.IDUtil;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer save(Customer customer) throws Exception {
		if ( IDUtil.isValid(customer.getId()) == false) {
			throw new Exception("O ID provided is invalid.");
		}
		boolean existsById = customerRepository.existsById(customer.getId());
		if ( existsById ) {
			customer = customerRepository.save(customer); // Update
		} else {
			customer = customerRepository.save(customer); // Insert
		}
		return customer;
	}

	@Override
	public String delete(Long id) throws Exception {
		if ( id != null ) {
			Optional<Customer> customer = customerRepository.findById(id);
			if ( customer.isPresent() ) {
			    customerRepository.delete(customer.get());
			} else {
				throw new Exception("Customer not found by id");
			}
		}
		return null;
	}

	@Override
	public Optional<Customer> find(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		return customer;
	}

	@Override
	public List<Customer> list() {
	    Iterable<Customer> customers = customerRepository.findAll();
	    return mapToList(customers);
	}

	private List<Customer> mapToList(Iterable<Customer> customers) {
	    List<Customer> listOfCustomers = new ArrayList<>();
	    for (Customer student : customers) {
	    	listOfCustomers.add(student);
	    }
	    return listOfCustomers;
	}
}
