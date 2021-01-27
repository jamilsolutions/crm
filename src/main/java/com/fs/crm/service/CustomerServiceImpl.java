package com.fs.crm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fs.crm.bom.Customer;
import com.fs.crm.db.CustomerRepository;
import com.fs.crm.util.IDUtil;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Value("${crm.mq.queue-name}")
    private String queueName;
	
	@Autowired
	private JmsOperations jmsOperations;
	
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
			
			String json = convertToJson(customer);
			
			
			jmsOperations.convertAndSend(queueName, json);			
		}
		return customer;
	}

	private String convertToJson(Customer customer) throws JsonProcessingException {
		//Object mapper instance
		ObjectMapper mapper = new ObjectMapper();
		//Convert POJO to JSON
		return mapper.writeValueAsString(customer);
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
