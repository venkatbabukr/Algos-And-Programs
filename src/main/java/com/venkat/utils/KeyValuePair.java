package com.venkat.utils;

import java.util.Comparator;

import com.venkat.utils.ext.ObjectsExt;

public class KeyValuePair<K, V> {

    private K keyObj;
    private V valObj;

    public static final class Comparators {

        private Comparators() { }

        public static <K extends Comparable<K>, V> Comparator<KeyValuePair<K, V>> keyComparator() {
            return (p1, p2) -> ObjectsExt.nullSafeCompare(p1, p2, KeyValuePair::key);
        }

        public static <K, V extends Comparable<V>> Comparator<KeyValuePair<K, V>> valComparator() {
            return (p1, p2) -> ObjectsExt.nullSafeCompare(p1, p2, KeyValuePair::val);
        }

    }

    public KeyValuePair(K k, V v) {
        this.keyObj = k;
        this.valObj = v;
    }

    public K key() {
        return keyObj;
    }

    public V val() {
        return valObj;
    }

    public String toString() {
        return String.format("{%s: %s}", keyObj, valObj);
    }

}
