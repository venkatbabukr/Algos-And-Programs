package com.venkat.design.structural.decorator;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class WrapperLock implements Lock {
	
	private Lock wrappedLock;
	
	public WrapperLock(Lock l) {
		Objects.requireNonNull(l, "Wrapped lock can't be null");
		this.wrappedLock = l;
	}

	@Override
	public void lock() {
		wrappedLock.lock();
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		wrappedLock.lockInterruptibly();
	}

	@Override
	public boolean tryLock() {
		return wrappedLock.tryLock();
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return wrappedLock.tryLock(time, unit);
	}

	@Override
	public void unlock() {
		wrappedLock.unlock();
	}

	@Override
	public Condition newCondition() {
		return wrappedLock.newCondition();
	}

}

/**
 * The idea here is to use {@link ReentrantReadWriteLock} to support parallel and exclusive flow control locks.
 * 
 * If you want parallel flows to happen, acquire parallelFlowLock. If you want exclusive flow locking, acquire
 * exclusiveFlowLock. This is possible only when there is no parallel flows in progress and no other exclusive
 * flows are in progress.
 * 
 * @author venkateshbabukr
 */
public class ServiceFlowLock {

	private ReentrantReadWriteLock flowControlLock;
	private Lock parallelFlowLock;
	private Lock exclusiveFlowLock;
	
	public ServiceFlowLock() {
		this.flowControlLock = new ReentrantReadWriteLock();
		this.parallelFlowLock = new WrapperLock(this.flowControlLock.readLock());
		this.exclusiveFlowLock = new WrapperLock(this.flowControlLock.writeLock());
	}

	public Lock parallelFlowLock() {
		return parallelFlowLock;
	}
	
	public Lock exclusiveFlowLock() {
		return exclusiveFlowLock;
	}

}
