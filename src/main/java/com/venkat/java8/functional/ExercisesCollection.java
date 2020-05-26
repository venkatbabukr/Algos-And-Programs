package com.venkat.java8.functional;

import java.util.Arrays;
import java.util.List;

import com.venkat.common.util.ISampleExercise;

public class ExercisesCollection {

    public static void main(String[] args) {
        List<ISampleExercise> exercisesList = Arrays.asList(new Exercise1FunctionReferenceTypes(),
                new Exercise2OOTBFunctionalInterfaces(), new Exercise3JavaFunctionalInterfacesExtraSupport());

        for (ISampleExercise exercise : exercisesList) {
            exercise.executeExercise();
            System.out.println();
        }
    }

}
