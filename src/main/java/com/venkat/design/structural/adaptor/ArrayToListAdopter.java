package com.venkat.design.structural.adaptor;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayToListAdopter {

    static class ArrayAsList<T> extends AbstractList<T> implements List<T> {

        private T[] baseArray;

        public ArrayAsList(T[] arr) {
            if (arr == null)
                throw new IllegalArgumentException("Can't give null array!");
        	this.baseArray = arr;
        }

		@Override
		public T get(int index) {
			return this.baseArray[index];
		}

		@Override
		public int size() {
			return this.baseArray.length;
		}

    }

    public static <T> List<T> adoptArrayAsList(T[] arr) {
        return new ArrayAsList<>(arr);
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        List<Integer> arrList = ArrayToListAdopter.adoptArrayAsList(arr);

        System.out.format("Array %s adopted to List %s%n", Arrays.toString(arr), arrList);

        System.out.format("Streaming with transformation: %s%n", arrList
                                                                     .stream()
                                                                     .map(a -> String.valueOf(a * a))
                                                                     .collect(Collectors.joining(", ")));

    }

}
