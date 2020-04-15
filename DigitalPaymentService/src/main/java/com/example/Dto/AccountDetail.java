package com.example.Dto;

import java.math.BigDecimal;

public class AccountDetail {
     
	private Long AccountId;
	
    private String firstName;
    
    private String lastName;

    private String email;
    
    private String phone;
    
    private BigDecimal accountBalance;
    
    private String bankAccountNumber;
    
    public AccountDetail() {
    	
    }

	public Long getAccountId() {
		return AccountId;
	}

	public void setAccountId(Long AccountId) {
		this.AccountId = AccountId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
    
    

}

