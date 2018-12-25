package com.example.demo.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.user.model.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	public User findByAccount(String account);	

}
