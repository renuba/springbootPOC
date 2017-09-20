package com.sapient.creditCard.customer.repository;

import org.springframework.data.repository.CrudRepository;

import com.sapient.creditCard.customer.domain.CustomerEntity;

public interface  ICustomerRepository extends CrudRepository<CustomerEntity, Integer>{
	
}
