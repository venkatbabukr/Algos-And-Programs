package com.venkat.utils;

import java.util.Comparator;

import com.venkat.utils.ext.ObjectsExt;

public class Pair<T> {
    T x;
    T y;

    public static final class Comparators {

        private Comparators() { }

        public static <V extends Comparable<V>> Comparator<Pair<V>> xComparator() {
            return (p1, p2) -> ObjectsExt.nullSafeCompare(p1, p2, Pair::getX);
        }

        public static <V extends Comparable<V>> Comparator<Pair<V>> yComparator() {
            return (p1, p2) -> ObjectsExt.nullSafeCompare(p1, p2, Pair::getY);
        }

    }
    
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

    @Override
    public int hashCode() {
        return (x != null ? x.hashCode() : 0) +
               (y != null ? y.hashCode() : 0);
    }

    @Override
    public boolean equals(Object other) {
        boolean equals = other instanceof Pair;
        if (equals) {
            @SuppressWarnings("unchecked")
            Pair<T> otherPair = (Pair<T>) other;
            equals &= (x == null ? otherPair.x == null : x.equals(otherPair.x)) &&
                       (y == null ? otherPair.y == null : y.equals(otherPair.y));
        }
        return equals;
    }

}
