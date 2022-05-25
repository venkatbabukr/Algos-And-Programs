package com.venkat.utils.ext;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class ObjectsExt {

    public static boolean nullSafeEquals(Object o1, Object o2) {
        return o1 == null ? o2 == null : o1.equals(o2);
    }

	@SafeVarargs
	public static <T extends Comparable<T>> T nullSafeMin(T... vals) throws IllegalArgumentException {
	    return Optional.ofNullable(vals)
	                   .flatMap(valsArr -> Arrays.stream(valsArr).filter(Objects::nonNull).min((x, y) -> x.compareTo(y)))
	                   .orElse(null);
	}

}
