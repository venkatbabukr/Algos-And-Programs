package com.venkat.design.behavioral.iterator;

import java.util.ConcurrentModificationException;
import java.util.List;

public class FacebookIterator implements ProfileIterator {

	private Facebook socialNetwork;
	private String profileId;
	private ConnectionType type;
	
	private List<Profile> cache;
	private int cacheSize;
	private int currentPosition;

	public FacebookIterator(Facebook facebook, String profileId, ConnectionType networkType) {
		this.socialNetwork = facebook;
		this.profileId = profileId;
		this.type = networkType;
	}

	private void lazyInit() {
		if (cache == null) {
			cache = socialNetwork.socialGraphRequest(type, profileId);
			cacheSize = cache.size();
			currentPosition = 0;
		}
	}
	
	private void checkConcurrentModification() {
		if (cache.size() != cacheSize) {
			throw new ConcurrentModificationException();
		}
	}

	@Override
	public boolean hasNext() {
		lazyInit();
		checkConcurrentModification();
		return currentPosition < cache.size();
	}

	@Override
	public Profile next() {
		checkConcurrentModification();
		return cache.get(currentPosition++);
	}

}
