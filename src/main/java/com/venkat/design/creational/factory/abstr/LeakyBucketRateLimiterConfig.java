package com.venkat.design.creational.factory.abstr;

import lombok.Getter;

@Getter
public class LeakyBucketRateLimiterConfig {

	private int bucketCapacity;
	
	public LeakyBucketRateLimiterConfig(int bc) {
		this.bucketCapacity = bc;
	}

}
