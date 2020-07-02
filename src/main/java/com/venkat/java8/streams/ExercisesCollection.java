package com.venkat.java8.streams;

import com.venkat.java.exercises.common.SampleExerciseCollectionBase;

public class ExercisesCollection extends SampleExerciseCollectionBase {

    public static final String COLLECTION_TITLE = "Java8 Streams";

    public ExercisesCollection() {
        super(COLLECTION_TITLE, new Exercise1StreamTransforming(), new Exercise2StreamAggregating(),
                new Exercise3StreamFiltering(), new Exercise4StreamIterationAndPredicates(),
                new Exercise5StreamGenerationAndConcat(), new Exercise6OOTBStreams());
    }

    public static void main(String[] args) {
        (new ExercisesCollection()).executeCollection();
    }

}
