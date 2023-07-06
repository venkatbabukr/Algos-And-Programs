package com.venkat.design.creational.factory.abstr;

import lombok.Getter;

@Getter
public class TokenBucketRateLimiterConfig {
	
	private int replenishRate;
	private int burstCapacity;
	
	public TokenBucketRateLimiterConfig(int rr, int bc) {
		this.replenishRate = rr;
		this.burstCapacity = bc;
	}

}
