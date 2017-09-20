package com.sapient.creditCard.customer.web;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.creditCard.customer.domain.CustomerEntity;
import com.sapient.creditCard.customer.domain.TransactionEntity;
import com.sapient.creditCard.customer.dto.CustomerResponse;
import com.sapient.creditCard.customer.dto.ResponseVO;
import com.sapient.creditCard.customer.dto.TransactionResponse;
import com.sapient.creditCard.customer.dto.TransactionsCustomerResponse;
import com.sapient.creditCard.customer.service.CustomerService;
import com.sapient.creditCard.customer.service.TransactionService;


@RestController
public class TransactionController {
	
	
	@Autowired
	private TransactionService transservice;
	
	@Autowired
	private CustomerService custservice;
	
	@Autowired
	private ModelMapper mapper;

	private static final String SUCCESS = "SUCCESS";

	private static final String FAILURE = "FAILURE";

	@RequestMapping(method = RequestMethod.POST, value="/creditCard/tranaction", produces = "application/json", consumes="application/json")
	public ResponseVO createTranaction(@RequestBody @Valid TransactionEntity trans){	
		ResponseVO responseVO = new ResponseVO();
		//get customer
				CustomerEntity cust= custservice.getCustomerById(trans.getCustId());
				if(cust!=null){					
		TransactionEntity tranactionEntity = transservice.setTransaction(trans);
		if(tranactionEntity != null){
			TransactionResponse responseDTO = mapper.map(tranactionEntity, TransactionResponse.class);
			if(responseDTO != null){
				responseVO.setData(responseDTO);
				responseVO.setMessage("tranaction is added successfully");
				responseVO.setStatus(SUCCESS);
			}else{
				responseVO.setMessage("tranaction could not be addaed");
				responseVO.setStatus(FAILURE);
			}
		}else{
			responseVO.setMessage("tranaction could not be addaed.");
			responseVO.setStatus(FAILURE);
		}
				}
				else{
					responseVO.setMessage("Customer ID is not valid.");
					responseVO.setStatus(FAILURE);
				}	
				
		return responseVO;
	}

	@RequestMapping(method = RequestMethod.GET, value="/creditCard/tranaction/{customerId}", produces="application/json")
	public ResponseVO getTranactions(@PathVariable Integer customerId){
		
		ResponseVO responseVO = new ResponseVO();
		//get customer
		CustomerEntity cust= custservice.getCustomerById(customerId);
		if(cust!=null){	
		List<TransactionEntity> transactionEntity = transservice.getTransactionByCustId(customerId);
		if(!transactionEntity.isEmpty()){
		
			 Map<String, List<TransactionEntity>> transactionMap=transactionEntity.stream().collect(Collectors.groupingBy(TransactionEntity::getMerchantName));
			 List<TransactionsCustomerResponse> responseDTO = new ArrayList<TransactionsCustomerResponse>();
			 transactionMap.forEach((key, value) -> {
				 TransactionsCustomerResponse p= new TransactionsCustomerResponse(key);
				 p.setTranactions(value);
				 responseDTO.add(p);});	 
				//	 transactionMap.forEach(c-> responseDTO.add(mapper.map(c, TransactionsCustomerResponse.class)));
		//	TransactionsCustomerResponse responseDTO = mapper.map(transactionMap, TransactionsCustomerResponse.class);.
			if(!responseDTO.isEmpty()){
				responseVO.setData(responseDTO);
			responseVO.setMessage("transactions found successfully");
			responseVO.setStatus(SUCCESS);
			}else{
				responseVO.setMessage("Transactions details not found for customer ID.");
				responseVO.setStatus(FAILURE);
			}
		}else{
			responseVO.setMessage("Transactions details not found for customer ID.");
			responseVO.setStatus(FAILURE);
		}
		}
		else{
			responseVO.setMessage( "Customer ID "+customerId+"  does not exist.");
			responseVO.setStatus(FAILURE);
		}
		return responseVO;
		
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value="/creditCard/customer", produces = "application/json", consumes="application/json")

	public ResponseVO createCustomer(@RequestBody @Valid CustomerEntity application){
				CustomerEntity customerEntity = custservice.createCustomer(application);
		ResponseVO responseVO = new ResponseVO();
		if(customerEntity != null){
			CustomerResponse responseDTO = mapper.map(customerEntity, CustomerResponse.class);
			if(responseDTO != null){
				responseVO.setData(responseDTO);
				responseVO.setMessage("customer is added successfully");
				responseVO.setStatus(SUCCESS);
			}else{
				responseVO.setMessage("User application could not be created");
				responseVO.setStatus(FAILURE);
			}
		}else{
			responseVO.setMessage("User application could not be created");
			responseVO.setStatus(FAILURE);
		}
		return responseVO;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/creditCard/customer/{customerId}", produces="application/json")
	public ResponseVO getCustomer(@PathVariable Integer customerId){

		CustomerEntity customerEntity = custservice.getCustomerById(customerId);

		ResponseVO responseVO = new ResponseVO();
		if(customerEntity != null){
			responseVO.setData(customerEntity);
			responseVO.setMessage("customer details found successfully");
			responseVO.setStatus(SUCCESS);
		}else{
			responseVO.setMessage("customer details not found or the input is incorrect");
			responseVO.setStatus(FAILURE);
		}
		return responseVO;
	}
}
