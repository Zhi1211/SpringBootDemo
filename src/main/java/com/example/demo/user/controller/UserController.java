package com.example.demo.user.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService service;

	@PostMapping(value = "/submit")
	@ResponseBody
	public String submitRegister(User user) {
		String errorMsg = service.userRegister(user);
		return errorMsg;
	}

	@PostMapping(value = "login")
	@ResponseBody
	public String login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		User user = service.userLogin(account, password);
		if (user != null) {
				session.setAttribute("LoginOK", user);
				return "登入成功！";
			} else {
				return "帳號或密碼錯誤";
			} 
	}

	@GetMapping("/users")
	public String users(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		User user;
		if (session.getAttribute("LoginOK") == null) {
			return "index";
		}
		user = (User) session.getAttribute("LoginOK");
		List<User> userList = service.listAllUsers(user);
		model.addAttribute("user", user);
		model.addAttribute("users", userList);
		return "userList";
	}

}
