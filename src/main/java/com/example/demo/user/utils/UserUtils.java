package com.example.demo.user.utils;

import java.text.SimpleDateFormat;

public class UserUtils {

	public UserUtils() {}
	
	public static  String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,})";
	public static boolean checkDate(String str) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		try {
			sd.setLenient(false);
			sd.parse(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
