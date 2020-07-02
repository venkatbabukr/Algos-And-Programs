package com.venkat.java8.util;

import java.util.Arrays;
import java.util.Objects;

import com.venkat.java.exercises.common.ExercisesData;
import com.venkat.java.exercises.common.SampleExerciseBase;

/**
 * Note: java.util.Objects is available @since 1.7, adding here just for
 *       understanding purposes...
 * @author vbkomarl
 */
public class Exercise5Objects extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "Java8 Objects (java.util.Objects)";
    
    public Exercise5Objects() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {

        // 1. equals() comparing null with string...
        printfln("1. equals() comparing null with string...: %s",
                 Objects.equals("str1", null));

        // 2. deepEquals() comparing Arrays...
        printfln("2. deepEquals() comparing Arrays...%s: %s",
                 Arrays.toString(ExercisesData.ALL_INTEGERS_ARRAY),
                 Objects.deepEquals(ExercisesData.ALL_INTEGERS_ARRAY,
                                    ExercisesData.ALL_INTEGERS_ARRAY));

        // 3. Computing hash() with varags
        printfln("3. Computing hash() with varags 1, 2, 3: %s",
                 Objects.hash(1, 2, 3));

        // 4. requireNotNull() getting exception
        try {
            Objects.requireNonNull(null);
        } catch (NullPointerException npe) {
            printfln("4. requireNotNull() getting exception: %s", npe.getMessage());
        }

        // 5. requireNotNull() with custom message
        try {
            Objects.requireNonNull(null, "My custom null message");
        } catch (NullPointerException npe) {
            printfln("5. requireNotNull() getting exception: %s", npe.getMessage());
        }
    }
    
    public static void main(String[] args) {
        (new Exercise5Objects()).executeExercise();
    }

}
