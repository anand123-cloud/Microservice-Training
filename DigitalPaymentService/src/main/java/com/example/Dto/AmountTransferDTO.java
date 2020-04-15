package com.example.Dto;

import java.math.BigDecimal;

public class AmountTransferDTO {
	
	private BigDecimal amount;
	
	public AmountTransferDTO() {
		
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public AmountTransferDTO(BigDecimal amount) {
		super();
		this.amount = amount;
	}
	
	

}
