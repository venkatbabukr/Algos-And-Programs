package com.venkat.algos.simple.stack;

import java.util.Arrays;

public class StackWithGetMinGFG {
    
    private int[] stackArr;
    private int stackTop;
    private Integer currMin;
    
    public StackWithGetMinGFG(int stackMaxSize) {
        this.stackArr = new int[stackMaxSize];
        this.stackTop = 0;
        this.currMin = null;
    }
    
    public void push(int n) {
        if (stackTop >= stackArr.length)
            throw new IllegalStateException("Maximum stack size exceeded!!!");
        int pushNum = n;
        if (currMin == null || n < currMin) {
            pushNum = currMin != null ? n + n - currMin : n;
            currMin = n;
        }
        stackArr[stackTop++] = pushNum;
    }
    
    public int pop() {
        if (stackTop > 0) {
            int popNum = stackArr[--stackTop];
            if (popNum < currMin) {
                Integer newCurrMin = null;
                if (stackTop > 1) {
                	newCurrMin = currMin + currMin - popNum;
                } else if (stackTop == 1) {
                	newCurrMin = stackArr[0];
                }
                popNum = currMin;
                currMin = newCurrMin;
            }
            return popNum;
        } else {
            throw new IllegalStateException("Empty stack! Can't pop anymore...");
        }
    }
    
    public int getMin() {
        return currMin;
    }
    
    public String toString() {
        return String.format("Stack: %s, Top: %d, Min: %d", Arrays.toString(stackArr), stackTop, currMin);
    }

}
