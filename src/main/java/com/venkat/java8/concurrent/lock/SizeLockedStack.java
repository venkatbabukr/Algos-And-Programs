package com.venkat.java8.concurrent.lock;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SizeLockedStack<T> {
	
	private Stack<T> stack;
	private int capacity;
	private Lock stackLock;
	private Condition elementPopped;
	private Condition elementPushed;

	public SizeLockedStack(Stack<T> s, int capacity) {
		this.stack = s;
		this.capacity = capacity;
		this.stackLock = new ReentrantLock();
		this.elementPopped = stackLock.newCondition();
		this.elementPushed = stackLock.newCondition();
	}
	
	public T push(T element) throws InterruptedException {
		try {
			stackLock.lock();
			while (stack.size() == capacity) {
				elementPopped.await();
			}
			T returnVal = stack.push(element);
			elementPushed.signalAll();
			return returnVal;
		} finally {
			stackLock.unlock();
		}
	}

	public T pop() throws InterruptedException {
		try {
			stackLock.lock();
			while (stack.isEmpty()) {
				elementPushed.await();
			}
			elementPopped.signalAll();
			return stack.pop();
		} finally {
			stackLock.unlock();
		}
	}
	
	public T peek() {
		return stack.peek();
	}

	public boolean empty() {
		return stack.empty();
	}
	
	public int search(Object o) {
		return stack.search(o);
	}

}
