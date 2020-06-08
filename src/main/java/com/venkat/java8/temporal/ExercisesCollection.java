package com.venkat.java8.temporal;

import com.venkat.java.exercises.util.SampleExerciseCollectionBase;

public class ExercisesCollection extends SampleExerciseCollectionBase {

    public static final String COLLECTION_TITLE = "Java8 temporal package";

    public ExercisesCollection() {
        super(COLLECTION_TITLE, new Exercise1TemporalUnit(), new Exercise2TemporalField(),
                new Exercise4TemporalAdjuster());
    }

    public static void main(String[] args) {
        (new ExercisesCollection()).executeCollection();
    }

}