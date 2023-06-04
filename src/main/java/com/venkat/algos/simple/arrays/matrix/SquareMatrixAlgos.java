package com.venkat.algos.simple.arrays.matrix;

import com.venkat.utils.ext.ArraysExt;

public class SquareMatrixAlgos {

	private SquareMatrixAlgos() { }

	public static <T> void inlineTranspose(T[][] input) {
		if (input != null && input.length > 0) {
			for (int i = 0 ; i < input.length ; i++) {
				for (int j = 0 ; j <= i ; j++) {
					T temp = input[i][j];
					input[i][j] = input[j][i];
					input[j][i] = temp;
				}
			}
		}
	}
	
	public static <T> void mirrorVertical(T[][] input) {
		if (input != null && input.length > 0) {
			int N = input.length;
			for (int swapCol = 0 ; swapCol < input[0].length / 2 ; swapCol++) {
				for (int swapRow = 0 ; swapRow < input.length ; swapRow++) {
					T temp = input[swapRow][swapCol];
					input[swapRow][swapCol] = input[swapRow][N - 1 - swapCol];
					input[swapRow][N - 1 - swapCol] = temp;
				}
			}
		}
	}

	public static <T> void mirrorHorizontal(T[][] input) {
		if (input != null && input.length > 0) {
			int N = input.length;
			for (int swapRow = 0 ; swapRow < input.length / 2 ; swapRow++) {
				for (int swapCol = 0 ; swapCol < N ; swapCol++) {
					T temp = input[swapRow][swapCol];
					input[swapRow][swapCol] = input[N - 1 - swapRow][swapCol];
					input[N - 1 - swapRow][swapCol] = temp;
				}
			}
		}
	}

	public static <T> void rotateAntiClock(T[][] input) {
		if (input != null && input.length > 0) {
			mirrorVertical(input);
			inlineTranspose(input);
		}
	}
	
	public static <T> void rotateClock(T[][] input) {
		if (input != null && input.length > 0) {
			mirrorHorizontal(input);
			inlineTranspose(input);
		}
	}
	
	public static void main(String[] args) {
		Integer[][] testArr = new Integer[][] {
			new Integer[] {1, 2, 3},
			new Integer[] {4, 5, 6},
			new Integer[] {7, 8, 9}
		};
		
		System.out.format("Input array%n%s%n", ArraysExt.to2DString(testArr));
		inlineTranspose(testArr);
		System.out.format("Transpose array%n%s%n", ArraysExt.to2DString(testArr));
		
		testArr = new Integer[][] {
			new Integer[] {1, 2, 3},
			new Integer[] {4, 5, 6},
			new Integer[] {7, 8, 9}
		};
		System.out.format("Input array%n%s%n", ArraysExt.to2DString(testArr));
		mirrorVertical(testArr);
		System.out.format("Vertical mirror%n%s%n", ArraysExt.to2DString(testArr));

		testArr = new Integer[][] {
			new Integer[] {1, 2, 3},
			new Integer[] {4, 5, 6},
			new Integer[] {7, 8, 9}
		};
		System.out.format("Input array%n%s%n", ArraysExt.to2DString(testArr));
		rotateAntiClock(testArr);
		System.out.format("Anti clockwise rotation%n%s%n", ArraysExt.to2DString(testArr));

		testArr = new Integer[][] {
			new Integer[] {1, 2, 3, 4},
			new Integer[] {5, 6, 7, 8},
			new Integer[] {9, 0, 1, 2},
			new Integer[] {3, 4, 5, 6}
		};
		System.out.format("Input array%n%s%n", ArraysExt.to2DString(testArr));
		rotateClock(testArr);
		System.out.format("Clockwise rotation%n%s%n", ArraysExt.to2DString(testArr));

	}

}
