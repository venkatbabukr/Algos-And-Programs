package com.venkat.utils;

public class KeyValuePair<K, V> {

    private K keyObj;
    private V valObj;

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
