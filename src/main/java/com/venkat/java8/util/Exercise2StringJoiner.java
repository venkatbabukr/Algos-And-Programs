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
        String delim = "$";
        //1. Simple join operation
        printfln("1. Simple join operation using delim %s: %s", delim,
                  new StringJoiner(delim)
                      .add("First")
                      .add("Second")
                      .add("Third")
                      .toString());

        //2. Simple join with prefix & suffix
        String prefix = "<joiner1>";
        String suffix = "</joiner1>";
        printfln("2. Simple join operation using prefix %s, suffix %s, delim %s: %s", prefix, suffix, delim,
                  new StringJoiner(delim, prefix, suffix)
                      .add("First")
                      .add("Second")
                      .add("Third")
                      .toString());


        //3. Length with prefix & suffix
        StringJoiner joiner1 = new StringJoiner(delim, prefix, suffix)
                                  .add("First")
                                  .add("Second")
                                  .add("Third");
        printfln("2. Length with prefix %s, suffix %s, delim %s: %s[%d]", prefix, suffix, delim, joiner1.toString(), joiner1.length());

        //4. Concat using merge()
        StringJoiner joiner2 = new StringJoiner("^", "<joiner2>", "</joiner2>")
                                   .add("1")
                                   .add("2")
                                   .add("3")
                                   .merge(joiner1);
        printfln("4. Concat using merge(): %s[%d]", joiner2, joiner2.length());

    }
    
    public static void main(String[] args) {
        (new Exercise2StringJoiner()).executeExercise();
    }

}
