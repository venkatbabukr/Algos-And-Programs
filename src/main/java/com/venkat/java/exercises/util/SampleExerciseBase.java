package com.venkat.java.exercises.util;

import java.io.PrintStream;

public abstract class SampleExerciseBase implements ISampleExercise {
    
    private String exerciseTitle;
    
    private PrintStream pout;

    public void setPrintStream(PrintStream pout) {
        this.pout = pout;
    }
    
    public PrintStream getPrintStream() {
        return this.pout;
    }

    protected SampleExerciseBase(String title) {
        this.exerciseTitle = title;
        setPrintStream(System.out);
    }

    @Override
    public String exerciseTitle() {
        return exerciseTitle;
    }

}
