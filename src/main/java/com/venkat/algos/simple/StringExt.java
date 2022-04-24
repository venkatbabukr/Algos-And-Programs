package com.venkat.algos.simple;

public class StringExt {

    public static boolean isPalindrome(String s) {
        boolean strIsPalindrome = false;
        if (s != null && s.length() > 0) {
            char[] sChars = s.toLowerCase().toCharArray();
            int l = 0, r = sChars.length - 1;
            for (;l < r && sChars[l] == sChars[r]; l++, r--);
            strIsPalindrome = l >= r;
        }
        return strIsPalindrome;
    }

    public static void main(String[] args) {
        String s = null;
        System.out.format("Str=%s, isPalindrome=%s%n", s, isPalindrome(s));
        s = "A";
        System.out.format("Str=%s, isPalindrome=%s%n", s, isPalindrome(s));
        s = "madam";
        System.out.format("Str=%s, isPalindrome=%s%n", s, isPalindrome(s));
        s = "Madam";
        System.out.format("Str=%s, isPalindrome=%s%n", s, isPalindrome(s));
    }

}
