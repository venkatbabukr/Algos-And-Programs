package com.venkat.design.structural.bridge;

public class AdvancedRemote extends Remote {
	
	private int volumeBeforeMute;
	
	public void mute() {
		volumeBeforeMute = getDevice().getVolume();
		getDevice().setVolume(0);
	}

	public void unMute() {
		getDevice().setVolume(volumeBeforeMute);
	}

}
