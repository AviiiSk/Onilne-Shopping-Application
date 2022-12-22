package com.shopify.controller;

import java.util.Scanner;

import com.eshopify.connectiondb.ConnectionDB;
import com.eshopify.entity.Product;
import com.eshopify.entity.User;
import com.shopify.message.properties.MessageProperties;
import com.shopify.services.ProductService;
import com.shopify.services.ProductServiceImp;
import com.shopify.services.UserService;
import com.shopify.services.UserServiceImp;
import com.shopify.utility.AppUtility;

public class ShopifyApplication{

	public static void run() {
		UserService userService = new UserServiceImp(ConnectionDB.connectDb());
		ProductService productService = new ProductServiceImp(ConnectionDB.connectDb());
		Scanner sc = new Scanner(System.in);
		String token = null;
		token = AppUtility.login(token, sc);
		if (token == null) {
			System.out.println("Do you wan to register with us, Plese enter 1 for Yes else enter any number");
			int option = sc.nextInt();
			if (option == 1) {
				User user = new User();
				System.out.print("Please Enter Your Name : ");
				String name = sc.next();
				System.out.print("please Enter Your Email: ");
				String email = sc.next();
				
				System.out.print("Please Set Password : ");
				String password = sc.next();
				user.setUserName(name);
				user.setUserEmail(email);
				user.setUserPassword(password);
				userService.createUserRegistration(user);
				token = AppUtility.login(token, sc);
				AppUtility.operation(token, sc);
			} else {
				System.out.println(MessageProperties.VISITE_AGAIN.getMessage());
			}

		} else {
			boolean run = true;
			while (run) {
				System.out.println("*****************Product Details**********************************");
				AppUtility.allProducts();
				System.out.println("Select Option from following : ");
				System.out.println("To know all Users : 1");
				System.out.println("To check order history of user : 2");
				System.out.println("To add products: 3");
				System.out.println("To Update Stock of Product : 4");
				System.out.println("To Purchase Product : 5");
				System.out.println("To Logout : 6");
				int select = sc.nextInt();
				switch (select) {
				case 1:
					System.out.println("******************************Users Details******************************");
					AppUtility.AllUser(token);
					break;
				case 2:
					System.out.println("******************************Users Details******************************");
					AppUtility.AllUser(token);
					System.out.println("Please Select Id of User from above to check Order History: ");
					int id = sc.nextInt();
					AppUtility.orderHistoryOfUser(token, id);
					break;
				case 3:
					Product pr = new Product();
					System.out.println("Please Enter Product Name: ");
					String pName = sc.next();
					pr.setProductName(pName);
					System.out.println("Please set Product price :");
					int pPrice = sc.nextInt();
					pr.setProductPrice(pPrice);
					System.out.println("Please Enter Product Description : ");
					String desc = sc.next();
					pr.setProductDecription(desc);
					System.out.println("Please Set Qty Of prodcut : ");
					int qty = sc.nextInt();
					pr.setProductQty(qty);
					productService.addProducts(pr, token);
					break;
				case 4:
					System.out.println("*******************************Product Details******************************************");
					AppUtility.allProducts();
					System.out.println("Select ProductId form above proudct list which you want to update.");
					int productId = sc.nextInt();
					System.out.println("Enter Product qty to update. :");
					int productQty = sc.nextInt();
					int updateStockOfProduct = productService.updataStockOfProudct(token, productId, productQty);
					if (updateStockOfProduct == 1) {
						System.out.println(MessageProperties.UPDATED_PRODUCT_STOCK.getMessage());
					} else {
						System.out.println(MessageProperties.INTERNAL_ERROER.getMessage());
					}
					break;
				case 5:
					AppUtility.operation(token, sc);
					break;
				case 6:
					run = false;
					break;
				default:
					System.out.println("Please Select correct option!");
				}

			}
		}
		System.out.println(MessageProperties.VISITE_AGAIN.getMessage());
	}

	public static void main(String[] args) {
		run();
	UserService userService = new UserServiceImp(ConnectionDB.connectDb());
		String token = "aveshshaikh2000@gmail.com";
	int userId=3;
// int makeUserAsAdmin = userService.makeUserAsAdmin(token, userId);
//	System.out.println(makeUserAsAdmin);

	ProductService productService = new ProductServiceImp(ConnectionDB.connectDb());
	int productId=1;
	int productQty = 20;
	int updateStockOfProduct = productService.updataStockOfProudct(token, productId, productQty);
	System.out.println(updateStockOfProduct);
	}
}