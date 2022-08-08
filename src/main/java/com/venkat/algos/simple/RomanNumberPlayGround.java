package com.venkat.algos.simple;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.venkat.utils.KeyValuePair;
import com.venkat.utils.ext.Mapper;

public class RomanNumberPlayGround {

    public enum RomanLiterals {
        UNITS_LITERALS(new RomanLiteralsData('I', 'V', 'X', 10)),
        TENS_LITERALS(new RomanLiteralsData('X', 'L', 'C', 100)),
        HUNDREDS_LITERALS(new RomanLiteralsData('C', 'D', 'M', 1000));

        static final class RomanLiteralsData {
            final Character maxChar;
            final Character midChar;
            final Character minChar;
            final int maxCharVal;

            public RomanLiteralsData(Character minC, Character midC, Character maxC, int maxVal) {
                this.maxChar = maxC;
                this.midChar = midC;
                this.minChar = minC;
                this.maxCharVal = maxVal;
            }

            public int maxCharVal() {
                return maxCharVal;
            }

            public int midCharVal() {
                return maxCharVal/2;
            }

            public int minCharVal() {
                return maxCharVal/10;
            }

            public String validationPattern() {
                return String.format("(%1$c%3$c|%1$c%2$c|%2$c?%1$c{0,3})", minChar, midChar, maxChar);
            }

            public String romanStr(int n) {
                if (n < 0 || n > 9)
                    throw new IllegalArgumentException("Number between 0 and 9 should be given!");
                StringBuilder romanStrBuilder = new StringBuilder();
                int romanValAppended = 0;
                if (n == 9) {
                    romanStrBuilder.append(minChar).append(maxChar);
                    romanValAppended = 9;
                } else if (n >= 5) {
                    romanStrBuilder.append(midChar);
                    romanValAppended = 5;
                } else if (n == 4) {
                    romanStrBuilder.append(minChar).append(midChar);
                    romanValAppended = 4;
                }
                n -= romanValAppended;
                if (n > 0) {
                    romanStrBuilder.append(new String(new char[n]).replace('\0', minChar));
                }
                return romanStrBuilder.toString();
            }

        }

        private RomanLiteralsData data;

        private RomanLiterals(RomanLiteralsData d) {
            this.data = d;
        }

        public RomanLiteralsData getData() {
            return data;
        }

        public String getRomanStr(int v) {
            return this.data.romanStr(v);
        }

    }

    public static final String ROMAN_THOUSANDS_VALIDATOR_PATTERN_STR = "M{0,3}";
    public static final String ROMAN_VALIDATOR_PATTERN_STR = ROMAN_THOUSANDS_VALIDATOR_PATTERN_STR + 
                                                                 Stream.of(RomanLiterals.values())
                                                                       .map(RomanLiterals::getData)
                                                                       .sorted(Comparator.comparing(RomanLiterals.RomanLiteralsData::maxCharVal).reversed())
                                                                       .map(d -> d.validationPattern())
                                                                       .collect(Collectors.joining());

    /**
     * Final map: {C=100, D=500, V=5, X=10, I=1, L=50, M=1000}
     */
    public static final Map<Character, Integer> ROMAN_CHAR_TO_VALUE_MAP = Stream.of(RomanLiterals.values())
                                                                                .map(RomanLiterals::getData)
                                                                                .flatMap(d ->
                                                                                    Stream.of(
                                                                                        new KeyValuePair<Character, Integer>(d.maxChar, d.maxCharVal()),
                                                                                        new KeyValuePair<Character, Integer>(d.midChar, d.midCharVal()),
                                                                                        new KeyValuePair<Character, Integer>(d.minChar, d.minCharVal())))
                                                                                .collect(Collectors.toMap(KeyValuePair::key, KeyValuePair::val, (kvp1, kvp2) -> kvp1));

    private static final TreeMap<Integer, Mapper<Integer, String>> ROMAN_MAPPERS_BY_RADIX = new TreeMap<Integer, Mapper<Integer,String>>(Comparator.reverseOrder()) {
        /** Default SerialVersionUID */
        private static final long serialVersionUID = 1L;

        {
            put(1000, (vNum) -> new String(new char[vNum]).replace('\0', 'M'));
            put(100, RomanLiterals.HUNDREDS_LITERALS::getRomanStr);
            put(10, RomanLiterals.TENS_LITERALS::getRomanStr);
            put(1, RomanLiterals.UNITS_LITERALS::getRomanStr);
        }
    };

    public int romanToInt(String romanNumber) {
        if (!Pattern.matches(ROMAN_VALIDATOR_PATTERN_STR, romanNumber)) {
            throw new IllegalArgumentException("Invalid roman numer given!");
        }
        int numberSum = 0;
        Integer prevRomanInt = 0, currRomanInt = null;
        for (Character c : romanNumber.toCharArray()) {
            currRomanInt = ROMAN_CHAR_TO_VALUE_MAP.get(c);
            numberSum += currRomanInt;
            if (prevRomanInt < currRomanInt) {
                numberSum -= 2 * prevRomanInt;
            }
            prevRomanInt = currRomanInt;
        }
        return numberSum;
    }

    public String intToRoman(int i) {
        if (i >= 4000)
            throw new IllegalArgumentException("Maximum value can be 4000");
        StringBuilder romanStringBuilder = new StringBuilder();
        Iterator<Entry<Integer, Mapper<Integer, String>>> mappersItrator = ROMAN_MAPPERS_BY_RADIX.entrySet().iterator();
        while (i > 0 && mappersItrator.hasNext()) {
            Entry<Integer, Mapper<Integer, String>> e = mappersItrator.next();
            int radix = e.getKey();
            romanStringBuilder.append(e.getValue().apply(i/radix));
            i %= radix;
        }

        return romanStringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.format("Roman number validation pattern: %s%n", ROMAN_VALIDATOR_PATTERN_STR);

        RomanNumberPlayGround converter = new RomanNumberPlayGround();

        for (int i = 0; i < 4000; i++) {
            String romanVal = converter.intToRoman(i);
            int revI = converter.romanToInt(romanVal);
            assert i == revI;
            System.out.format("%d=>%s=>%d%n", i, romanVal, revI);
        }
    }

}
