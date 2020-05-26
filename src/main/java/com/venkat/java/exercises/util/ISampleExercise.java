package com.venkat.java.exercises.util;

public interface ISampleExercise {

    String exerciseTitle();

    void exerciseOutput();

    default void executeExercise() {
        System.out.println(ExercisesUtil.getFormattedExerciseTitle(exerciseTitle()));
        exerciseOutput();
    }

}
