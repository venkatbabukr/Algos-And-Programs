package com.venkat.design.creational.factory.abstr;

public class LeakyBucketRateLimiterFactory implements RateLimiterFactory<LeakyBucketRateLimiter> {

	private LeakyBucketRateLimiterConfig rlConfig;
	
	public LeakyBucketRateLimiterFactory(LeakyBucketRateLimiterConfig config) {
		this.rlConfig = config;
	}

	@Override
	public LeakyBucketRateLimiter newRateLimiter() {
		return new LeakyBucketRateLimiter(rlConfig.getBucketCapacity());
	}

}
