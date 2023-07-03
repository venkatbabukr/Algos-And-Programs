package com.venkat.java.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class NThreadPrinterUsingLocks {
	
	private int currentNumToGenerate;
	private int numThreads;
	private Lock syncLock;
	private Condition numberPrinted;
	
	public NThreadPrinterUsingLocks(int numThreads) {
		this.numThreads = numThreads;
	}
	
	public void process() {
		this.currentNumToGenerate = 0;
		this.syncLock = new ReentrantLock(true);
		this.numberPrinted = this.syncLock.newCondition();
		IntStream.range(0, numThreads)
				.boxed()
		        .forEach(num -> new Thread(new GeneratorThread(num, System.out::println)).start());
	}

	final class GeneratorThread implements Runnable {
		
		private int threadGeneratedNum;
		private Consumer<String> numberConsumer;

		public GeneratorThread(int num, Consumer<String> nc) {
			this.threadGeneratedNum = num;
			this.numberConsumer = nc;
		}
		
		public void run() {
			syncLock.lock();
			while (currentNumToGenerate != threadGeneratedNum) {
				try {
					numberPrinted.await();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
			numberConsumer.accept(String.format("%2$s => Num %1$d", threadGeneratedNum, Thread.currentThread()));
			currentNumToGenerate++;
			// You have to signalAll so that the next thread that has to print number
			// should be made available. Otherwise, we'll enter dead-lock...
			numberPrinted.signalAll();
			syncLock.unlock();
		}
	}
	
	public static void main(String[] args) {
		
		new NThreadPrinterUsingLocks(10).process();

	}

}
