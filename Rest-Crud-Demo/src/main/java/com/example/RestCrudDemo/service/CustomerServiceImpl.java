package com.example.RestCrudDemo.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RestCrudDemo.model.Customer;
import com.example.RestCrudDemo.repository.CustomerRespository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRespository customerRepository;
 
	@Override
	public Customer getCustomerByCustID(int custId){
		return customerRepository.findByCustomerId(custId);
	}

	@Override
	public List<Customer> listCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer saveCustomer(Customer cs) {
		return customerRepository.save(cs);
	}

	@Override
	public Customer updateCustomer(Customer cs) {
		return customerRepository.save(cs);

	}

	@Override
	public String deleteCustomerByCustID(int custId) {
		customerRepository.deleteById(custId);
		return "customer deleted with id : " + custId;
	}

}
