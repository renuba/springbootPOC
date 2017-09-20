package com.sapient.creditCard.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sapient.creditCard.customer.domain.CustomerEntity;
import com.sapient.creditCard.customer.repository.ICustomerRepository;



@Component
public class CustomerService {

	@Autowired
	private ICustomerRepository repo;
	
	public CustomerEntity createCustomer(CustomerEntity customer) {
		CustomerEntity savedApplication = repo.save(customer);
		return savedApplication;
	}
		
		public CustomerEntity getCustomerById(Integer custId){
			return repo.findOne(custId);
		}

}
