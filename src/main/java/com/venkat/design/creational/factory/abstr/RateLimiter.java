package com.venkat.design.creational.factory.abstr;

public interface RateLimiter {
	
	boolean permitRequest(int requestId);
	
	void requestCompleted(int requestId);

}
