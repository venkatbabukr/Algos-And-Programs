package com.venkat.algos.simple;

/**
 * Hacker rank problem - https://www.hackerrank.com/challenges/kangaroo/problem
 * 
 * The solution can be obtained mathematically solving for two line equations:
 * 
 * k1's line equation y = x1 + v1 * x
 * K2's line equation y = x2 + v2 * x
 * 
 * At the point of intersection (xint, yint)
 * 
 * x1 + v1 * xint = x2 + v2 * xint
 * 
 * => xint = (x2 - x1) / (v1 - v2)
 * 
 * We just need to ensure that this xint point is > 0 & is a whole number (without any fractions...)
 * 
 * @author vbkomarl
 */
public class KangarooJumpsSolver {
    
    public boolean findIfKangaroosMeet(int x1, int v1, int x2, int v2) {
        boolean kangaroosCanMeet = v1 - v2 != 0;
        if (kangaroosCanMeet) {
            double intersectX = (x2 - x1) * 1.0 / (v1 - v2);
            kangaroosCanMeet = intersectX > 0.0 && intersectX - Math.floor(intersectX) == 0;
        }
        return kangaroosCanMeet;
    }

}
