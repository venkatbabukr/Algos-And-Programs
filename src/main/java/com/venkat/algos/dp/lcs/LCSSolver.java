package com.venkat.algos.dp.lcs;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LCSSolver<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(LCSSolver.class);

	private final T[] seq1;
    private final T[] seq2;

    private static class LCSCountMatrix {
        private final int[][] L;

        public LCSCountMatrix(int str1Len, int str2Len) {
            this.L = new int[str1Len][str2Len];
        }

        public int get(int i, int j) {
            return i > -1 && j > -1 ? L[i][j] : 0;
        }

        public void set(int i, int j, int val) {
            L[i][j] = val;
        }

        public String toString() {
            return Arrays.stream(L)
                       .sequential()
                       .map(row -> Arrays.toString(row))
                       .collect(Collectors.joining(System.lineSeparator()));
        }

    }

    public LCSSolver(T[] s1, T[] s2) {
        this.seq1 = s1;
        this.seq2 = s2;
    }

    @SuppressWarnings("unchecked")
	public T[] findLCS() {
        T[] lcsChars = null;
        if (seq1 != null && seq2 != null) {
            LCSCountMatrix countMatrix = new LCSCountMatrix(seq1.length, seq2.length);
            for (int i = 0; i < seq1.length; i++) {
                for (int j = 0; j < seq2.length; j++) {
                    int lcs_ij = seq1[i] == seq2[j] ?
                                     1 + countMatrix.get(i-1, j-1) :
                                     Math.max(countMatrix.get(i-1, j), countMatrix.get(i, j-1));
                    countMatrix.set(i, j, lcs_ij);
                }
            }
            LOGGER.debug("LCSCountMatrix:\n{}\n", countMatrix);

            int i = seq1.length - 1;
            int j = seq2.length - 1;
            int lcsLength = countMatrix.get(i, j);
            lcsChars = (T[]) new Object[lcsLength];

            for (int lcsIdx = lcsLength - 1; lcsIdx >= 0; ) {
                if (seq1[i] == seq2[j]) {
                    lcsChars[lcsIdx] = seq1[i];
                    LOGGER.debug("Added character {} from s1[{}], s2[{}] to lcsChars[{}]={}", seq1[i], i, j, lcsIdx, lcsChars);
                    i--;
                    j--;
                    lcsIdx--;
                } else if (countMatrix.get(i-1, j) > countMatrix.get(i, j-1))
                    i--;
                else
                    j--;
                LOGGER.debug("At the end of iteration, i={}, j={}, lcsIdx={}", i, j, lcsIdx);
            }
        }
        return lcsChars;
    }

    public static void main(String[] args) {
    	Integer[] intSeq1 = new Integer[] { 2, 3, 4, 5 };
    	Integer[] intSeq2 = new Integer[] { 4, 5 };
        LCSSolver<Integer> solver = new LCSSolver<Integer>(intSeq1, intSeq2);
        System.out.format("LCS of %s, %s = %s", Arrays.toString(intSeq1),
                          Arrays.toString(intSeq2), Arrays.toString(solver.findLCS()));
    }

}
