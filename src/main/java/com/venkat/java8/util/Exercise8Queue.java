package com.venkat.java8.util;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import com.venkat.java.exercises.common.SampleExerciseBase;

public class Exercise8Queue extends SampleExerciseBase {

    public static final String EXERCISE_TITLE = "Java8 Queue (java.util.Queue)";

	public Exercise8Queue() {
		super(EXERCISE_TITLE);
	}

	@Override
	public void exerciseOutput() {
		Queue<Integer> testQueue = new ArrayBlockingQueue<>(1);
		printfln("Fail fast functions (That throw exceptions)");
		int num = 4;
		printfln("1. testQueue.remainingCapacity()=%d, add(%d)=%s", ((ArrayBlockingQueue) testQueue).remainingCapacity(), num, testQueue.add(num));
		try {
			testQueue.add(num);
		} catch (IllegalStateException ise) {
			printfln("2. testQueue.remainingCapacity()=%d, add(%d) failed with exception: %s(%s)", ((ArrayBlockingQueue) testQueue).remainingCapacity(), num, ise.getClass().getName(), ise.getMessage());
		}
		println();
		printfln("Test queue now: %s", testQueue);
		printfln("3. testQueue.size()=%d, testQueue.remainingCapacity()=%d, element()=%d", testQueue.size(), ((ArrayBlockingQueue) testQueue).remainingCapacity(), testQueue.element());
		printfln("4. testQueue.size()=%d, testQueue.remainingCapacity()=%d, remove()=%d", testQueue.size(), ((ArrayBlockingQueue) testQueue).remainingCapacity(), testQueue.remove());
		try {
			printfln("5. element()=%d", testQueue.element());
		} catch (NoSuchElementException nsse) {
			printfln("5. testQueue.size()=%d, testQueue.remainingCapacity()=%d, element() failed with exception: %s(%s)", testQueue.size(), ((ArrayBlockingQueue) testQueue).remainingCapacity(), nsse.getClass().getName(), nsse.getMessage());
		}
		try {
			testQueue.remove();
		} catch (NoSuchElementException nsse) {
			printfln("6. testQueue.size()=%d, testQueue.remainingCapacity()=%d, remove() failed with exception: %s(%s)", testQueue.size(), ((ArrayBlockingQueue) testQueue).remainingCapacity(), nsse.getClass().getName(), nsse.getMessage());
		}
		println();

		printfln("Fail safe functions (No exceptions)");
		num = 5;
		printfln("6. testQueue.remainingCapacity()=%d, offer(%d)=%s", ((ArrayBlockingQueue) testQueue).remainingCapacity(), num, testQueue.offer(num));
		printfln("7. testQueue.remainingCapacity()=%d, offer(%d)=%s", ((ArrayBlockingQueue) testQueue).remainingCapacity(), num, testQueue.offer(num));
		println();
		printfln("Test queue now: %s", testQueue);
		printfln("8. testQueue.size()=%d, testQueue.remainingCapacity()=%d, peek()=%d", testQueue.size(), ((ArrayBlockingQueue) testQueue).remainingCapacity(), testQueue.peek());
		printfln("9. testQueue.size()=%d, testQueue.remainingCapacity()=%d, poll()=%d", testQueue.size(), ((ArrayBlockingQueue) testQueue).remainingCapacity(), testQueue.poll());
		printfln("10. testQueue.size()=%d, testQueue.remainingCapacity()=%d, peek()=%d", testQueue.size(), ((ArrayBlockingQueue) testQueue).remainingCapacity(), testQueue.peek());
		printfln("11. testQueue.size()=%d, testQueue.remainingCapacity()=%d, poll()=%d", testQueue.size(), ((ArrayBlockingQueue) testQueue).remainingCapacity(), testQueue.poll());
	}
	
	public static void main(String[] args) {
		(new Exercise8Queue()).executeExercise();
	}

}
