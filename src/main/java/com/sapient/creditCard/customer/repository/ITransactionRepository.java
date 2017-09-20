package com.sapient.creditCard.customer.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.CrudRepository;

import com.sapient.creditCard.customer.domain.TransactionEntity;

public interface ITransactionRepository extends CrudRepository<TransactionEntity, Integer>{

	//List<TransactionEntity> findByCustIdOrderByTransValue(Integer customerId);

	List<TransactionEntity>  findByCustIdOrderByTransValue(Integer customerId);

	
}
