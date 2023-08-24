package com.venkat.java8.streams.primitive;

import java.util.stream.IntStream;

import com.venkat.java.exercises.common.SampleExerciseBase;

public class Exercise1IntStream extends SampleExerciseBase {

	public static final String EXERCISE_TITLE = "Primitive IntStream";

	protected Exercise1IntStream() {
		super(EXERCISE_TITLE);
	}

	@Override
	public void exerciseOutput() {
		int begin = 0, end = 10;
		printfln("1. rangeSummaryStatistics(%d, %d)=%s", begin, end,
					IntStream.range(begin, end).summaryStatistics());
		printfln("2. rangeClosedSummaryStatistics(%d, %d)=%s", begin, end,
					IntStream.rangeClosed(begin, end).summaryStatistics());

	}

	public static void main(String[] args) {
		new Exercise1IntStream().executeExercise();
	}
}
