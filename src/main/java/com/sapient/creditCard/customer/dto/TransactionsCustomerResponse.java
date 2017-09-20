package com.sapient.creditCard.customer.dto;

import java.util.ArrayList;
import java.util.List;

import com.sapient.creditCard.customer.domain.TransactionEntity;

public class TransactionsCustomerResponse {

	private String merchantName;
	
	private List<TransactionEntity> tranactions ;

	public TransactionsCustomerResponse(String merchantName) {
		super();
		this.merchantName = merchantName;
		this.tranactions = new ArrayList<TransactionEntity>();
	}



	public String getMerchantName() {
		return merchantName;
	}



	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}



	public List<TransactionEntity> getTranactions() {
		return tranactions;
	}

	public void setTranactions(List<TransactionEntity> tranactions) {
		this.tranactions = tranactions;
	}



	
	
	
	
	
}
