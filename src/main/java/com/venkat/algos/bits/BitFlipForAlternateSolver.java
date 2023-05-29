package com.venkat.algos.bits;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.IntStream;

/**
 * The Problem is to find number of bit flips required & also the bit flips sequence in a binary string to 
 * make it match one of the alternate - "01" or "10"
 * <br><br>
 * Examples:<br>
 *    1. getAllFlips("1000") = ["1010"] // Just one flip required in position 2 - Flip 0 to 1<br>
 *    2. getAllFlips("1001") = ["1011", "1010"] // Two flips required in positions 2 & 3
 *
 * @author venkat
 */
public class BitFlipForAlternateSolver {

    /**
     * Only two possible alternate sequences:
     * ZERO_ONE = "01"
     * ONE_ZERO = "10"
     * 
     * @author venkat
     */
    static enum AlternateSequenceTemplate {
        ZERO_ONE(new String(new char[] { BitStringUtils.ZERO_CHAR, BitStringUtils.ONE_CHAR })),
        ONE_ZERO(new String(new char[] { BitStringUtils.ONE_CHAR, BitStringUtils.ZERO_CHAR }));

        private String templateStr;
        
        public String getTemplateStr() {
            return templateStr;
        }

        public String toString() {
            return String.format("[%s]", templateStr);
        }

        private AlternateSequenceTemplate(String template) {
            this.templateStr = template;
        }
    }

    private String bitStr;
    
    public BitFlipForAlternateSolver(String bitStr) {
        BitStringUtils.validateBitStr(bitStr);
        this.bitStr = bitStr;
    }
    
    /**
     * The logic here is - to find all positions where bitStr[pos] != templateStr[pos % 2], because templateStr is always two bits string...
     * <br><br>
     * The way we find is also - no need of if condition for matching...
     * Instead XOR(bitStr[pos], templateStr[pos % 2]) and sum up all positions where XOR is 1 (i.e. bits don't match)
     * 
     * @param matchTemplate - One of the match template {@link AlternateSequenceTemplate#ZERO_ONE} or {@link AlternateSequenceTemplate#ONE_ZERO}
     * 
     * @return Count of positions where the bits don't match with template = Count of flips required...
     */
    private int getFlipCountForTemplateMatch(AlternateSequenceTemplate matchTemplate) {
        char[] bitStrArray = bitStr.toCharArray();
        char[] templateStrArray = matchTemplate.getTemplateStr().toCharArray();
        return IntStream.range(0, bitStr.length())
                   .map(pos -> bitStrArray[pos] ^ templateStrArray[pos % 2]) // These are all the flip positions
                                                                             // where bitStrArray[pos] ^ templateStrArray[pos % 2] = 1
                                                                             // i.e. bitStrArray[pos] doesn't match templateStrArray[pos % 2]
                   .sum();
    }

    /**
     * Only first flipCount for the match template {@link AlternateSequenceTemplate#ZERO_ONE} has to be calculated
     * <br>
     * flipCount for the other template {@link AlternateSequenceTemplate#ONE_ZERO} = bitStr.length() - above flipCount
     * 
     * @return    {@link TreeMap} of flipCount required to the matching {@link AlternateSequenceTemplate}
     */
    public TreeMap<Integer, AlternateSequenceTemplate> getFlipCountWithTemplate() {
        TreeMap<Integer, AlternateSequenceTemplate> flipCountMap = new TreeMap<>();
        int flipsRequiredForZeroOneMatch = getFlipCountForTemplateMatch(AlternateSequenceTemplate.ZERO_ONE);
        flipCountMap.put(flipsRequiredForZeroOneMatch, AlternateSequenceTemplate.ZERO_ONE);
        flipCountMap.put(bitStr.length() - flipsRequiredForZeroOneMatch, AlternateSequenceTemplate.ONE_ZERO);
        return flipCountMap;
    }

    /**
     * Returns the minimum flips required - Equal to the first key/first entry of the TreeMap that {@link BitFlipForAlternateSolver#getFlipCountWithTemplate()}
     * returns...
     * 
     * @return The minimum flips required to match given bit string into one of the two alternating templates
     */
    public int getMinRequiredFlipCount() {
        return getFlipCountWithTemplate().firstKey();
    }

    /**
     * Returns the list of all flips that have to be done...
     * 
     * @return {@link List} of all flips that are done to match given string to the nearest/closest {@link AlternateSequenceTemplate}
     */
    public List<String> getAllFlips() {
        TreeMap<Integer, AlternateSequenceTemplate> flipCountMap = getFlipCountWithTemplate();
        Entry<Integer, AlternateSequenceTemplate> minFlipsEntry = flipCountMap.firstEntry();
        int requiredFlipsCount = minFlipsEntry.getKey();
        List<String> flipsList = new ArrayList<>(requiredFlipsCount);
        if (requiredFlipsCount > 0) {
            char[] bitStrArr = bitStr.toCharArray();
            char[] flipTemplateArr = minFlipsEntry.getValue().getTemplateStr().toCharArray();
            
            int[] flipPositions = IntStream.range(0, bitStrArr.length)
                                      .filter(idx -> (bitStrArr[idx] ^ flipTemplateArr[idx %2]) > 0)
                                      .toArray();
            for (int pos : flipPositions) {
                BitStringUtils.flipBitAtPos(bitStrArr, pos); // This call roughly translates to this code: bitStrArr[pos] = (char) ((bitStrArr[pos] ^ '1') + '0');
                flipsList.add(new String(bitStrArr));
            }
        }
        return flipsList;
    }

    public static void main(String[] args) {
        int numBits = 4;
        for (int i = 0 ; i < (1 << numBits) ; i++) {
            String bitStr = BitStringUtils.toBinaryString(i, numBits);
            System.out.format("%s: %s\n", bitStr, new BitFlipForAlternateSolver(bitStr).getAllFlips());
        }
    }

}
