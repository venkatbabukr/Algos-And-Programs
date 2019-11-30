package com.venkat.algos.dp;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BracesBalancerJava8 {
    
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
        List<String> balancedCombos = null;

        Set<String> currentLevelCombos = new HashSet<>();
        currentLevelCombos.add(inputString);
        
        while (!currentLevelCombos.isEmpty()) {
            Map<Boolean, List<String>> comboPartitionMap = currentLevelCombos
                                                               .stream()
                                                               .collect(Collectors.partitioningBy(str -> isBalanced(str)));
            balancedCombos = comboPartitionMap.get(Boolean.TRUE);
            if (balancedCombos != null && balancedCombos.size() > 0) {
                // Balanced combos found!
                currentLevelCombos.clear();
            } else {
                currentLevelCombos = comboPartitionMap.get(Boolean.FALSE)
                                         .parallelStream()
                                         .map(str -> {
                                             Set<String> nextLevelCombos = new HashSet<>();
                                             for (int i = 0 ; i < str.length() ; i++) {
                                                 switch (str.charAt(i)) {
                                                     case OPEN_BRACE:
                                                     case CLOSED_BRACE:
                                                         String nextLevelStr = str.substring(0, i).concat(str.substring(i+1));
                                                         nextLevelCombos.add(nextLevelStr);
                                                 }
                                              }
                                              return nextLevelCombos;
                                         })
                                         .flatMap(s -> s.stream())
                                         .collect(Collectors.toSet());
            }
        }
        return new HashSet<>(balancedCombos);
    }
    
    public static void main(String[] args) {
        String[] imbalancedCases = new String[] {"((A())", "())((()", "())(", "())()("};
        BracesBalancerJava8 balancer = new BracesBalancerJava8();
        for (String str : imbalancedCases) {
            System.out.format("Balanced set for %s: %s\n", str, balancer.balanceWithMinimumRemoval(str));
        }
    }

}
