package com.venkat.java.threads;

/*
 * Bealdung link: https://www.baeldung.com/java-even-odd-numbers-with-2-threads
 */
public class OddEvenThreadPrinter {
    
    static class Printer {
        private Boolean oddTurn = Boolean.TRUE;
        
        public synchronized void printOdd(int oddNumber) {
            while (!oddTurn)
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            System.out.format("%s: %d\n", Thread.currentThread().getName(), oddNumber);
            oddTurn = Boolean.FALSE;
            notifyAll();
        }
        
        public synchronized void printEven(int evenNumber) {
            while (oddTurn)
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            System.out.format("%s: %d\n", Thread.currentThread().getName(), evenNumber);
            oddTurn = Boolean.TRUE;
            notifyAll();
        }
    }
    
    public static void main(String[] args) {
        int maxNumToPrint = 10;
        Printer sharedPrinter = new Printer();
        
        Thread oddWorkerThread = new Thread(new Runnable() {
            
            @Override
            public void run() {
                for (int num = 1 ; num <= maxNumToPrint; num += 2) {
                    sharedPrinter.printOdd(num);
                }
            }
        }, "oddWorker");

        Thread evenWorkerThread = new Thread(new Runnable() {
            
            @Override
            public void run() {
                for (int num = 2 ; num <= maxNumToPrint; num += 2) {
                    sharedPrinter.printEven(num);
                }
            }
        }, "evenWorker");
        
        oddWorkerThread.start();
        evenWorkerThread.start();

    }

}
