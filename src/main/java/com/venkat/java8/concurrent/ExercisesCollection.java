package com.venkat.java8.concurrent;

import com.venkat.java.exercises.util.SampleExerciseCollectionBase;

public class ExercisesCollection extends SampleExerciseCollectionBase {

    public static final String COLLECTION_TITLE = "Java8 Concurrency";

    public ExercisesCollection() {
        super(COLLECTION_TITLE, new Exercise1CompletableFuture());
    }

    public static void main(String[] args) {
        (new ExercisesCollection()).executeCollection();
    }

}
