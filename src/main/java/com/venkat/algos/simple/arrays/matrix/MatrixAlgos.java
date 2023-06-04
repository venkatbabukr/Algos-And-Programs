package com.venkat.algos.simple.arrays.matrix;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.venkat.utils.ext.ArraysExt;

public class MatrixAlgos {

	public static final Logger LOGGER = LoggerFactory.getLogger(MatrixAlgos.class);

	private MatrixAlgos() { }
	
	@SuppressWarnings("unchecked")
	public static <T> T[][] transpose(T[][] input, Class<T> clazz) {
		T[][] transpose = null;
		if (input == null) {
			transpose = null;
		} else if (input.length == 0 || input[0].length == 0) {
			transpose = Arrays.copyOf(input, input.length);
		} else if (input != null) {
			int[] transposeSize = new int[] {input[0].length, input.length};
			transpose = (T[][]) Array.newInstance(clazz, transposeSize);
			for (int i = 0 ; i < input.length ; i++) {
				for (int j = 0 ; j < input[i].length; j++) {
					transpose[j][i] = input[i][j];
				}
			}
		}
		return transpose;
	}
	
	public static Integer[][] zeroMatrix(Integer[][] input) {
		LOGGER.info("Entering zeroMatrix");
		Integer[][] zeroMatrix = input;
		if (input != null && input.length > 0) {
			zeroMatrix = new Integer[input.length][input[0].length];
			boolean firstColZero = false;

			for (int i = 0 ; i < input.length ; i++) {
				for (int j = 0 ; j < input[i].length ; j++) {
					zeroMatrix[i][j] = input[i][j];
					if (input[i][j] == 0) {
						zeroMatrix[i][0] = 0;
						if (j != 0) {
							zeroMatrix[0][j] = 0;
						}
						firstColZero |= (j == 0);
					}
				}
			}
			LOGGER.debug("Zero matrix with header rows/cols:");
			LOGGER.debug("\n" + ArraysExt.to2DString(zeroMatrix));

			// Update all rows...
			for (int i = 0 ; i < zeroMatrix.length ; i++) {
				if (zeroMatrix[i][0] == 0) {
					for (int j = 1; j < zeroMatrix[i].length; j++) {
						zeroMatrix[i][j] = 0;
					}
				}
			}
			LOGGER.debug("Zero matrix after rows update:");
			LOGGER.debug("\n" + ArraysExt.to2DString(zeroMatrix));

			// Update all cols...
			if (firstColZero) {
				LOGGER.debug("First col is zero, so updating first col elements to 0");
				for (int i = 0 ; i < zeroMatrix.length ; i++)
					zeroMatrix[i][0] = 0;
			}
			for (int j = 1 ; j < input[0].length ; j++) {
				if (zeroMatrix[0][j] == 0) {
					for (int i = 1 ; i < zeroMatrix.length ; i++) {
						zeroMatrix[i][j] = 0;
					}
				}
			}
			LOGGER.debug("Zero matrix after columns update:");
			LOGGER.debug("\n" + ArraysExt.to2DString(zeroMatrix));

		}
		return zeroMatrix;
	}

	public static <T> Integer[][] countPaths(T[][] input) {
		Integer[][] pathsCount = null;
		if (input != null && input.length > 0) {
			pathsCount = new Integer[input.length][input[0].length];
			pathsCount[0][0] = 0;
			// Set paths in first row...
			for (int r = 1 ; r < input.length ; r++) {
				pathsCount[r][0] = 1;
			}
			// Set paths in first col...
			for (int c = 1 ; c < input[0].length ; c++) {
				pathsCount[0][c] = 1;
			}
			// Calculate for remaining...
			for (int r = 1 ; r < input.length ; r++) {
				for (int c = 1; c < input[r].length ; c++) {
					pathsCount[r][c] = pathsCount[r - 1][c] +
										pathsCount[r][c - 1];
				}
			}
		}
		return pathsCount;
	}

    public static void main(String[] args) {
    	Integer[][] input = null;
    	System.out.println("Input");
    	System.out.println(ArraysExt.to2DString(input));
    	System.out.println("Transpose");
    	System.out.println(ArraysExt.to2DString(transpose(input, Integer.class)));

    	input = new Integer[0][0];
    	System.out.println("Input");
    	System.out.println(ArraysExt.to2DString(input));
    	System.out.println("Transpose");
    	System.out.println(ArraysExt.to2DString(transpose(input, Integer.class)));

    	input = new Integer[][] {
    		new Integer[] {1, 2, 3},
    		new Integer[] {4, 5, 6}
    	};

    	System.out.println("Input");
    	System.out.println(ArraysExt.to2DString(input));
    	System.out.println("Transpose");
    	System.out.println(ArraysExt.to2DString(transpose(input, Integer.class)));
    }
}
