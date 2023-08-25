package com.venkat.java8.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.venkat.java8.interview.model.Product;
import com.venkat.utils.ext.CollectionsExt;

public class ProductClickCountSolver {

	/*
	 * The crux here is to use Collectors.toMap() instead of Collectors.groupingBy(), because we want Map<id, Product> and not Map<id, Sum(Click count)!
	 */
	public List<Product> aggregateAndRemoveDuplicates(List<Product> allProductClicksList) {
		return Optional.ofNullable(allProductClicksList)
						.map(l -> {
							Map<Integer, Product> groupedByClickCount = l.stream()
																			.collect(Collectors.toMap(Product::getProductId,
																										Function.identity(),
																										(p1, p2) -> new Product(p1.getProductId(),
																																p1.getPrice(),
																																p1.getCategory(),
																																p1.getClickCount() + p2.getClickCount()),
																										LinkedHashMap::new));
			return groupedByClickCount.values();
		}).map(ArrayList::new).orElse(null);
	}

	public Map<String, List<Product>> getKMostPopular(List<Product> allProductClicksList, int k) {
		return aggregateAndRemoveDuplicates(allProductClicksList)
				.stream()
		        .collect(Collectors.groupingBy(Product::getCategory,
		        			Collectors.collectingAndThen(Collectors.toList(),
		        						l -> CollectionsExt.maxK(l, k, Comparator.comparing(Product::getClickCount)))));
	}
	
	public static void main(String[] args) {
		List<Product> products = Product.SAMPLE_LIST1;

		ProductClickCountSolver solver = new ProductClickCountSolver();
		int popularCount = 3;
		System.out.format("ProductClickCountSolver#getKMostPopular(%s, %d)=%n%s%n", products, popularCount, solver.getKMostPopular(products, popularCount));
	}

}
