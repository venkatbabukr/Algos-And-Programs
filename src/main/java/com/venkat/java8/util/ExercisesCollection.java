package com.venkat.java8.util;

import com.venkat.java.exercises.common.SampleExerciseCollectionBase;

public class ExercisesCollection extends SampleExerciseCollectionBase {

    public static final String COLLECTION_TITLE = "Java8 Stream Collectors";

    public ExercisesCollection() {
        super(COLLECTION_TITLE, new Exercise1Optional(), new Exercise2StringJoiner(), new Exercise3Base64EncodeDecode(),
                new Exercise4ComparatorEnhancements(), new Exercise5Objects(), new Exercise6Arrays());
    }

    public static void main(String[] args) {
        (new ExercisesCollection()).executeCollection();
    }

}
