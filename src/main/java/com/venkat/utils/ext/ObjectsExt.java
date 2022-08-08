package com.venkat.utils.ext;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class ObjectsExt {

    public static boolean nullSafeEquals(Object o1, Object o2) {
        return o1 == null ? o2 == null : o1.equals(o2);
    }

    public static <T, V> boolean nullSafeEquals(T v1, T v2, Function<T, V> compareValExtractor) {
        return v1 == null || v2 == null ?
                  v1 == v2 :
                  nullSafeEquals(compareValExtractor.apply(v1), compareValExtractor.apply(v2));
    }

    public static <T extends Comparable<T>> int nullSafeCompare(T v1, T v2) {
        return Optional.ofNullable(v1)
                       .map(val1 -> val1.compareTo(v2))
                       .orElse(v2 == null ? 0 : -1);
    }

    public static <T, V extends Comparable<V>> int nullSafeCompare(T v1, T v2, Function<T, V> compareValExtractor) {
        return v1 != null && v2 != null ? nullSafeCompare(compareValExtractor.apply(v1), compareValExtractor.apply(v2)) :
                   v1 != null ? 1 : v2 != null ? -1 : 0;
    }

    @SafeVarargs
    public static <T extends Comparable<T>> T nullSafeMin(T... vals) throws IllegalArgumentException {
        return Optional.ofNullable(vals)
                       .flatMap(valsArr -> Arrays.stream(valsArr).filter(Objects::nonNull).min((x, y) -> x.compareTo(y)))
                       .orElse(null);
    }

}
