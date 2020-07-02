package com.venkat.java.exercises;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import com.venkat.java.exercises.common.ISampleExerciseCollection;

public class ExercisesReadMeGenerator {

    public static void main(String[] args) {
        List<ISampleExerciseCollection> exercisesCollection = Arrays.asList(
                new com.venkat.java8.functional.ExercisesCollection(),
                new com.venkat.java8.streams.ExercisesCollection(),
                new com.venkat.java8.streams.collectors.ExercisesCollection(),
                new com.venkat.java8.concurrent.ExercisesCollection(),
                new com.venkat.java8.temporal.ExercisesCollection(),
                new com.venkat.java8.util.ExercisesCollection());

        exercisesCollection.forEach(coll -> {
            try {
                coll.generateReadme();
            } catch (IOException | URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

    }

}
