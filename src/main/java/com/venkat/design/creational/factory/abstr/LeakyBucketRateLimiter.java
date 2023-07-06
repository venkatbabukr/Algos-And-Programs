package com.venkat.design.creational.factory.abstr;

public class LeakyBucketRateLimiter implements RateLimiter {
	
	protected int bucketCapacity;
	protected int availableCapacity;

	public LeakyBucketRateLimiter(int capacity) {
		this.bucketCapacity = capacity;
		this.availableCapacity = bucketCapacity;
	}

	@Override
	public synchronized boolean permitRequest(int requestId) {
		boolean permit = availableCapacity > 0;
		if (permit) {
			availableCapacity--;
		}
		return permit;
	}

	@Override
	public synchronized void requestCompleted(int requestId) {
		availableCapacity = Math.min(availableCapacity + 1, bucketCapacity);
	}

}
