package com.venkat.java.exercises.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
        String readMeFilePath = new StringJoiner("/")
                                    .add("src/main/java")
                                    .add(this.getClass().getPackage().getName().replaceAll("\\.", "/"))
                                    .add("README.md").toString();
        File readMeFile = new File(Paths.get("", readMeFilePath).toUri());
        if (!readMeFile.exists()) {
            readMeFile.createNewFile();
        }
        try (PrintWriter fout = new PrintWriter(readMeFile);) {
            fout.println(ExercisesUtil.getFormattedExerciseCollectionTitle(collectionTitle()));
            List<ISampleExercise> exercisesList = collectionExercises();
            fout.println(IntStream.range(1, exercisesList.size() + 1)
                                        .boxed()
                                        .map(idx -> {
                                            ISampleExercise exercise = exercisesList.get(idx - 1);
                                            return String.format("%d. [%s](%s.java)", idx, exercise.exerciseTitle(), exercise.getClass().getSimpleName());
                                        })
                                        .collect(Collectors.joining(System.lineSeparator())));
        }
    }

    default void executeCollection() {
        System.out.println(ExercisesUtil.getFormattedExerciseCollectionTitle(collectionTitle()));
        collectionExercises()
            .forEach(exercise -> {
                exercise.executeExercise();
                System.out.println();
            });
    }

}
