package com.example.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Dto.AccountDetail;
import com.example.Dto.AmountTransferDTO;


@FeignClient(name="banking-service",path = "/bankingservice")
public interface DigitalBankServiceClient {
	
	@GetMapping("/accounts/{phone}")
	public ResponseEntity<AccountDetail> getAccountDetailByPhone(@PathVariable String phone) throws RuntimeException;
	
	
	@PutMapping("/accounts/{fromPhone}/{toPhone}")
	public String transfer(@RequestBody AmountTransferDTO amountDTo,@PathVariable String fromPhone, @PathVariable String toPhone);
}
