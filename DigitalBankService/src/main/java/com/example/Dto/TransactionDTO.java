package com.example.Dto;

import java.math.BigDecimal;

public class TransactionDTO {
	
	private BigDecimal amount;
	
	public TransactionDTO() {
		
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransactionDTO(BigDecimal amount) {
		super();
		this.amount = amount;
	}
	
	

}
