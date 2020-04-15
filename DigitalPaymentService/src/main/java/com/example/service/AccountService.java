package com.example.service;

import java.util.List;

import com.example.Dto.AmountTransferDTO;
import com.example.Dto.AccountDetail;
import com.example.entity.Transaction;

public interface AccountService {

	AccountDetail registerUPIAccount(String phone);
	String transferAmount(AmountTransferDTO amountDTo,String fromPhone,String toPhone); 
	List<Transaction> getTransactions(String phone);

}
