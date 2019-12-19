package com.venkat.algos.recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import com.venkat.algos.bits.BitUtils;

/**
 * GrayCode is a sequence of bit-string codes where each code is obtained from previous one by just flipping one bit...
 * <br><br>
 * Examples: <br>
 *     1. GrayCode for 1 bit = [0, 1]<br>
 *     2. GrayCode for 2 bits = [00, 01, 11, 10]<br>
 *     3. GrayCode for 3 bits = [000, 001, 011, 010, 110, 111, 101, 100]<br>
 *     4. GrayCode for 4 bits = [0000, 0001, 0011, 0010, 0110, 0111, 0101, 0100,<br>
 *                               1100, 1101, 1111, 1110, 1010, 1011, 1001, 1000]
 *     
 * Problem is to get this list of GrayCodes for given bitLength/bitSize...
 * <br>
 * As we can see from pattern above, the list for bitSize n can be built using recursive calls to get list of codes for size (n-1)
 * and prepend '0' first to the list,<br>
 * and then prepend '1' to the list reversed...<br>
 * GrayCode for 3 bits = [0 + {00, 01, 11, 10}] + [1 + {10, 11, 01, 00}]
 * <br>
 * @author venkat
 */
public class GrayCodeBuilder {
    
    private static final List<String> ONE_BIT_GRAY_CODE_LIST = Arrays.asList(new String[] {BitUtils.ZERO_STR, BitUtils.ONE_STR});
    private static final int[] ONE_BIT_GRAY_CODE_INT_ARRAY = new int[] {0, 1};

    public List<String> buildGrayCodeStringList(int numBits) {
        if (numBits <= 1) {
            return ONE_BIT_GRAY_CODE_LIST;
        }
        List<String> nBitGrayCode = new ArrayList<>((int) Math.pow(2, numBits));
        List<String> subGrayCode = buildGrayCodeStringList(numBits - 1);

        /*
         * Use ListIterator, so that we can do iterations both in forward & reverse directions...
         */
        ListIterator<String> iter = subGrayCode.listIterator();
        while (iter.hasNext()) {
            nBitGrayCode.add(BitUtils.ZERO_STR + iter.next());
        }

        /*
         * Logic for reverse iteration...
         */
        iter = subGrayCode.listIterator(subGrayCode.size());
        while (iter.hasPrevious()) {
            nBitGrayCode.add(BitUtils.ONE_STR + iter.previous());
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

        // Just do the arrayCopy, the zero prepending is same as copying the subArray!
        System.arraycopy(subGrayCodeIntArray, 0, grayCodeIntArray, 0, subGrayCodeIntArray.length);

        int onePrefixNumber = 1 << (numBits - 1);
        for (int idx = arraySize/2 ; idx < arraySize ; idx++) {
            grayCodeIntArray[idx] = subGrayCodeIntArray[arraySize - idx - 1] | onePrefixNumber;
        }
        return grayCodeIntArray;
    }
    
    public void printGrayCodeIntArray(int numBits, int[] codeIntArray) {
        Arrays.stream(codeIntArray)
            .sequential()
            .boxed()
            .map(i -> BitUtils.toBinaryString(i, numBits))
            .forEach(System.out::println);
    }

    public static void main(String[] args) {
        GrayCodeBuilder builder = new GrayCodeBuilder();
        
        for (int numBits = 1 ; numBits <= 5 ; numBits++) {
            System.out.format("Graycode list for bits #%d\n", numBits);
            System.out.format("--------------------------\n", numBits);
            builder.printGrayCodeIntArray(numBits, builder.buildGrayCodeIntArray(numBits));
        }
    }

}
