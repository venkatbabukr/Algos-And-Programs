package com.venkat.utils.ext;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class ObjectsExt {

    public static boolean nullSafeEquals(Object o1, Object o2) {
        return o1 == null ? o2 == null : o1.equals(o2);
    }

    public static <T extends Comparable<T>> int nullSafeCompare(T v1, T v2) {
        return Optional.ofNullable(v1)
                       .map(val1 -> val1.compareTo(v2))
                       .orElse(v2 == null ? 0 : -1);
    }

	@SafeVarargs
	public static <T extends Comparable<T>> T nullSafeMin(T... vals) throws IllegalArgumentException {
	    return Optional.ofNullable(vals)
	                   .flatMap(valsArr -> Arrays.stream(valsArr).filter(Objects::nonNull).min((x, y) -> x.compareTo(y)))
	                   .orElse(null);
	}

}
