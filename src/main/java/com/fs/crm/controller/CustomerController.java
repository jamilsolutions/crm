package com.fs.crm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fs.crm.bom.Customer;
import com.fs.crm.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping("/save")
	public Customer save(@Validated @RequestBody Customer customer) throws MethodArgumentNotValidException, Exception {
		if ( customer == null ) {
			throw new MethodArgumentNotValidException(null, null);
		}
		customer = this.customerService.save(customer);

		return customer;
	}

	@GetMapping("/read/{id}")
	public Customer read(@PathVariable Long id) {
		Optional<Customer> customer = this.customerService.find(id);
		if (customer.isPresent()) {
			return customer.get();
		}
		return null;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
		String message = this.customerService.delete(id);
		if ( message == null) {
		return ResponseEntity.status(HttpStatus.OK)
		        .body("{ \"message\": \"Customer "+ id +" successfully deleted\" }");
		} else {
			return ResponseEntity.status(HttpStatus.OK)
			        .body("{ \"message\": \"" + message + "\" }");
		}
	}

	@GetMapping("/list")
	public List<Customer> list() {
		List<Customer> customers = this.customerService.list();
		return customers; 
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Map<String, String> handleValidationExceptions(Exception ex) {
		Map<String, String> errors = new HashMap<String, String>();
		FieldError error = new FieldError("Exception", "Exception", ex.getMessage());
		String fieldName = ((FieldError) error).getField();
		String errorMessage = error.getDefaultMessage();
		errors.put(fieldName, errorMessage);
	    return errors;
	}

}
