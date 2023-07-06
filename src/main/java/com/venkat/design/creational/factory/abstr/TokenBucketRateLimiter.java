package com.venkat.design.creational.factory.abstr;

import java.time.Duration;
import java.time.Instant;

public class TokenBucketRateLimiter extends LeakyBucketRateLimiter implements RateLimiter {

	private int tokenReplenishRate;
	private Instant lastReplenishTime;

	public TokenBucketRateLimiter(int tokenRR, int burstCapacity) {
		super(burstCapacity);
		this.tokenReplenishRate = tokenRR;

		this.availableCapacity = tokenRR;
		this.lastReplenishTime = Instant.now();
	}

	private void replenishTokens() {
		int replSeconds = (int) Duration.between(lastReplenishTime, Instant.now()).getSeconds();
		if (replSeconds > 0) {
			availableCapacity = Math.min(availableCapacity + replSeconds * tokenReplenishRate, bucketCapacity);
		}
	}

	@Override
	public synchronized boolean permitRequest(int requestId) {
		replenishTokens();
		return super.permitRequest(requestId);
	}

}
