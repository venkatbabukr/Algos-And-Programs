package com.venkat.java8.interview.model;

import java.util.Arrays;
import java.util.List;

public class Product {
	int productId;
	String category;
	double price;
	long clickCount;

	public Product(int pId, double price, String cat, long cc) {
		this.productId = pId;
		this.price = price;
		this.category = cat;
		this.clickCount = cc;
	}

	public int getProductId() {
		return productId;
	}
	public String getCategory() {
		return category;
	}
	public long getClickCount() {
		return clickCount;
	}
	public double getPrice() {
		return price;
	}
	public String toString() {
		return String.format("{productId: %d, price: %f, category: %s, clickCount: %d}", productId, price, category, clickCount);
	}
	
	public static final List<Product> SAMPLE_LIST1 = Arrays.asList(
			new Product(1, 24.99, "Books", 20),
			new Product(2, 1025.00, "Movies", 2),
			new Product(3, 150.00, "Songs", 100),
			new Product(4, 199.99, "Books", 4),
			new Product(5, 250.00, "Songs", 6),
			new Product(6, 975.00, "Movies", 2),
			new Product(7, 49.99, "Books", 9),
			new Product(8, 1150.00, "Movies", 10),
			new Product(9, 34.99, "Books", 9),
			new Product(10, 750.00, "Movies", 10),
			new Product(1, 24.99, "Books", 9)
		);
}
