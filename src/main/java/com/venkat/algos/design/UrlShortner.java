package com.venkat.algos.design;

import java.util.Random;

import com.venkat.algos.bits.Base64Util;

/*
 * https://www.geeksforgeeks.org/how-to-design-a-tiny-url-or-url-shortener/
 */
public class UrlShortner {

	public String counterIdToShortURL(int counterId) {
		StringBuilder shortUrlBuilder = new StringBuilder();
		while (counterId > 0) {
			shortUrlBuilder.append(Base64Util.encodeIntVal(counterId & 0x3f));
			counterId = counterId >>> 6;
		}
		return shortUrlBuilder.reverse().toString();
	}
	
	public int shortUrlToCounterId(String shortUrl) {
		int counterId = 0;
		for (char c : shortUrl.toCharArray()) {
			counterId = counterId << 6 | Base64Util.decodeIntVal(c, false);
		}
		return counterId;
	}
	
	public static void main(String[] args) {
		UrlShortner testShortner = new UrlShortner();
		for (int id = 0, reverseId = 0 ; id < Math.abs(new Random().nextInt()) ; id++) {
			String shortUrl = testShortner.counterIdToShortURL(id);
			reverseId = testShortner.shortUrlToCounterId(shortUrl);
			if (id == reverseId) {
				System.out.format("counterIdToShortURL(%1$d)=%2$s, shortUrlToCounterId(%2$s)=%3$d%n", id, shortUrl, reverseId);
			} else {
				System.out.format("Short url (%s) to reverse (%d) not working for %d!%n", shortUrl, id, reverseId);
				break;
			}
		}
	}

}
