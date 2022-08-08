package com.venkat.algos.simple;

import java.util.Optional;
import java.util.Stack;

import com.venkat.utils.Constants.StringConstants;

public class NumberToWordConverter {

    private static final long ONE_CRORE = 10000000L;

    private static final String AND = "and";
    
    private static final String[] UNIQUE_NUM_WORDS = new String[] {
        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };
    
    private static final String[] TENTH_NUM_WORDS = new String[] {
        "", "", "Twenty", "Thrity", "Fourty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };
    
    private static final String HUNDRED_SUFFIX = " Hundred";

    private static final String[] UNIQUE_MILLION_SYSTEM_SUFFIXES = new String[] {
        "Thousand", "Million", "Billion", "Trillion", "Quadrillion", "Quintillion", "Sextillion"
    };
    
    private static final String[] UNIQUE_ARABIC_SUFFIXES = new String[] {
        "Thousand", "Lakh", "Crore"
    };

    private String wordsStackToWord(Stack<String> wordsStack) {
        return Optional.ofNullable(wordsStack)
                   .map(stack -> {
                       StringBuilder wordBuilder = new StringBuilder();
                       while (!wordsStack.isEmpty()) {
                           wordBuilder.append(wordsStack.pop()).append(StringConstants.SPACE_STRING);
                       }
                       return wordBuilder.toString().trim();
                   })
                   .orElse(null);
    }

    /**
     * Gets word for given two digit number.
     * Logic is simple:
     *     If number < 20, return by a map lookup in unique array of {@link NumberToWordConverter#UNIQUE_NUM_WORDS}
     *     else return {@link NumberToWordConverter#TENTH_NUM_WORDS}[<tenth digit> + " " + {@link NumberToWordConverter#UNIQUE_NUM_WORDS}[<unit digit>]
     *
     * @param number          The two digit number for which word is required
     * 
     * @return                Word for the given two-digit number...
     */
    private String wordForTwoDigits(int number) {
        return number < 20 ?
            UNIQUE_NUM_WORDS[number] :
            String.join(StringConstants.SPACE_STRING, TENTH_NUM_WORDS[number / 10], UNIQUE_NUM_WORDS[number % 10]);
    }

    /**
     * Gets the word for three digit number. Logic is simple 3-steps and explained in-line in code...
     * 
     * @param number            The three digit number for which word is required
     * @param forcePrefixAnd    Pass true if "And" has to be force prefixed/otherwise, the function
     *                          will prefix "And" only if required...
     *                          
     * @return                  The word for the given 3-digit number...
     */
    private String wordForThreeDigits(int number, boolean forcePrefixAnd) {
        Stack<String> wordsStack = new Stack<>();

        int twoDigits = number % 100;
        int hundredth = number / 100;

        // Step #1: Build word for 2 digits...
        wordsStack.push(wordForTwoDigits(twoDigits));

        // Step #2: Logic for prefixing "And"
        //
        // 1. Prefix "And" only if the two digit number != 0 and
        //     a. forcePrefixAnd is true / set
        //     b. Hundredth number is greater than 0
        if (twoDigits != 0 && (forcePrefixAnd || hundredth > 0)) {
            wordsStack.push(AND);
        }

        // Step #3: Push the word for hundredth if the hundredth is greater than zero and add Hundred suffix...
        if (hundredth > 0) {
            wordsStack.push(UNIQUE_NUM_WORDS[hundredth] + HUNDRED_SUFFIX);
        }
        return wordsStackToWord(wordsStack);
    }

    /**
     * Create word for seven digit number (Crore system). Logic is simple Two steps and explained in-line
     * 
     * @param number                     Seven digit number for which word is required
     * @param prefixAndInThreeDigits     If true, "And" prefix is forcefully added in the three digit number, otherwise not
     * 
     * @return                           Word for the given seven-digit number...
     */
    private String wordForSevenDigits(long number, boolean prefixAndInThreeDigits) {
        Stack<String> wordsStack = new Stack<>();

        // Step #1: Build word for 3-digits...
        int threeDigits = (int) number % 1000;
        number /= 1000;
        wordsStack.push(wordForThreeDigits(threeDigits, number > 0 || prefixAndInThreeDigits));

        // Step #2: For remaining max 4-digits, build the word in two iterations and add suitable suffixes...
        int iterationCount = 0;
        while (number > 0 && iterationCount < 2) {
            int twoDigitNumber = (int) number % 100;
            if (twoDigitNumber > 0) {
                wordsStack.push(UNIQUE_ARABIC_SUFFIXES[iterationCount % UNIQUE_ARABIC_SUFFIXES.length]);
            }
            wordsStack.push(wordForTwoDigits(twoDigitNumber));
            number /= 100;
            iterationCount++;
        }
        return wordsStackToWord(wordsStack);
    }

    public String metricWordForNumber(long number) {
        Stack<String> wordsStack = new Stack<>();
        int threeDigits = (int) number % 1000;
        number /= 1000;
        wordsStack.push(wordForThreeDigits(threeDigits, number > 0));
        int iterationCount = 0;
        while (number > 0) {
            wordsStack.push(UNIQUE_MILLION_SYSTEM_SUFFIXES[iterationCount]);
            threeDigits = (int) number % 1000;
            number /= 1000;
            wordsStack.push(wordForThreeDigits(threeDigits, number > 0));
            iterationCount++;
        }
        return wordsStackToWord(wordsStack);
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
        return wordsStackToWord(wordsStack);
    }

    public static void main(String[] args) {
        NumberToWordConverter printer = new NumberToWordConverter();
        for (int i = 100000 ; i < 100222 ; i++) {
            System.out.format("%d = %s\n", i, printer.metricWordForNumber(i));
        }
        long num = 2452320010001L;
        System.out.println("Arabic for " + num + " is " + printer.arabicWordForNumber(num));
    }

}
