package com.example.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	
	User findUserByPhone(String phone);
}
