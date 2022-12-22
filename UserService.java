package com.shopify.services;


import java.util.List;

import com.eshopify.entity.Product;
import com.eshopify.entity.User;
import com.eshopify.entity.UserLogin;

public interface UserService {

	public boolean createUserRegistration(User user);

	public static String loginUser(UserLogin loginUser) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<User> getAllUsers(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	public int makeUserAsAdmin(String token, int userId);

	public static Iterable<Product> getAllUsers1(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
