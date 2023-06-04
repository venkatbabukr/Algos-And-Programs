package com.venkat.algos.simple.arrays.matrix;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.venkat.utils.ext.ArraysExt;

public class MatrixAlgosTest {

	@Test
	public void testZeroMatrix() {

		// Arbitrary zeros...
		Integer[][] testMatrix = new Integer[][] {
			new Integer[] { 1, 2, 3, 4 },
			new Integer[] { 5, 6, 0, 8 },
			new Integer[] { 9, 0, 1, 2 }
		};
		
		Integer[][] zeroMatrix = MatrixAlgos.zeroMatrix(testMatrix);
		Integer[][] expectedZeroMatrix = new Integer[][] {
			new Integer[] { 1, 0, 0, 4 },
			new Integer[] { 0, 0, 0, 0 },
			new Integer[] { 0, 0, 0, 0 }
		};
		assertTrue(Arrays.deepEquals(zeroMatrix, expectedZeroMatrix));

		testMatrix = new Integer[][] {
			new Integer[] { 1, 2, 3, 4, 5 },
			new Integer[] { 6, 7, 8, 9, 0 },
			new Integer[] { 0, 1, 2, 3, 4 },
			new Integer[] { 5, 6, 7, 8, 9 }
		};
		
		zeroMatrix = MatrixAlgos.zeroMatrix(testMatrix);
		expectedZeroMatrix = new Integer[][] {
			new Integer[] { 0, 2, 3, 4, 0 },
			new Integer[] { 0, 0, 0, 0, 0 },
			new Integer[] { 0, 0, 0, 0, 0 },
			new Integer[] { 0, 6, 7, 8, 0 }
		};
		System.out.println(ArraysExt.to2DString(zeroMatrix));
		assertTrue(Arrays.deepEquals(zeroMatrix, expectedZeroMatrix));
	}

}
