package com.venkat.java8.streams.collectors;

import com.venkat.java.exercises.common.SampleExerciseCollectionBase;
import com.venkat.java8.interview.GroupByAgeExample;
import com.venkat.java8.interview.GroupingCollectorWordCountSolver;

public class ExercisesCollection extends SampleExerciseCollectionBase {

    public static final String COLLECTION_TITLE = "Java8 Stream Collectors";

    public ExercisesCollection() {
        super(COLLECTION_TITLE, new Exercise1TransformingCollectors(), new Exercise2AggregatingCollectors(),
                new Exercise3GroupingCollectors(), new Exercise4PartitioningCollectors(),
                new Exercise5AndThenCollectors(), new GroupingCollectorWordCountSolver(),
                new GroupByAgeExample());
    }

    public static void main(String[] args) {
        (new ExercisesCollection()).executeCollection();
    }

}
