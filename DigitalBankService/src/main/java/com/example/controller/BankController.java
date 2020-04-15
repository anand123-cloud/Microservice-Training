package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Dto.AccountDetail;
import com.example.Dto.TransactionDTO;
import com.example.Service.BankService;

@RestController
@RequestMapping("/bankservice")
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	@GetMapping("/accounts/{phone}")
	public ResponseEntity<AccountDetail> getAccountDetailByPhone(@PathVariable String phone) throws RuntimeException {
		AccountDetail accountDetail=bankService.findAccountDetailByPhone(phone);
		if(accountDetail!=null) {
			return new ResponseEntity<AccountDetail>(accountDetail, HttpStatus.OK);
		} else {
			throw new RuntimeException();
		}
	}
	
	@PutMapping("/accounts/{fromPhone}/{toPhone}")
	public String transfer(@RequestBody TransactionDTO amountDTo,@PathVariable String fromPhone, @PathVariable String toPhone) {
		return bankService.transfer(fromPhone, toPhone, amountDTo);
	}
	

}
