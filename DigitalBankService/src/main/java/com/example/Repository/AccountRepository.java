package com.example.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

}
