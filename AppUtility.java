package com.shopify.utility;


import java.util.List;
import java.util.Scanner;

import com.eshopify.connectiondb.ConnectionDB;
import com.eshopify.entity.cart;
import com.eshopify.entity.Order;
import com.eshopify.entity.Product;
import com.eshopify.entity.UserLogin;
import com.shopify.model.CartModel;
import com.shopify.message.properties.MessageProperties;
import com.shopify.services.CartService;
import com.shopify.services.CartServiceImp;
import com.shopify.services.OrderService;
import com.shopify.services.OrderServiceImp;
import com.shopify.services.ProductService;
import com.shopify.services.ProductServiceImp;
import com.shopify.services.UserService;
import com.shopify.services.UserServiceImp;

public class AppUtility {
	
	public static void allProducts() {
		ProductServiceImp prouctService = new ProductServiceImp(ConnectionDB.connectDb());
		List<Product> getProductList = prouctService.fetchAllProduct();
		System.out.println(
				"________________________________________________________________________________________________________________________");
		System.out.println("productId : Product Name   Product Description    Proudct price ");
		getProductList.forEach(it -> {
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------");
			System.out.println(it.getId() + ",     " + it.getProductName() + ",  " + it.getProductDecription() + ", "
					+ it.getProductPrice());
		});
		System.out.println(
				"__________________________________________________________________________________________________________________________");
	}
	
	public static void getCartDetailsOfUser(String token) {
		System.out.println(MessageProperties.CART_DETAILS.getMessage());
		CartService cartService = new CartServiceImp(ConnectionDB.connectDb());
		List<CartModel> cartDetails = cartService.getCartDetails(token);
		System.out.println(
				"____________________________________________________________________________________________________________________________________");
		System.out.println("ProductId : Product Name   : ProductPrice: TotalQty of product : TotalPrice");
		cartDetails.forEach(it -> {
			System.out.println(it.getProductId() + "         : " + it.getProductName() + "  : " + it.getProductPrice()
					+ "         : " + it.getProductQty() + "                : " + it.getTotalPrice());
		});
		System.out.println(
				"_____________________________________________________________________________________________________________________________________");

	}
	
	public static void operation(String token, Scanner sc) {
		CartService cartService = new CartServiceImp(ConnectionDB.connectDb());
		OrderService orderService = new OrderServiceImp(ConnectionDB.connectDb());
		System.out.println("***************************************Proudct Details**************************************");
		AppUtility.allProducts();
		System.out.println("To Add Product in Cart, Please Select Product Id");
		int id = sc.nextInt();
		System.out.println("Please Select Product QTY");
		int oty = sc.nextInt();
		cart cart = new cart();
		cart.setProductId(id);
		cart.setProductQty(oty);
		int addToCart = cartService.addToCart(token, cart);
		if (addToCart == 1) {
			System.out.println("*********************************Your Cart Detils****************************************");
			AppUtility.getCartDetailsOfUser(token);
			Order order = new Order();
			System.out.println("To Place Order please Select Id of Product");
			int productId = sc.nextInt();
			System.out.println("Please Select Qty of product");
			int qty = sc.nextInt();
			order.setProductId(productId);
			order.setProductOty(qty);
			int placeOrder = orderService.placeOrder(token, order);
			if (placeOrder == 1) {
				System.out.println("************************Your Cart Datails After Order is placed**********************");
				AppUtility.getCartDetailsOfUser(token);
			} else {
				System.out.println(MessageProperties.INTERNAL_ERROER.getMessage());
			}

		} else {
			System.out.println(MessageProperties.INTERNAL_ERROER.getMessage());
		}

	}
	
	public static String login(String token, Scanner sc) {
		@SuppressWarnings("unused")
		UserService userService = new UserServiceImp(ConnectionDB.connectDb());
		System.out.println("Please Login first");
		System.out.print("Please Enter Email: ");
		String userName = sc.next();
		System.out.println("Please Enter Your Password");
		String pass = sc.next();
		UserLogin loginUser = new UserLogin(userName, pass);
		
		try {
			token = UserService.loginUser(loginUser);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return token;
	}
	
	public static void AllUser(String token) {
		UserServiceImp userService = new UserServiceImp(ConnectionDB.connectDb());
		System.out.println("____________________________________________________________________________________________________________________________");
		System.out.println("UserId : Name         : Email                : IsAmdmin  " );
		System.out.println("--------------------------------------------------------------------------------------------------------------");
		userService.getAllUsers(token).forEach(it->{
			System.out.println(it.getUserId() +"      : "+it.getUserName()+"  : "+it.getUserEmail()+" : " +(it.isAdmin() ? "YES" : "NO"));
		});
		System.out.println("____________________________________________________________________________________________________________________________________________");
	}
	
	public static void orderHistoryOfUser(String token, int userId) {
		OrderServiceImp orderService = new OrderServiceImp(ConnectionDB.connectDb());
		System.out.println("________________________________________________________________________________________________________________________________");
		System.out.println("OrderId : Product name   : proudctQTY : OrderPrice");
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		OrderService.checkuserOrderHistory(userId, token).forEach(it->{
			System.out.println(it.getOrderId()+"       : "+it.getProductName()+"   :  "+it.getProductOty()+" : "+it.getOrderPrice());
		});
		System.out.println("____________________________________________________________________________________________________________________________________");
	}
		
	
}