package com.venkat.algos.bits;

import com.venkat.utils.Constants;

public final class BitUtils {
    
    private BitUtils() { }

    public static final char ZERO_CHAR = '0';
    public static final char ONE_CHAR = '1';

    public static final String ZERO_STR = "0";
    public static final String ONE_STR = "1";

    public static void validateBitStr(String bitStr) {
        if (bitStr == null || bitStr.trim().length() == 0)
            throw new IllegalArgumentException("bitStr can't be null or empty!");
        if (bitStr.chars().anyMatch(c -> c < '0' || c > '1'))
            throw new IllegalArgumentException("bitStr is not binary!");
    }

    public static void flipBitAtPos(char[] bitStrArray, int pos) {
        bitStrArray[pos] = (char) ((bitStrArray[pos] ^ ONE_CHAR) + ZERO_CHAR);
    }

    public static void safeFlipBitAtPos(char[] bitStrArray, int pos) {
    	if (bitStrArray == null)
    		throw new IllegalArgumentException("bitStrArray is null!");
    	if (pos < 0 || pos > bitStrArray.length)
            throw new IllegalArgumentException("pos is out of bounds of bitStrArray!");
    	flipBitAtPos(bitStrArray, pos);
    }

    public static String toBinaryString(int num, int bitsLength) {
        return String.format("%" + bitsLength + "s", Integer.toBinaryString(num))
                   .replace(Constants.SPACE_CHAR, ZERO_CHAR);
    }

}
