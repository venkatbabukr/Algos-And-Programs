package com.venkat.java.threads;

import java.util.stream.IntStream;

public class NThreadPrinter {

    static final class Printer {

        private int currentAllowedThread;
        
        public Printer() {
            currentAllowedThread = 0;
        }
        
        public synchronized void print(int num, int workerThreadNum, int totalWorkersCount) {
            while (currentAllowedThread != workerThreadNum) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.format("Thread%d: %d\n", workerThreadNum, num);
            currentAllowedThread = (currentAllowedThread + 1) % totalWorkersCount;
            notifyAll();
        }
        
    }
    
    public static void main(String[] args) {
        final int maxNumToPrint = 10;
        final int numThreads = 3;
        
        final Printer sharedPrinter = new Printer();
        
        IntStream.range(0, numThreads)
            .forEach(threadNum -> {
                new Thread(new Runnable() {
                    
                    @Override
                    public void run() {
                        for (int num = threadNum ; num <= maxNumToPrint ; num += numThreads) {
                            sharedPrinter.print(num, threadNum, numThreads);
                        }
                    }
                }).start();
            });
    }

}
