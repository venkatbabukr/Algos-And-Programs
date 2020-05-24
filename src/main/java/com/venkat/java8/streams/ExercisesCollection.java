package com.venkat.java8.streams;

import java.util.Arrays;
import java.util.List;

import com.venkat.common.util.ISampleExercise;

public class ExercisesCollection {

	public static void main(String[] args) {
		List<ISampleExercise> exercisesList = Arrays.asList(new Exercise1StreamTransforming(),
				new Exercise2StreamAggregating(), new Exercise3StreamFiltering(),
				new Exercise4StreamIterationAndPredicates(), new Exercise5StreamGeneration());

		for (ISampleExercise exercise : exercisesList) {
			exercise.run();
			System.out.println();
		}
	}

}
