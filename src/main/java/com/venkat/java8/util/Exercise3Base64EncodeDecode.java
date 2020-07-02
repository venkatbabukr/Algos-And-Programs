package com.venkat.java8.util;

import java.util.Arrays;
import java.util.Base64;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.venkat.java.exercises.common.SampleExerciseBase;

public class Exercise3Base64EncodeDecode extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "Java8 Base64 (java.util.Base64)";
    
    public Exercise3Base64EncodeDecode() {
        super(EXERCISE_TITLE);
    }

    @Override
    public void exerciseOutput() {
        String text = "Hello world???";
        Base64.Encoder encoder = Base64.getEncoder();

        //1. Encoding text to bytes
        printfln("1. Encoding text <%s> to bytes: %s", text,
                  Arrays.toString(encoder.encode(text.getBytes())));

        //2. Encoding text to base64 string
        printfln("2. Encoding text <%s> to base64 string: %s", text,
                  Base64.getEncoder().encodeToString(text.getBytes()));

        //3. Encoding text to base64 string without padding
        printfln("3. Encoding text <%s> to base64 string without padding: %s", text,
                  Base64.getEncoder().withoutPadding().encodeToString(text.getBytes()));

        //4. URL Encoding text
        printfln("4. URL Encoding text <%s>: %s", text,
                  Base64.getUrlEncoder().encodeToString(text.getBytes()));

        //4. MIME Encoding text
        int times = 5;
        String longText = Stream.generate(() -> text).limit(times).collect(Collectors.joining(System.lineSeparator()));
        printfln("5. MIME Encoding text <%s> repeated %d times: %s", text, times,
                  Base64.getMimeEncoder().encodeToString(longText.getBytes()));
    }
    
    public static void main(String[] args) {
        (new Exercise3Base64EncodeDecode()).executeExercise();
    }

}
