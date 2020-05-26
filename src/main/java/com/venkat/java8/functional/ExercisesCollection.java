package com.venkat.java8.functional;

import java.io.IOException;
import java.net.URISyntaxException;

import com.venkat.java.exercises.util.SampleExerciseCollectionBase;

public class ExercisesCollection extends SampleExerciseCollectionBase {

    public static final String COLLECTION_TITLE = "Java8 Functional Programming";

    public ExercisesCollection() {
        super(COLLECTION_TITLE, new Exercise1FunctionReferenceTypes(), new Exercise2OOTBFunctionalInterfaces(),
                new Exercise3JavaFunctionalInterfacesExtraSupport());
    }

    public static void main(String[] args) {
        try {
            (new ExercisesCollection()).generateReadme();
        } catch (IOException | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
