package com.example.demo.user.service.impl;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.user.model.User;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.user.service.UserService;
import com.example.demo.user.utils.UserUtils;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository repository;
	
	public UserServiceImpl() {}

	@Override
	public String userRegister(User user) {
		String errorMsg = "";
		String account = user.getAccount();
		String password = user.getPassword();
		String gender = user.getGender();
		String birthday = user.getBirthday();
		if (account == null || account.trim().length() == 0) {
			errorMsg += "請輸入帳號\n";
		} else {
			if (dupAccount(account)) {
				errorMsg += "帳號已被註冊\n";
			}
		}

		if (password == null || password.trim().length() == 0) {
			errorMsg += "請輸入密碼\n";
		} else {
			Pattern pattern = Pattern.compile(UserUtils.PASSWORD_PATTERN);
			Matcher matcher = pattern.matcher(password);
			if (!matcher.matches()) {
				errorMsg += "密碼至少含有一個大寫字母、小寫字母、數字，且長度不能小於八個字元\n";
			}
		}

		if (gender == null || gender.trim().length() == 0) {
			errorMsg += "請選擇性別\n";
		} else {
			if (!(gender.equals("male")) && !(gender.equals("female"))) {
				errorMsg += "請輸入正確性別\n";
			}
		}

		if (birthday == null || birthday.trim().length() == 0) {
			errorMsg += "請輸入生日\n";
		} else {
			if (!UserUtils.checkDate(birthday)) {
				errorMsg += "日期格式錯誤";
			}
		}
		if (errorMsg.equals("")) {
			repository.save(new User(account, password, gender, birthday));
			return "註冊成功！";
		} else {
			return "請檢查註冊內容：\n" + errorMsg;
		}
	}

	@Override
	public User userLogin(String account, String password) {
		User user = repository.findByAccount(account);
		if(user != null) {
			if(user.password.equals(password)) {		
				return user;
			}else {
				return null;
			}
		}else {
			return user;
		}
	}

	@Override
	public boolean dupAccount(String account) {
		boolean isDup = false;
		if (repository.findByAccount(account) != null) {
			isDup = true;
		}
		return isDup;
	}
	
	@Override
	public List<User> listAllUsers(User user) {
		List<User> users = repository.findAll();
		Collections.sort(users, (user1, user2) -> {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date comparedDay = sdf.parse(user.birthday);
					Date date1 = sdf.parse(user1.birthday);
					Date date2 = sdf.parse(user2.birthday);
					Long differ1 = (long) Math.abs(comparedDay.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);
					Long differ2 = (long) Math.abs(comparedDay.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
					if (differ1.compareTo(differ2) == 0) {
						if (user.gender.equals(user1.gender)) {
							return -1;
						} else {
							return 1;
						}
					} else {
						return differ1.compareTo(differ2);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return 0;
		});
		return users;
	}

}
