package com.example.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Dto.AccountDetail;
import com.example.Dto.TransactionDTO;
import com.example.entity.Account;
import com.example.entity.Transaction;
import com.example.entity.User;
import com.example.Repository.AccountRepository;
import com.example.Repository.UserRepository;


@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public AccountDetail findAccountDetailByPhone(String phone) {
     
		User user=userRepository.findUserByPhone(phone);
		AccountDetail accountDetail=new AccountDetail();
		if(user!=null) {
			List<Account> accounts = user.getAccounts();
		 if(accounts!=null && !accounts.isEmpty()) {
			 Account account=accounts.get(0);
			 accountDetail.setAccountBalance(account.getAccountBalance());
			 accountDetail.setAccountId(account.getAccountId());
			 accountDetail.setAccountNumber(account.getAccountNumber());
			 accountDetail.setFirstName(user.getFirstName());
			 accountDetail.setLastName(user.getLastName());
			 accountDetail.setPhone(user.getPhone());
			 
		 }
		} else {
			throw new RuntimeException("user not exist");
		}
		 return accountDetail;
		
	}

	@Override
	public String transfer(String fromPhone, String toPhone, TransactionDTO transferAmountDTO) {
		
		User fromUser=userRepository.findUserByPhone(fromPhone);
		User toUser=userRepository.findUserByPhone(toPhone);
		Account fromAccount = fromUser.getAccounts().get(0);
		Account toAccount = toUser.getAccounts().get(0);
		BigDecimal transferAmount=transferAmountDTO.getAmount();
		BigDecimal finalAmountFromAccount = fromAccount.getAccountBalance().subtract(transferAmount);
		
		fromAccount.setAccountBalance(finalAmountFromAccount);
		toAccount.setAccountBalance(toAccount.getAccountBalance().add(transferAmount));
		
		Transaction fromAccountTransaction=new Transaction();
		
		fromAccountTransaction.setAccount(fromAccount);
		fromAccountTransaction.setAvailableBalance(fromAccount.getAccountBalance());
		fromAccountTransaction.setTransactionAmount(transferAmount);
		fromAccountTransaction.setTransactionCreated(new Date());
		fromAccountTransaction.setTransactionStatus("success");
		fromAccountTransaction.setTransactionType("debit");
		fromAccount.getTransactions().add(fromAccountTransaction);
		accountRepository.save(fromAccount);
		
		
		
		Transaction toAccountTransaction=new Transaction();
		toAccountTransaction.setAccount(toAccount);
		toAccountTransaction.setAvailableBalance(toAccount.getAccountBalance());
		toAccountTransaction.setTransactionAmount(transferAmount);
		toAccountTransaction.setTransactionCreated(new Date());
		toAccountTransaction.setTransactionStatus("success");
		toAccountTransaction.setTransactionType("credit");
		toAccount.getTransactions().add(toAccountTransaction);
		accountRepository.save(toAccount);
		
		return "success";
	}

}

