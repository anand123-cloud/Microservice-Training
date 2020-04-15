package com.example.Service;

import com.example.Dto.AccountDetail;
import com.example.Dto.TransactionDTO;

public interface BankService {

	AccountDetail findAccountDetailByPhone(String phone);
	
	String transfer(String fromPhone, String toPhone,TransactionDTO transferAmountDTO);
}
