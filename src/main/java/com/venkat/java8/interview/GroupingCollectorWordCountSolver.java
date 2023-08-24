package com.venkat.java8.interview;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.venkat.java.exercises.common.SampleExerciseBase;
import com.venkat.java8.nio.PathUtils;

public class GroupingCollectorWordCountSolver extends SampleExerciseBase {

    public static final class SolverConstants {
        
        private SolverConstants() { }
        
        public static final String WHITE_SPACE_PATTERN = "\\s";
        public static final String PUNCTUATION_PATTERN = "\\p{Punct}";
        public static final String EMPTY_STR = "";
    }
    
    public static final String EXERCISE_TITLE = "Word Count Solver";
    
    public GroupingCollectorWordCountSolver() {
        super(EXERCISE_TITLE);
    }

    private Map<String, Long> getWordCount(Stream<String> linesStream) {
        // flatMap is required so that within each line, the words are split!
        return linesStream
                   .flatMap(line -> Arrays.stream(line.split(SolverConstants.WHITE_SPACE_PATTERN)))
                   .map(word -> word.replaceAll(SolverConstants.PUNCTUATION_PATTERN, SolverConstants.EMPTY_STR))
                   .filter(word -> word.length() > 0)
                   .map(word -> word.toLowerCase())
                   .collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()));
    }

    public Map<String, Long> getWordCount(List<String> lines) {
        return Optional.ofNullable(lines)
                   .map(linesList -> getWordCount(linesList.stream()))
                   .orElse(null);
    }

    public Map<String, Long> getWordCount(String linesFile) {
        try {
            return getWordCount(Files.lines(PathUtils.getResourcePath(linesFile)));
        } catch (IOException | URISyntaxException e) {
            System.out.println("Got exception! " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void exerciseOutput() {
        printfln("1. Simple words count in inputs/WordCountSolverInputFile.txt  = %s", this.getWordCount("inputs/WordCountSolverInputFile.txt"));
        printfln("2. Words count inside poem inputs/JackAndJillPoem.txt = %s", this.getWordCount("inputs/JackAndJillPoem.txt"));
    }

    public static void main(String[] args) {
        (new GroupingCollectorWordCountSolver()).executeExercise();
    }

}
