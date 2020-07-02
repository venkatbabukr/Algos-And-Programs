package com.venkat.java8.util;

import java.util.Arrays;

import com.venkat.java.exercises.common.ExercisesData;
import com.venkat.java.exercises.common.SampleExerciseBase;

/**
 * Note: java.util.Objects is available @since 1.7, adding here just for
 *       understanding purposes...
 * @author vbkomarl
 */
public class Exercise6Arrays extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "Java8 Arrays Enhancements (java.util.Arrays)";
    
    public Exercise6Arrays() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {

        Integer[] copyOfExercisesData = Arrays.copyOf(ExercisesData.ALL_INTEGERS_ARRAY, ExercisesData.ALL_INTEGERS_ARRAY.length);

        //1. Arrays.parallelPrefix()
        Arrays.parallelPrefix(copyOfExercisesData, (left, right) -> left * right);
        printfln("1. Arrays.parallelPrefix() multiply elements: %s",
                Arrays.toString(copyOfExercisesData));

        // 2. Arrays.parallelSort()...
        Arrays.parallelSort(copyOfExercisesData);
        printfln("2. Arrays.parallelSort(): %s",
                 Arrays.toString(copyOfExercisesData));
        
        // 3. Arrays.set() - set all squares by position
        Arrays.setAll(copyOfExercisesData, i -> i * i);
        printfln("3. Arrays.set() set all squares by position: %s",
                 Arrays.toString(copyOfExercisesData));

        // 4. Arrays.stream()
        printfln("4. Arrays.stream(): %s",
                 Arrays.stream(ExercisesData.ALL_INTEGERS_ARRAY));

    }
    
    public static void main(String[] args) {
        (new Exercise6Arrays()).executeExercise();
    }

}
