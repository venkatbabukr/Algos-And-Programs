package com.venkat.utils;

import java.util.Optional;

public class BooleanUtils {

    private BooleanUtils() { }

    public static int toInt(boolean b) {
    	return b ? 1 : 0;
    }

    public static Integer toInt(Boolean b) {
    	return Optional.ofNullable(b)
                   .map(BooleanUtils::toInt)
                   .orElse(null);
    }

}
