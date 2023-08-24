package com.venkat.algos.simple.arrays.matrix;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MatrixSpiralPrinter<T> {

	public MatrixSpiralPrinter<T> print(T[][] matrix) {
		String matrixPrintStr = Arrays.stream(matrix)
                                    .map(matrixRow -> Arrays.toString(matrixRow))
                                    .collect(Collectors.joining("\n"));
		System.out.format("Matrix:\n%s\n", matrixPrintStr);
		return this;
	}

    public MatrixSpiralPrinter<T> spiralPrint(T[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            System.out.println("Almost empty matrix given! Nothing to print...");
            return this;
        }
        int beginRow, endRow, beginCol, endCol;
        beginRow = beginCol = 0;
        endRow = matrix.length - 1;
        endCol = matrix[0].length - 1;
        System.out.println("Spiral print:");
        while (beginRow < endRow && beginCol < endCol) {
            for (int c = beginCol ; c <= endCol ; c++) {
                System.out.print(matrix[beginRow][c] + " ");
            }
            beginRow++;
            for (int r = beginRow ; r <= endRow ; r++) {
                System.out.print(matrix[r][endCol] + " ");
            }
            endCol--;
            for (int c = endCol ; c >= beginCol ; c--) {
                System.out.print(matrix[endRow][c] + " ");                
            }
            endRow--;
            for (int r = endRow ; r >= beginRow ; r--) {
                System.out.print(matrix[r][beginCol] + " ");
            }
            beginCol++;
        }

        /*
         * The above while loop breaks on following conditions:
         * beginRow >= endRow, beginCol >= endCol...
         * 
         * Out of these, the cases where the begins are greater than ends, we need not worry.
         * So, we are left with following:
         * 
         * 1. beginRow == endRow
         * 2. beginCol == endCol
         * 3. Or both - beginRow == endRow && beginCol == endCol...
         * 
         * The first if condition: if (beginRow == endRow)
         *     Takes care of printing remaining in both cases #1 & #3
         *
         * Second if condition: else if (beginRow < endRow && beginCol == endCol)
         *     Takes care of printing remaining in case #2...
         */
        if (beginRow == endRow) {
        	// If we've reached the point where only single row
            // from beginCol to endCol has to be printed...
	        for (int c = beginCol ; c <= endCol ; c++) {
	            System.out.print(matrix[beginRow][c] + " ");
	        }
        } else if (beginRow < endRow && beginCol == endCol) {
        	// This is where only single column from
        	// beginRow to endRow has to be printed...
	        for (int r = beginRow ; r <= endRow ; r++) {
	            System.out.print(matrix[r][beginCol] + " ");
	        }
        }
        System.out.println();
        return this;
    }

    public T spiralFind(T A[][], int k)
    {
		int topR = 0, leftC = 0, bottomR = A.length - 1, rightC = A[0].length - 1;

		int kIter = 0, numElems;
		while (topR < bottomR && leftC < rightC) {
		    numElems = rightC - leftC + 1;
		    if (k - kIter > numElems) {
		        kIter += numElems;
		    } else {
		        return A[topR][leftC + k - kIter - 1];
		    }
			topR++;
			numElems = bottomR - topR + 1;
		    if (k - kIter > numElems) {
		        kIter += numElems;
		    } else {
		        return A[topR + k - kIter - 1][rightC];
		    }
			rightC--;
			numElems = rightC - leftC + 1;
		    if (k - kIter > numElems) {
		        kIter += numElems;
		    } else {
		        return A[bottomR][rightC - (k - kIter - 1)];
		    }
			bottomR--;
			numElems = bottomR - topR + 1;
		    if (k - kIter > numElems) {
		        kIter += numElems;
		    } else {
		        return A[bottomR - (k - kIter - 1)][leftC];
		    }
			leftC++;
		}
		if (topR == bottomR) {
		    return A[topR][leftC + k - kIter - 1];
		} else if (topR < bottomR && leftC == rightC) {
		    return A[topR + k - kIter - 1][leftC];
		}
		return null;
    }

    public static void main(String[] args) {
        MatrixSpiralPrinter<Integer> printer = new MatrixSpiralPrinter<>();

        Integer[][] matrix = null;
        // Test case #1 - Single element
        matrix = new Integer[][] {
            {1}
        };
        printer.print(matrix).spiralPrint(matrix);
        // Test case #2 - Single row
        matrix = new Integer[][] {
            {1, 2, 3}
        };
        printer.print(matrix).spiralPrint(matrix);
        // Test case #3 - Single column
        matrix = new Integer[][] {
            {1},
            {2},
            {3}
        };
        printer.print(matrix).spiralPrint(matrix);
        // Test case #4 - 2x2 square matrix
        matrix = new Integer[][] {
            {1, 2},
            {3, 4}
        };
        printer.print(matrix).spiralPrint(matrix);
        // Test case #5 - 3x3 square matrix
        matrix = new Integer[][] {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        printer.print(matrix).spiralPrint(matrix);
        // Test case #6 - 2x3 rectangular matrix
        matrix = new Integer[][] {
            {1, 2},
            {3, 4},
            {5, 6}
        };
        printer.print(matrix).spiralPrint(matrix);
        // Test case #7 - 2x4 rectangular matrix
        matrix = new Integer[][] {
            {1, 2},
            {3, 4},
            {5, 6},
            {7, 8}
        };
        printer.print(matrix).spiralPrint(matrix);
        // Test case #8 - 3x2 rectangular matrix
        matrix = new Integer[][] {
            {1, 2, 3},
            {4, 5, 6}
        };
        printer.print(matrix).spiralPrint(matrix);
        // Test case #9 - 4x2 rectangular matrix
        matrix = new Integer[][] {
            {1, 2, 3, 4},
            {5, 6, 7, 8}
        };
        printer.print(matrix).spiralPrint(matrix);
        // General problem
        matrix = new Integer[][] {
        	{1, 2, 3, 4}, 
            {5, 6, 7, 8}, 
            {9, 10, 11, 12}, 
            {13, 14, 15, 16}
        };
        printer.print(matrix).spiralPrint(matrix);
    }

}
