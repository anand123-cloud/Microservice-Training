package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.entity.Transaction;

public interface FundTransferRepository extends PagingAndSortingRepository<Transaction, Long> {
	
	List<Transaction> findUPIAccountTransactionByAccountId(Long accountId);
	
	Page<Transaction> findByAccountId(Long upiAccountId, Pageable pageable);

}
