package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Dto.AccountDetail;
import com.example.Dto.AmountTransferDTO;
import com.example.Dto.AccountDetail;
import com.example.entity.Account;
import com.example.entity.Transaction;
import com.example.feignclient.DigitalBankServiceClient;
import com.example.repository.FundTransferRepository;
import com.example.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private FundTransferRepository fundTransferRepository;
	
	@Autowired
	private DigitalBankServiceClient digitalbankServiceClient;

	@Override
	public AccountDetail registerUPIAccount(String phone) {
    //fetch account details from banking service
		ResponseEntity<AccountDetail> accountDetailByPhone = digitalbankServiceClient.getAccountDetailByPhone(phone);
		AccountDetail account=accountDetailByPhone.getBody();
		Account account=new Account();
		AccountDetail accountDetail=new AccountDetail();
		if(account!=null) {
			account.setAccountBalance(account.getAccountBalance());
			account.setBankAccountNumber(account.getBankAccountNumber());
			account.setFirstName(account.getFirstName());
			account.setLastName(account.getLastName());
			account.setPhone(account.getPhone());
			
			Account savedAccount = accountRepository.save(account);
			
			accountDetail.setAccountBalance(savedAccount.getAccountBalance());
			accountDetail.setBankAccountNumber(savedAccount.getBankAccountNumber());
			accountDetail.setFirstName(savedAccount.getFirstName());
			accountDetail.setLastName(savedAccount.getLastName());
			accountDetail.setPhone(savedAccount.getPhone());
			accountDetail.setAccountId(savedAccount.getAccountId());

		} else {
			throw new RuntimeException("account does not exist for this phone");
		}
		
		return accountDetail;
	}

	@Override
	public String transferAmount(AmountTransferDTO amountDTo, String fromPhone, String toPhone) {
		String result = digitalbankServiceClient.transfer(amountDTo, fromPhone, toPhone);
		//success
		if(result!=null && result.equals("success")) {
			AccountDetail fromAccountDetail = digitalbankServiceClient.getAccountDetailByPhone(fromPhone).getBody();
			
			FundTransferRepository debitTransaction = createAccountTransactionfromAccountDetail(fromAccountDetail,
					amountDTo,"debit");
			
			FundTransferRepository.save(debitTransaction);
			
			AccountDetail toAccountDetail = digitalbankServiceClient.getAccountDetailByPhone(toPhone).getBody();

			FundTransfer creditTransaction =createAccountTransactionfromAccountDetail(toAccountDetail, amountDTo, "credit");
			fundTransferRepository.save(creditTransaction);
		}
		
		
		return "succcess";
	}

	
	private Transaction createAccountTransactionfromAccountDetail(
			AccountDetail accountDetail,
			AmountTransferDTO amountDTo,
			String transactionType) {
		Transaction accountTransaction=new Transaction();
		accountTransaction.setAvailableBalance(accountDetail.getAccountBalance());
		accountTransaction.setTransactionAmount(amountDTo.getAmount());
		accountTransaction.setTransactionCreated(new Date());
		accountTransaction.setTransactionStatus("success");
		accountTransaction.setTransactionType(transactionType);
		accountTransaction.setUpiAccountId(accountDetail.getAccountId());
		return accountTransaction;
	}

	@Override
	public List<Transaction> getTransactions(String phone) {
		Long accountId = accountRepository.findUPIAccountByPhone(phone).getAccountId();
		
		Pageable paging = PageRequest.of(0, 5);

		return fundTransferRepository.findByAccountId(accountId, paging).getContent();
	    
	}


}

