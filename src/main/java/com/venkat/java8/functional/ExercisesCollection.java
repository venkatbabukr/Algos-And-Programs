package com.venkat.java8.functional;

import com.venkat.java.exercises.common.SampleExerciseCollectionBase;

public class ExercisesCollection extends SampleExerciseCollectionBase {

    public static final String COLLECTION_TITLE = "Java8 Functional Programming";

    public ExercisesCollection() {
        super(COLLECTION_TITLE, new Exercise1FunctionReferenceTypes(), new Exercise2OOTBFunctionalInterfaces(),
                new Exercise3JavaFunctionalInterfacesExtraSupport());
    }

    public static void main(String[] args) {
        (new ExercisesCollection()).executeCollection();
    }

}
