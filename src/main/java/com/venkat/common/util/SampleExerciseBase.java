package com.venkat.common.util;

public abstract class SampleExerciseBase implements ISampleExercise {
    
    private String exerciseTitle;

    protected SampleExerciseBase(String title) {
        this.exerciseTitle = title;
    }

    @Override
    public String exerciseTitle() {
        return exerciseTitle;
    }

}
