package com.venkat.algos.bits;

import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Encoder;

/*
 * References:
 * Encoding: https://www.geeksforgeeks.org/encode-ascii-string-base-64-format/
 * Decoding: https://www.geeksforgeeks.org/decode-encoded-base-64-string-ascii-string/
 */
public class Base64Util {

	public static final char[] ENCODE_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+=".toCharArray();

	public static char encodeIntVal(int v) throws IllegalArgumentException {
		if (v >= 0 && v < 0x40) {
			return ENCODE_TABLE[v];
		} else {
			throw new IllegalArgumentException("Integer value should be in range {0 - 0x3f (63)}");
		}
	}

	public static int decodeIntVal(char encChar, boolean paddingUsed) throws IllegalArgumentException {
		int decodeVal = -1;
		if (encChar >= 'A' && encChar <= 'Z') {
			decodeVal = encChar - 'A';
		} else if (encChar >= 'a' && encChar <= 'z') {
			decodeVal = encChar - 'a' + 26;
	    } else if (encChar >= '0' && encChar <= '9') {
			decodeVal = encChar - '0' + 52;
		} else if (encChar == '+') {
			decodeVal = 0x3e;
		} else if (encChar == '=') {
			if (!paddingUsed) {
				decodeVal = 0x3f;
			}
		} else {
			throw new IllegalArgumentException("Invalid base64 char given!");
		}
		return decodeVal;
	}

	public static char[] encode(byte[] bytes) {
		char[] dest = new char[(int) (4.0/3.0 * (bytes.length + 2))];
		for (int i = 0, k = 0 ; i < bytes.length ; i += 3) {
			int val = 0;
			int bitsCount = 0;

			for (int j = i ; j < Math.min(i + 3, bytes.length) ; j++) {
				val = val << 8 | bytes[j];
				bitsCount += 8;
			}
			int padding = bitsCount % 3;
			while (bitsCount > 0) {
				int shiftIdx = 0, encodeIdx = 0;
				if (bitsCount >= 6) {
					shiftIdx = bitsCount - 6;
					encodeIdx = val >>> shiftIdx & 0x3f;
				} else {
					shiftIdx = 6 - bitsCount;
                    encodeIdx = val << shiftIdx & 0x3f;
				}
				dest[k++] = ENCODE_TABLE[encodeIdx];
				bitsCount -= 6;
			}
			for ( ; padding > 0 ; padding--) {
				dest[k++] = ENCODE_TABLE[0x3f];
			}
		}
		return dest;
	}

	public static String encodeToString(byte[] bytes) {
		return new String(encode(bytes));
	}
	
	public static byte[] decode(char[] b64Chars) {
		byte[] dest = new byte[(int) (b64Chars.length * 3.0 / 4.0)];
		for (int i = 0 ; i < b64Chars.length ; i += 4) {
			int val = 0;
			for (int j = i ; j < i + 4; j++) {
				if (b64Chars[j] != '=') {
					val = val << 6 | decodeIntVal(b64Chars[j], true);
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Encoder javaEncoder = Base64.getEncoder();
		
		// Test encoding...
		for (int arrLen = 1; arrLen <= 3; arrLen++) {
			char[] charArr = new char[arrLen];
			System.out.format("%d char array%n", arrLen);
			for (char c = 'A'; c <= '9'; c++) {
				Arrays.fill(charArr, c);
				String charStr = new String(charArr);
				byte[] charByteArr = charStr.getBytes();
				System.out.format("encodeToString(%1$s)=%2$s,Base64.encode(%1$s)=%3$s%n", charStr, Base64Util.encodeToString(charByteArr), javaEncoder.encodeToString(charByteArr));
			}
		}
		
		// Test single character decoding...
	}

}
