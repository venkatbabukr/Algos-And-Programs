package com.venkat.algos.hacker;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

import com.venkat.utils.ArraysExt;

/**
 * Question video: https://www.youtube.com/watch?v=ICdGWdv9EdI
 * 
 * @author vbkomarl
 *
 */
public class ProcessorTasksAssigner {

    public int findFastestCompletionTime(Integer[] taskExecutionTimes, int[] processorStartTimes, int numCoresPerProcessor) {
        if (ArraysExt.isEmpty(taskExecutionTimes) || ArraysExt.isEmpty(processorStartTimes))
            throw new IllegalArgumentException("Task execution times and processors array are mandatory! Can't give null or empty...");
        else if (processorStartTimes.length * numCoresPerProcessor != taskExecutionTimes.length) {
            throw new IllegalArgumentException("Number of tasks should be equal to total number of cores!");
        }
        Arrays.sort(processorStartTimes);
        Arrays.sort(taskExecutionTimes, Comparator.reverseOrder());
        return IntStream.range(0, processorStartTimes.length)
                        .boxed()
                        .map(i -> processorStartTimes[i] + taskExecutionTimes[numCoresPerProcessor * i])
                        .max(Comparator.naturalOrder())
                        .get();
    }

    public static void main(String[] args) {
        class ProcessorsTasksTestData {
            Integer[] tasksWithTime;
            int[] processorsWithStartTime;
            int numProcessorCores;

            public ProcessorsTasksTestData(Integer[] twt, int[] pwst, int nCores) {
                this.tasksWithTime = twt;
                this.processorsWithStartTime = pwst;
                this.numProcessorCores = nCores;
            }
        }
        
        ProcessorsTasksTestData[] allTestCases = new ProcessorsTasksTestData[] {
            new ProcessorsTasksTestData(new Integer[] { 2, 2, 3, 1, 8, 7, 4, 5 }, new int[] {8, 10}, 4),
            new ProcessorsTasksTestData(new Integer[] { 2, 3, 1, 2, 5, 8, 4, 3, 3 }, new int[] {10, 15, 20}, 3)
        };

        ProcessorTasksAssigner assigner = new ProcessorTasksAssigner();

        for (ProcessorsTasksTestData testCase : allTestCases) {
            System.out.format("Tasks=%s, Processors=%s, Numcores=%d, Fastest execution time=%d%n",
                              Arrays.toString(testCase.tasksWithTime),
                              Arrays.toString(testCase.processorsWithStartTime),
                              testCase.processorsWithStartTime.length * testCase.numProcessorCores,
                              assigner.findFastestCompletionTime(testCase.tasksWithTime, testCase.processorsWithStartTime, testCase.numProcessorCores));
        }
        
    }

}
