package com.venkat.algos.bits;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

/*
 * Reference: 
 * https://github.com/callicoder/java-snowflake/blob/master/src/test/java/com/callicoder/snowflake/SnowflakePerformanceTest.java
 * https://raw.githubusercontent.com/callicoder/java-snowflake/master/src/test/java/com/callicoder/snowflake/SnowflakePerformanceTest.java
 */
public class SnowflakeIDGeneratorPerformanceTest {

    @Test
    public void nextId_withSingleThread() {
        int iterations = 1000000; // 1 million

        SnowflakeIDGenerator testGenerator = new SnowflakeIDGenerator(897);
        long beginTimestamp = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            testGenerator.nextId();
        }
        long endTimestamp = System.currentTimeMillis();

        long cost = (endTimestamp - beginTimestamp);
        long costMs = iterations/cost;
        System.out.println("Single Thread:: IDs per ms: " + costMs);
    }

    @Test
    public void nextId_withMultipleThreads() throws InterruptedException {
        int iterations = 1000000; // 1 million
        int numThreads = 50;

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);

        SnowflakeIDGenerator testGenerator = new SnowflakeIDGenerator(897);

        long beginTimestamp = System.currentTimeMillis();
        for(int i = 0; i < iterations; i++) {
            executorService.submit(() -> {
                testGenerator.nextId();
                latch.countDown();
            });
        }

        latch.await();
        long endTimestamp = System.currentTimeMillis();
        long cost = (endTimestamp - beginTimestamp);
        long costMs = iterations/cost;
        System.out.println(numThreads + " Threads:: IDs per ms: " + costMs);
    }

}
