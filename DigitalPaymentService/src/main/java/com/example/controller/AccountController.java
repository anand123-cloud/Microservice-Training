package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Dto.AmountTransferDTO;
import com.example.Dto.AccountDetail;
import com.example.entity.Transaction;
import com.example.service.AccountService;

@RestController
@RequestMapping("/upi")
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@PostMapping("/accounts/{phone}")
	public AccountDetail registerUPIAccount(@PathVariable String phone) {
		return accountService.registerUPIAccount(phone);
		
	}
	
	@PutMapping("/accounts/{fromPhone}/{toPhone}")
	public String transferAmount(@RequestBody AmountTransferDTO amountDTo,
			                      @PathVariable String fromPhone,
			                      @PathVariable String toPhone) {
		
		return accountService.transferAmount(amountDTo, fromPhone, toPhone);
				
	}
	
	@GetMapping("/accounts/{phone}")
	public List<Transaction> getTransactions(@PathVariable String phone){
		return accountService.getTransactions(phone);
	}
}
