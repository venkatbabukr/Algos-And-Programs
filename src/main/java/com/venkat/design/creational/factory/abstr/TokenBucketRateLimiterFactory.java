package com.venkat.design.creational.factory.abstr;

public class TokenBucketRateLimiterFactory implements RateLimiterFactory<TokenBucketRateLimiter> {
	
	private TokenBucketRateLimiterConfig rlConfig;

	public TokenBucketRateLimiterFactory(TokenBucketRateLimiterConfig config) {
		this.rlConfig = config;
	}

	@Override
	public TokenBucketRateLimiter newRateLimiter() {
		return new TokenBucketRateLimiter(rlConfig.getReplenishRate(), rlConfig.getBurstCapacity());
	}

}
