package com.venkat.java8.streams.collectors;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.venkat.java8.nio.PathUtils;

public class GroupingCollectorWordCountSolver {

    private Map<String, Long> getWordCount(Stream<String> linesStream) {
        return linesStream.collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()));
    }

    public Map<String, Long> getWordCount(List<String> linesList) {
        return Optional.ofNullable(linesList)
                   .map(ll -> getWordCount(ll.stream()))
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

    public static void main(String[] args) {
        GroupingCollectorWordCountSolver solver = new GroupingCollectorWordCountSolver();
        System.out.format("Words count map = %s", solver.getWordCount("inputs/WordCountSolverInputFile.txt"));
    }

}
