package com.venkat.design.behavioral.iterator;

import java.util.List;

public class Facebook implements SocialNetwork {

	@Override
	public ProfileIterator createFriendsIterator(String profileId) {
		return new FacebookIterator(this, profileId, ConnectionType.SOCIAL);
	}

	@Override
	public ProfileIterator createColleaguesIterator(String profileId) {
		return new FacebookIterator(this, profileId, ConnectionType.PROFESSIONAL);
	}

	public List<Profile> socialGraphRequest(ConnectionType type, String profileId) {
		// TODO Auto-generated method stub
		return null;
	}

}
