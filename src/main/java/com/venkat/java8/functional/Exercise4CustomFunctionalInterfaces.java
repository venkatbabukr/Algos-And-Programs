package com.venkat.java8.functional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.venkat.java.exercises.common.SampleExerciseBase;

public class Exercise4CustomFunctionalInterfaces extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "Custom functional Interfaces (@java.lang.FunctionalInterface)";

    @FunctionalInterface
    public static interface Exponentiator {
        long pow(int x);
    }

    public static class ExponentiatorFactory {

        public static Exponentiator getExpo(int n) {
            return (int x) -> (long) Math.pow(x, n);
        }

    }

	protected Exercise4CustomFunctionalInterfaces() {
		super(EXERCISE_TITLE);
	}

	@Override
	public void exerciseOutput() {
        int testNum = 3;
        List<Long> exponentiators = IntStream.range(0, 5)
                                                 .boxed()
                                                 .map(n -> ExponentiatorFactory.getExpo(n))
                                                 .map(e -> (Supplier<Long>) () -> e.pow(testNum))
                                                 .map(CompletableFuture::supplyAsync)
                                                 .map(CompletableFuture::join)
                                                 .collect(Collectors.toList());
       printfln("For test number %d, all exponents: %s", testNum, exponentiators);
	}

}
