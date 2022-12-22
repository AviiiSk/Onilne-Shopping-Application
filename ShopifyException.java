package com.shopify.exception;

public class ShopifyException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	public enum ExceptionType {
        USER_ALREADY_PRESENT,
        EMAIL_NOT_FOUND,
        PASSWORD_INCORRECT,
        EMAIL_NOT_VERIFIED,
        UNAUTHORISED_USER,
        PRODUCT_NOT_FOUND,
        INTERNAL_ERROR,
    }
	public  ShopifyException.ExceptionType type;
	
	public  ShopifyException(String msg, ShopifyException.ExceptionType type) {
		super(msg);
		this.type=type;
	}
	
	public  ShopifyException(String msg) {
		super(msg);
	}
	
	
	

}
