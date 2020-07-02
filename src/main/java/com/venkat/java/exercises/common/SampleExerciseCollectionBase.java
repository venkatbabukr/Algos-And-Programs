package com.venkat.java.exercises.common;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class SampleExerciseCollectionBase implements ISampleExerciseCollection {
    
    private String collectionTitle;
    
    private List<ISampleExercise> exercises;
    
    protected SampleExerciseCollectionBase(String title, ISampleExercise ...exercises) {
        Objects.requireNonNull(title);
        Objects.requireNonNull(exercises);

        this.collectionTitle = title;
        this.exercises = Arrays.stream(exercises)
                               .filter(Objects::nonNull)
                               .collect(Collectors.toList());
    }

    @Override
    public final String collectionTitle() {
        return collectionTitle;
    }

    @Override
    public final List<ISampleExercise> collectionExercises() {
        return exercises;
    }

}
