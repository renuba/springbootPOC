package com.sapient.creditCard.customer.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

	
	@SuppressWarnings("serial")
	@Entity
	public class TransactionEntity implements Serializable{

		@Id
	    @GeneratedValue
	    
	private Integer transactionId;
		
		@Column
		@NotBlank(message="merchant name can not be blank")
	private String merchantName;
		
		@Column
		@NotNull(message="Please enter customer ID")
		
	private Integer custId;
		
		public String getMerchantName() {
			return merchantName;
		}


		public void setMerchantName(String merchantName) {
			this.merchantName = merchantName;
		}

		@Column
		@NotNull(message="tranaction value can not be null")
	private Double transValue;
		
		
		@Column
		@NotBlank
	private String transType;
		
		@Column
		@Past
		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date transdate;


		public Date getTransdate() {
			return transdate;
		}


		public void setTransdate(Date transdate) {
			this.transdate = transdate;
		}


		public Integer getTransactionId() {
			return transactionId;
		}


		public void setTransactionId(Integer transactionId) {
			this.transactionId = transactionId;
		}




		public Integer getCustId() {
			return custId;
		}


		public void setCustId(Integer custId) {
			this.custId = custId;
		}

		public Double getTransValue() {
			return transValue;
		}


		public void setTransValue(Double transValue) {
			this.transValue = transValue;
		}


		public String getTransType() {
			return transType;
		} 

		public void setTransType(String transType) {
			this.transType = transType;
		}
	
	

}
