package com.venkat.algos.recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class GrayCodeBuilder {
	
	private static final List<String> ONE_BIT_GRAY_CODE_LIST = Arrays.asList(new String[] {"0", "1"});
	private static final int[] ONE_BIT_GRAY_CODE_INT_ARRAY = new int[] {0, 1};

	public List<String> buildForBits(int numBits) {
		if (numBits <= 1) {
			return ONE_BIT_GRAY_CODE_LIST;
		}
		List<String> nBitGrayCode = new ArrayList<>((int) Math.pow(2, numBits));
		List<String> subGrayCode = buildForBits(numBits - 1);
		
		for (ListIterator<String> iter = subGrayCode.listIterator() ; iter.hasNext();) {
			nBitGrayCode.add("0" + iter.next());
		}
		for (ListIterator<String> iter = subGrayCode.listIterator(subGrayCode.size()) ; iter.hasPrevious();) {
			nBitGrayCode.add("1" + iter.previous());
		}

		return nBitGrayCode;
	}

	public int[] buildGrayCodeIntArray(int numBits) {
		if (numBits <= 1) {
			return ONE_BIT_GRAY_CODE_INT_ARRAY;
		}
		int arraySize = (int) Math.pow(2, numBits);
		int[] grayCodeIntArray = new int[arraySize];
		int[] subGrayCodeIntArray = buildGrayCodeIntArray(numBits - 1);
		System.arraycopy(subGrayCodeIntArray, 0, grayCodeIntArray, 0, subGrayCodeIntArray.length);
		
		int numBitsNum = 1 << (numBits - 1);
		for (int idx = arraySize/2 ; idx < arraySize ; idx++) {
			grayCodeIntArray[idx] = subGrayCodeIntArray[arraySize - idx - 1] | numBitsNum;
		}
		return grayCodeIntArray;
	}
	
	public void printGrayCodeIntArray(int numBits, int[] codeIntArray) {
		Arrays.stream(codeIntArray).boxed()
            .map(i -> String.format("%d\t" + "%" + numBits + "s", i, Integer.toBinaryString(i)))
            .forEach(System.out::println);
	}

	public static void main(String[] args) {
		GrayCodeBuilder builder = new GrayCodeBuilder();
		
		/*
		for (int i = 1 ; i <= 5 ; i++) {
			System.out.format("Graycode list for bits #%d=%s\n", i, builder.buildForBits(i));
		}
		*/
		
		for (int i = 1 ; i <= 5 ; i++) {
			System.out.format("Graycode list for bits #%d\n", i);
			System.out.format("--------------------------\n", i);
			builder.printGrayCodeIntArray(i, builder.buildGrayCodeIntArray(i));
		}
	}

}
