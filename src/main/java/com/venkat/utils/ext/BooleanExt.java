package com.venkat.utils.ext;

import java.util.Optional;

public class BooleanExt {

    private BooleanExt() { }

    public static int toInt(boolean b) {
    	return b ? 1 : 0;
    }

    public static Integer toInt(Boolean b) {
    	return Optional.ofNullable(b)
                   .map(bObj -> BooleanExt.toInt(bObj.booleanValue()))
                   .orElse(null);
    }

}
