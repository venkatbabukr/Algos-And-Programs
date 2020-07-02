package com.venkat.java8.temporal;

import com.venkat.java.exercises.common.SampleExerciseCollectionBase;

public class ExercisesCollection extends SampleExerciseCollectionBase {

    public static final String COLLECTION_TITLE = "Java8 temporal package";

    public ExercisesCollection() {
        super(COLLECTION_TITLE, new Exercise1TemporalUnit(), new Exercise2TemporalField(),
                new Exercise3TemporalAmount(), new Exercise4TemporalAdjuster(), new Exercise5TemporalAccessor(),
                new Exercise6Temporal());
    }

    public static void main(String[] args) {
        (new ExercisesCollection()).executeCollection();
    }

}
