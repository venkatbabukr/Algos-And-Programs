package com.venkat.algos.simple.stack;

class StackNode<T extends Comparable<T>> {
    private T value;
    private StackNode<T> prevPtr;
    private StackNode<T> minPtr;

    public StackNode(T val, StackNode<T> prevPtr, StackNode<T> minPtr) {
        if (val == null)
            throw new IllegalArgumentException("Null value can't be pushed into stack...");
        this.value = val;
        this.prevPtr = prevPtr;
        this.minPtr = minPtr;
    }

    public T getValue() {
        return value;
    }

    public StackNode<T> getPrevPtr() {
        return prevPtr;
    }

    public StackNode<T> getMinPtr() {
        return minPtr;
    }
    
    public String toString() {
        return String.format("%s(min=%s)", value, minPtr != null ? minPtr.value : null);
    }

}

public class StackWithGetMin<T extends Comparable<T>> {

    private StackNode<T> top;

    public void push(T val) {
        StackNode<T> newNodePrevPtr = null;
        StackNode<T> newNodeMinPtr = null;
        StackNode<T> currMinPtr = null;
        if (top != null) {
            newNodePrevPtr = top;
            currMinPtr = top.getMinPtr() != null ? top.getMinPtr() : top;
            newNodeMinPtr = currMinPtr.getValue().compareTo(val) > 0 ? null : currMinPtr;
        }
        top = new StackNode<T>(val, newNodePrevPtr, newNodeMinPtr);
    }

    public T peek() {
        return top != null ? top.getValue() : null;
    }

    public T pop() {
        if (top == null)
            throw new IllegalStateException("Stack is empty!");
        T val = top.getValue();
        top = top.getPrevPtr();
        return val;
    }
    
    public T getMin() {
        return top != null ?
                   (top.getMinPtr() != null ?
                        top.getMinPtr().getValue() : top.getValue()) : null;
    }

    public String toString() {
        StringBuilder stackStrBuilder = new StringBuilder();
        for (StackNode<T> n = top ; n != null ; n = n.getPrevPtr()) {
            stackStrBuilder.append(n).append("-->");
        }
        return stackStrBuilder.toString();
    }

    public static void main(String[] args) {

        System.out.println("Run 1");
        System.out.println("-----");

        Integer[] numbers = new Integer[] {3, 5, 2, 1, 1, -1};

        StackWithGetMin<Integer> stack = new StackWithGetMin<>();
        for (Integer x : numbers) {
            stack.push(x);
            System.out.format("Pushed %d, Stack min = %d, Stack = %s%n", x, stack.getMin(), stack);
        }
        System.out.println();
        while (stack.peek() != null) {
            Integer x = stack.pop();
            System.out.format("Popped %d, Stack min = %d, Stack = %s%n", x, stack.getMin(), stack);
        }
    }

}
