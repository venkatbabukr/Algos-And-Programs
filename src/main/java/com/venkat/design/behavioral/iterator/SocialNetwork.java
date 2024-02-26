package com.venkat.design.behavioral.iterator;

public interface SocialNetwork {
	
	ProfileIterator createFriendsIterator(String profileId);
	
	ProfileIterator createColleaguesIterator(String profileId);

}
