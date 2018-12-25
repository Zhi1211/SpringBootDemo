package com.example.demo.user.service;

import java.util.List;

import com.example.demo.user.model.User;

public interface UserService {

	String userRegister(User user);

	User userLogin(String account, String password);

	boolean dupAccount(String account);

	List<User> listAllUsers(User user);

}