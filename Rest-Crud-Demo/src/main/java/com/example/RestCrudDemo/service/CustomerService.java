package com.example.RestCrudDemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.RestCrudDemo.model.Customer;

@Component
public interface CustomerService {

	//Optional<Customer> getCustomerByCustID(int custId);
	
	Customer getCustomerByCustID(int custId);

	List<Customer> listCustomers();

	Customer saveCustomer(Customer cs);

	Customer updateCustomer(Customer cs);

	String deleteCustomerByCustID(int custId);

}
