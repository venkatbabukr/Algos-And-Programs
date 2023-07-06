package com.venkat.design.creational.factory.abstr;

public interface RateLimiterFactory<R extends RateLimiter> {
	
	R newRateLimiter();

}
