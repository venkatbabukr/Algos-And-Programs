package com.venkat.java8.util;

import java.util.StringJoiner;

import com.venkat.java.exercises.util.SampleExerciseBase;

public class Exercise2StringJoiner extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "Java8 StringJoiner (java.util.StringJoiner)";
    
    public Exercise2StringJoiner() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        String delim = "~";
        //1. Simple join operation
        System.out.format("1. Simple join operation using delim %s: %s\n", delim,
                          new StringJoiner(delim)
                              .add("First")
                              .add("Second")
                              .add("Third")
                              .toString());

        //2. Simple join with prefix & suffix
        String prefix = "<pre>";
        String suffix = "</pre>";
        System.out.format("2. Simple join operation using prefix %s, suffix %s, delim %s: %s\n", prefix, suffix, delim,
                          new StringJoiner(delim, prefix, suffix)
                              .add("First")
                              .add("Second")
                              .add("Third")
                              .toString());


        //3. Length with prefix & suffix
        StringJoiner joiner = new StringJoiner(delim, prefix, suffix)
                                  .add("First")
                                  .add("Second")
                                  .add("Third");
        System.out.format("2. Length with prefix %s, suffix %s, delim %s: %s[%d]\n", prefix, suffix, delim, joiner.toString(), joiner.length());

        //4. Concat using merge()
        StringJoiner joiner2 = new StringJoiner("^")
                                   .add("1")
                                   .add("2")
                                   .add("3")
                                   .merge(joiner);
        System.out.format("4. Concat using merge(): %s[%d]\n", joiner2, joiner2.length());

    }
    
    public static void main(String[] args) {
        (new Exercise2StringJoiner()).executeExercise();
    }

}
