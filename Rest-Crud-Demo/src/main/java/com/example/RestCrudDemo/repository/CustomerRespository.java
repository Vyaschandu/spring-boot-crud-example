package com.example.RestCrudDemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.RestCrudDemo.model.Customer;

@Repository
public interface CustomerRespository extends MongoRepository<Customer, Integer> {
	
	public Customer findByCustomerId(int id) ;

}
