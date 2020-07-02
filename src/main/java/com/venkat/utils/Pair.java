package com.venkat.utils;

public class Pair<T> {
    T x;
    T y;
    
    public Pair(T first, T second) {
        this.x = first;
        this.y = second;
    }
    
    public T getX() {
        return x;
    }
    
    public T getY() {
        return y;
    }
    
    public String toString() {
        return String.format("{%s, %s}", x, y);
    }

}
