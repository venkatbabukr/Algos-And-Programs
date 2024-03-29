package com.venkat.java.exercises.common;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface ISampleExerciseCollection {

    String collectionTitle();
    
    List<ISampleExercise> collectionExercises();

    default void generateReadme() throws IOException, URISyntaxException {
        String readMeFilePath = new StringJoiner("/", "src/main/java/", "/README.md")
                                    .add(this.getClass().getPackage().getName().replaceAll("\\.", "/"))
                                    .toString();
        File readMeFile = new File(Paths.get("", readMeFilePath).toUri());
        if (readMeFile.exists() || readMeFile.createNewFile()) {
            try (PrintStream fpout = new PrintStream(readMeFile);) {
                fpout.println(ExercisesUtil.getFormattedExerciseCollectionTitle(collectionTitle()));
            
                List<ISampleExercise> exercisesList = collectionExercises();
                fpout.println(IntStream.range(1, exercisesList.size() + 1)
                                            .boxed()
                                            .map(idx -> {
                                                ISampleExercise exercise = exercisesList.get(idx - 1);
                                                return String.format("%d. [%s](%s.java)", idx, exercise.exerciseTitle(), exercise.getClass().getSimpleName());
                                            })
                                            .collect(Collectors.joining(System.lineSeparator())));
                fpout.println();
                exercisesList
                    .forEach(exercise -> {
                        exercise.executeExercise(fpout);
                        fpout.println();
                    });
            }
        } else {
            System.out.format("Failed creating/updating readme file %s%n", readMeFilePath);
        }
    }

    default void executeCollection(PrintStream pout) {
        pout.println(ExercisesUtil.getFormattedExerciseCollectionTitle(collectionTitle()));
        collectionExercises()
            .forEach(exercise -> {
                exercise.executeExercise(pout);
                pout.println();
            });
    }

    default void executeCollection() {
        executeCollection(System.out);
    }

}
