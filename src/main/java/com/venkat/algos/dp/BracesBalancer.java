package com.venkat.algos.dp;

import java.util.HashSet;
import java.util.Set;

public class BracesBalancer {
    
    private static final char OPEN_BRACE = '(';
    
    private static final char CLOSED_BRACE = ')';

    private boolean isBalanced(String str) {
        int openBraceCount = 0;
        if (str != null && str.length() > 0) {
            char[] strArray = str.toCharArray();
            for (int i = 0 ; i < strArray.length && openBraceCount > -1 ; i++) {
                char c = strArray[i];
                switch (c) {
                    case OPEN_BRACE:
                        openBraceCount++;
                        break;
                    case CLOSED_BRACE:
                        openBraceCount--;
                        break;
                }
            }
        }
        return(openBraceCount == 0);
    }

    public Set<String> balanceWithMinimumRemoval(String inputString) {
        Set<String> balancedCombos = new HashSet<>();
        Set<String> currentLevelCombos = new HashSet<>();
        currentLevelCombos.add(inputString);
        
        while (!currentLevelCombos.isEmpty()) {
            Set<String> nextLevelCombos = new HashSet<>();
            for (String str : currentLevelCombos) {
                if (isBalanced(str)) {
                    balancedCombos.add(str);
                    /*
                     * Clear nextLevelCombos, as we have found balanced str at current level...
                     * Just iterate on the remaining strs at this level to see if we can get
                     * any other combination also balanced...
                     */
                    nextLevelCombos.clear();
                } else if (balancedCombos.isEmpty()) {
                    // If balanced string is not yet found...
                    for (int i = 0 ; i < str.length() ; i++) {
                        switch (str.charAt(i)) {
                            case OPEN_BRACE:
                            case CLOSED_BRACE:
                                String nextLevelStr = str.substring(0, i).concat(str.substring(i+1));
                                nextLevelCombos.add(nextLevelStr);
                        }
                    }
                }
            }
            currentLevelCombos = nextLevelCombos;
        }
        return balancedCombos;
    }
    
    public static void main(String[] args) {
        String[] imbalancedCases = new String[] {"((A())", "())((()", "())(", "())()("};
        BracesBalancer balancer = new BracesBalancer();
        for (String str : imbalancedCases) {
            System.out.format("Balanced set for %s: %s\n", str, balancer.balanceWithMinimumRemoval(str));
        }
    }

}
