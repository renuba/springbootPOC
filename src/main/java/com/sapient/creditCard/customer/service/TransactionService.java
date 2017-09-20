package com.sapient.creditCard.customer.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sapient.creditCard.customer.domain.TransactionEntity;
import com.sapient.creditCard.customer.repository.ITransactionRepository;



@Component
public class TransactionService {


	@Autowired
	private ITransactionRepository repo;
	
	

		public List<TransactionEntity> getTransactionByCustId(Integer customerId) {
			return repo.findByCustIdOrderByTransValue(customerId);
			
		}


		public TransactionEntity setTransaction(TransactionEntity trans) {
			TransactionEntity savedApplication = repo.save(trans);
			return savedApplication;
		}

}
