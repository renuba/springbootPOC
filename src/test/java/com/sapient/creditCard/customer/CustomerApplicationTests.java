package com.sapient.creditCard.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sapient.creditCard.customer.domain.CustomerEntity;
import com.sapient.creditCard.customer.domain.TransactionEntity;
import com.sapient.creditCard.customer.dto.ResponseVO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerApplicationTests {

	@Test
	public void contextLoads() {
	}	
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void createCustomer() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		CustomerEntity custVO = mapper.readValue(new ClassPathResource("jsons/CustomerCreateJson").getFile(),CustomerEntity.class);
		// Testing POST ::/creditCard/customer
		ResponseEntity<ResponseVO> response = restTemplate.postForEntity("/creditCard/customer", custVO, ResponseVO.class);
		ResponseVO responseVO = response.getBody();
		assertEquals("SUCCESS", responseVO.getStatus());
		assertNotNull(responseVO.getMessage());
		
}
	
/*	@Test
	public void createTransPass() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		TransactionEntity transVO = mapper.readValue(new ClassPathResource("jsons/TransactionPass").getFile(),TransactionEntity.class);
		// Testing POST ::/creditCard/customer
		ResponseEntity<ResponseVO> response = restTemplate.postForEntity("/creditCard/tranaction", transVO, ResponseVO.class);
		ResponseVO responseVO = response.getBody();
		assertEquals("SUCCESS", responseVO.getStatus());
		assertNotNull(responseVO.getMessage());
		assertNotNull(responseVO.getData());
		
}
	
	
	public void createTransFail() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		TransactionEntity transVO = mapper.readValue(new ClassPathResource("jsons/TransactionFail").getFile(),TransactionEntity.class);
		// Testing POST ::/creditCard/customer
		ResponseEntity<ResponseVO> response = restTemplate.postForEntity("/creditCard/tranaction", transVO, ResponseVO.class);
		ResponseVO responseVO = response.getBody();
		assertEquals("FAILURE", responseVO.getStatus());
		assertNotNull(responseVO.getMessage());
		//assertNull(responseVO.getData());
		
}*/
	

}
