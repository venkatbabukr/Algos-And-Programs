package com.venkat.utils.ext;

public class ObjectsExt {

    public static boolean nullSafeEquals(Object o1, Object o2) {
        return o1 == null ? o2 == null : o1.equals(o2);
    }

}
