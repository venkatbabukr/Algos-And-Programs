package com.venkat.utils.ext;

import java.util.Optional;
import java.util.function.Supplier;

import com.venkat.utils.Pair;

public final class NumbersExt {
	
	private NumbersExt() {}

	public static final Pair<Integer> decimalRange(int n) {
		int numDigits = String.valueOf(n).length();
		int lowerDecimal = (int) Math.pow(10, Math.max((numDigits - 1), 0));
		int upperDecimal = (int) Math.pow(10, numDigits);
		return new Pair<>(lowerDecimal, upperDecimal);
	}

	public static final Pair<Integer> decimalRange(Number n) {
		return decimalRange((int) Optional.ofNullable(n).map(Number::intValue).orElse(0));
	}

	public static final Pair<Integer> decimalRange(Supplier<Number> numberSupplier) {
		return decimalRange(Optional.ofNullable(numberSupplier)
								.map(s -> s.get().intValue())
								.orElse(0));
	}

}
