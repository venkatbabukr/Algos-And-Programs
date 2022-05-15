package com.venkat.utils.ext;

import java.util.function.Function;

/**
 * Just marker interface to say the function is more of a mapper!
 *
 * @author vbkomarl
 *
 * @param <T>
 * @param <R>
 */
public interface Mapper<T, R> extends Function<T, R> {

}
