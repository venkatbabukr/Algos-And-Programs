package com.venkat.algos.recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import com.venkat.algos.bits.BitStringUtils;

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
    
    private static final List<String> ONE_BIT_GRAY_CODE_LIST = Arrays.asList(new String[] {BitStringUtils.ZERO_STR, BitStringUtils.ONE_STR});
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
            nBitGrayCode.add(BitStringUtils.ZERO_STR + iter.next());
        }

        /*
         * Logic for reverse iteration...
         */
        iter = subGrayCode.listIterator(subGrayCode.size());
        while (iter.hasPrevious()) {
            nBitGrayCode.add(BitStringUtils.ONE_STR + iter.previous());
        }

        return nBitGrayCode;
    }

    public int[] buildGrayCodeIntArray(int numBits) {
        if (numBits <= 1) {
            return ONE_BIT_GRAY_CODE_INT_ARRAY;
        }
        int[] subGrayCodeIntArray = buildGrayCodeIntArray(numBits - 1);
        int[] grayCodeIntArray = new int[subGrayCodeIntArray.length * 2];
        int arraySize = grayCodeIntArray.length;

        // Just do the arrayCopy, the zero prepending is same as copying the subArray!
        System.arraycopy(subGrayCodeIntArray, 0, grayCodeIntArray, 0, subGrayCodeIntArray.length);

        int onePrefixNumber = 1 << (numBits - 1);
        for (int idx = arraySize/2 ; idx < arraySize ; idx++) {
            grayCodeIntArray[idx] = subGrayCodeIntArray[arraySize - idx - 1] | onePrefixNumber;
        }
        return grayCodeIntArray;
    }

    /*
     * See "Converting to and from Gray code" in https://en.wikipedia.org/wiki/Gray_code
     */
    public int binaryToGray(int num) {
        return num ^ (num >> 1);
    }

    public int grayToBinary(int grayNum) {
        int binNum = grayNum;
        int mask = grayNum;
        while (mask > 0) {
            mask >>= 1;
            binNum ^= mask;
        }
        return binNum;
    }

    public int[] fastBuildGrayCodeIntArray(int numBits) {
        int arraySize = 1 << numBits;
        int[] result = new int[arraySize];
        for (int i = 0 ; i < arraySize; i++) {
            result[i] = binaryToGray(i);
        }
        return result;
    }

    public void printGrayCodeIntArray(int numBits, int[] codeIntArray) {
        Arrays.stream(codeIntArray)
            .sequential()
            .boxed()
            .map(i -> {
                int iBinary = grayToBinary(i);
                return String.format("Gray: %s, Binary: %s(%d)", BitStringUtils.toBinaryString(i, numBits), BitStringUtils.toBinaryString(iBinary, numBits), iBinary);
            })
            .forEach(System.out::println);
    }

    public static void main(String[] args) {
        GrayCodeBuilder builder = new GrayCodeBuilder();
        
        for (int numBits = 1 ; numBits <= 5 ; numBits++) {
            System.out.format("Graycode list for bits #%d%n", numBits);
            System.out.format("--------------------------%n", numBits);
            builder.printGrayCodeIntArray(numBits, builder.fastBuildGrayCodeIntArray(numBits));
        }
        
    }

}
