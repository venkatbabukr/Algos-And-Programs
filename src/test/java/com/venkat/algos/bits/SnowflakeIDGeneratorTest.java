package com.venkat.algos.bits;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

/*
 * Refrence:
 * https://github.com/callicoder/java-snowflake/blob/master/src/test/java/com/callicoder/snowflake/SnowflakeTest.java
 * https://raw.githubusercontent.com/callicoder/java-snowflake/master/src/test/java/com/callicoder/snowflake/SnowflakeTest.java
 */
public class SnowflakeIDGeneratorTest {

    @Test
    public void nextId_shouldGenerateIdWithCorrectBitsFilled() {
        SnowflakeIDGenerator SnowflakeIDGenerator = new SnowflakeIDGenerator(784);

        long beforeTimestamp = Instant.now().toEpochMilli();

        long id = SnowflakeIDGenerator.nextId();

        // Validate different parts of the Id
        long[] attrs = SnowflakeIDGenerator.parse(id);
        assertTrue(attrs[0] >= beforeTimestamp);
        assertEquals(784, attrs[1]);
        assertEquals(0, attrs[2]);
    }

    @Test
    public void nextId_shouldGenerateUniqueId() {
        SnowflakeIDGenerator SnowflakeIDGenerator = new SnowflakeIDGenerator(234);
        int iterations = 5000;

        // Validate that the IDs are not same even if they are generated in the same ms
        long[] ids = new long[iterations];
        for(int i = 0; i < iterations; i++) {
            ids[i] = SnowflakeIDGenerator.nextId();
        }

        for(int i = 0; i < ids.length; i++) {
            for(int j = i+1; j < ids.length; j++) {
                assertFalse(ids[i] == ids[j]);
            }
        }
    }

    @Test
    public void nextId_shouldGenerateUniqueIdIfCalledFromMultipleThreads() throws InterruptedException, ExecutionException  {
        int numThreads = 50;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);

        SnowflakeIDGenerator SnowflakeIDGenerator = new SnowflakeIDGenerator(234);
        int iterations = 10000;

        // Validate that the IDs are not same even if they are generated in the same ms in different threads
        Future<Long>[] futures = new Future[iterations];
        for(int i = 0; i < iterations; i++) {
            futures[i] =  executorService.submit(() -> {
                long id = SnowflakeIDGenerator.nextId();
                latch.countDown();
                return id;
            });
        }

        latch.await();
        for(int i = 0; i < futures.length; i++) {
            for(int j = i+1; j < futures.length; j++) {
                assertFalse(futures[i].get() == futures[j].get());
            }
        }
    }
}
