package com.venkat.java8.util;

import com.venkat.java.exercises.util.SampleExerciseCollectionBase;

public class ExercisesCollection extends SampleExerciseCollectionBase {

    public static final String COLLECTION_TITLE = "Java8 Stream Collectors";

    public ExercisesCollection() {
        super(COLLECTION_TITLE, new Exercise1Optional(), new Exercise2StringJoiner());
    }

    public static void main(String[] args) {
        (new ExercisesCollection()).executeCollection();
    }

}
