package com.shopify.services;

import java.util.List;

import com.eshopify.entity.Product;

public interface ProductService {

	public int addProducts(Product product, String token);

	public List<Product> fetchAllProduct();

	public int updataStockOfProudct(String token, int productId, int productQty);

}
