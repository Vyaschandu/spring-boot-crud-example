package com.example.RestCrudDemo.resource;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RestCrudDemo.error.CodeError;
import com.example.RestCrudDemo.error.HandleException;
import com.example.RestCrudDemo.model.Customer;
import com.example.RestCrudDemo.service.CustomerService;

@RestController
@RequestMapping(path = "/customer")
public class CustomerResource {

	@Autowired
	private CustomerService customerService;

	@PostMapping(path = "/addCustomer")
	public Customer createCustomer(@RequestBody Customer cus) {
		Customer customer = null;
		try {
			customer = customerService.saveCustomer(cus);
		} catch (Exception e) {
			throw new HandleException(CodeError.SERVOR_ERROR, e);
		}
		return customer;
	}

	@PutMapping(path = "/updateCustomer")
	public Customer updateCustomer(@RequestBody Customer customer) {
		Customer cus = null;
		try {
			cus = customerService.updateCustomer(customer);
		} catch (Exception e) {
			throw new HandleException(CodeError.SERVOR_ERROR, e);
		}
		return cus;
	}

	@GetMapping(path = "/{custId}")
	public Customer getCustomer(@PathVariable int custId) {
		Customer cusOptional = null;

		try {
			cusOptional = customerService.getCustomerByCustID(custId);

		} catch (Exception e) {
			throw new HandleException(CodeError.SERVOR_ERROR, e);
		}
		if (cusOptional == null) {

			throw new HandleException(CodeError.DATA_NOT_FOUND);
		}

		return cusOptional;
	}

	@GetMapping(path = "/findAllCustomers")
	public List<Customer> getCustomersList() {
		List<Customer> cusList = null;
		try {
			cusList = customerService.listCustomers();

		} catch (Exception e) {
			throw new HandleException(CodeError.SERVOR_ERROR, e);
		}
		if (cusList.size() == 0) {

			throw new HandleException(CodeError.DATA_NOT_FOUND);
		}
		return cusList;
	}

	@DeleteMapping(path = "/{custId}")
	public String deleteCustomer(@PathVariable int custId) {
		Customer cusOptional = customerService.getCustomerByCustID(custId);
		if (cusOptional == null) {

			throw new HandleException(CodeError.DATA_NOT_FOUND);
		}

		return customerService.deleteCustomerByCustID(custId);
	}

}
