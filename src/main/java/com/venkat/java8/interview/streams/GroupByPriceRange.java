package com.venkat.java8.interview.streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.venkat.java8.interview.model.Product;
import com.venkat.utils.Pair;
import com.venkat.utils.ext.NumbersExt;

public class GroupByPriceRange {
	
	public static void main(String[] args) {
		
		Map<Pair<Integer>, List<Product>> productsByPriceRange =
				Product.SAMPLE_LIST1
					.stream()
					.collect(Collectors.groupingBy(p -> NumbersExt.decimalRange(p.getPrice()),
								() -> new TreeMap<>(Comparator.comparing(Pair::getX)),
								Collectors.toList()));
		
		System.out.println(productsByPriceRange);
		
	}

}
