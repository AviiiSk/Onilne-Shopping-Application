package com.shopify.services;


	
	
	import java.util.List;

	import com.eshopify.entity.Order;
import com.eshopify.entity.Product;
import com.shopify.model.OrderModel;

	public interface OrderService {

		int placeOrder(String token, Order order);

		static List<OrderModel> checkuserOrderHistory(int userId, String token) {
			// TODO Auto-generated method stub
			return null;
		}

		
	}
	
	
	


