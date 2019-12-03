package com.venkat.algos.simple;

import java.util.Stack;

public class NumberToWordConverter {

    private static final long ONE_CRORE = 10000000L;

    private static final String SPACE = " ";
    
    private static final String AND = "and";
    
    private static final String[] UNIQUE_NUM_WORDS = new String[] {
        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };
    
    private static final String[] TENTH_NUM_WORDS = new String[] {
        "", "", "Twenty", "Thrity", "Fourty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };
    
    private static final String HUNDRED_SUFFIX = " Hundred";

    private static final String[] UNIQUE_METRIC_SUFFIXES = new String[] {
        "Thousand", "Million", "Billion", "Trillion"
    };
    
    private static final String[] UNIQUE_ARABIC_SUFFIXES = new String[] {
        "Thousand", "Lakh", "Crore"
    };

    private String wordForTwoDigits(int number) {
        String wordForNumber = null;
        if (number < 20) {
            wordForNumber = UNIQUE_NUM_WORDS[number];
        } else {
            wordForNumber = TENTH_NUM_WORDS[number / 10] + SPACE + UNIQUE_NUM_WORDS[number % 10];
        }
        return wordForNumber;
    }

    private String wordForThreeDigits(int number, boolean prefixAnd) {
    	Stack<String> wordsStack = new Stack<>();
    	wordsStack.push(wordForTwoDigits(number % 100));
        int hundredth = number / 100;
    	if (number % 100 > 0 && (prefixAnd || hundredth > 0)) {
    		wordsStack.push(AND);
    	}
        if (hundredth > 0) {
            wordsStack.push(UNIQUE_NUM_WORDS[hundredth] + HUNDRED_SUFFIX);
        }
        StringBuilder numberWordBuilder = new StringBuilder();
        while (!wordsStack.isEmpty()) {
        	numberWordBuilder
                .append(wordsStack.pop())
                .append(SPACE);
        }
        return numberWordBuilder.toString().trim();
    }

    private String wordForSevenDigits(long number, boolean prefixAndInThreeDigits) {
        Stack<String> wordsStack = new Stack<>();
        int threeDigits = (int) number % 1000;
        number /= 1000;
        wordsStack.push(wordForThreeDigits(threeDigits, number > 0 || prefixAndInThreeDigits));

        int cnt = 0;
        while (number > 0 && cnt < 2) {
        	int twoDigitNumber = (int) number % 100;
        	if (twoDigitNumber > 0) {
                wordsStack.push(UNIQUE_ARABIC_SUFFIXES[cnt % UNIQUE_ARABIC_SUFFIXES.length]);
        	}
            wordsStack.push(wordForTwoDigits(twoDigitNumber));
            number /= 100;
            cnt++;
        }
        StringBuilder numberWordBilder = new StringBuilder();
        while (!wordsStack.isEmpty()) {
            numberWordBilder.append(wordsStack.pop()).append(SPACE);
        }
        return numberWordBilder.toString().trim();
    }

    public String metricWordForNumber(long number) {
        Stack<String> wordsStack = new Stack<>();
        int threeDigits = (int) number % 1000;
        number /= 1000;
        wordsStack.push(wordForThreeDigits(threeDigits, number > 0));
        int cnt = 0;
        while (number > 0) {
            wordsStack.push(UNIQUE_METRIC_SUFFIXES[cnt]);
            threeDigits = (int) number % 1000;
            number /= 1000;
            wordsStack.push(wordForThreeDigits(threeDigits, number > 0));
            cnt++;
        }
        StringBuilder numberWordBilder = new StringBuilder();
        while (!wordsStack.isEmpty()) {
            numberWordBilder.append(wordsStack.pop()).append(SPACE);
        }
        return numberWordBilder.toString().trim();
    }
    
    public String arabicWordForNumber(long number) {
        Stack<String> wordsStack = new Stack<>();
        
        long convertNumber = number % ONE_CRORE;
        number /= ONE_CRORE;
        wordsStack.push(wordForSevenDigits(convertNumber, number > 0));
        while (number > 0) {
        	wordsStack.push(UNIQUE_ARABIC_SUFFIXES[2]);
        	convertNumber = number % ONE_CRORE;
            number /= ONE_CRORE;
            wordsStack.push(wordForSevenDigits(convertNumber, number > 0));
        }
        
        StringBuilder numberWordBilder = new StringBuilder();
        while (!wordsStack.isEmpty()) {
            numberWordBilder.append(wordsStack.pop()).append(SPACE);
        }
        return numberWordBilder.toString().trim();
    }

    public static void main(String[] args) {
        NumberToWordConverter printer = new NumberToWordConverter();
        for (int i = 100000 ; i < 100102 ; i++) {
            System.out.format("%d = %s\n", i, printer.metricWordForNumber(i));
        }
        long num = 2452320000001L;
        System.out.println("Arabic for " + num + " is " + printer.arabicWordForNumber(num));
    }

}
