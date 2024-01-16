package com.venkat.java8.interview.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ArrayAlgosExt {

	public static <T> Map<T, Long> elementsWithCount(T[] array) {
		return Optional.ofNullable(array)
					.map(arr -> Arrays.stream(arr).collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting())))
					.orElse(null);
	}

	public static <T extends Comparable<T>> Map<T, Long> sortedElementsWithCount(T[] array) {
		return Optional.ofNullable(array)
					.map(arr -> Arrays.stream(arr).collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting())))
					.orElse(null);
	}

	public static <T> Map<T, Long> elementsWithCount(T[] array, Supplier<Map<T, Long>> mapSupplier) {
		return Optional.ofNullable(array)
					.map(arr -> Arrays.stream(arr).collect(Collectors.groupingBy(Function.identity(), mapSupplier, Collectors.counting())))
					.orElse(null);
	}
	
	public static Map<Character, Long> charsInStrWithCount(String str) {
		return Optional.ofNullable(str)
					.map(s -> {
						Character[] sChars = s.chars()
												.mapToObj(c -> Character.valueOf((char) c))
												.toArray(Character[]::new);
						return elementsWithCount(sChars);
					})
					.orElse(null);
	}

}
