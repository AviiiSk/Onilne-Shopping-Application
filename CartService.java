package com.shopify.services;
import java.util.List;

import com.eshopify.entity.cart;
import com.shopify.model.CartModel;

public interface CartService {

	int increaseQtyAndPriceInCartWhenProductIsAddedInCart(String token, cart cart, int cartId);

	int addToCart(String token, cart cart);

	List<CartModel> getCartDetails(String token);

}