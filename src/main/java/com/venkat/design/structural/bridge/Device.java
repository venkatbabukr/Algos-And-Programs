package com.venkat.design.structural.bridge;

/**
 * This is the Bridge class - The interface whose concrete implementations will be used in the
 * abstraction.
 * 
 * @author venkateshbabukr
 */
public interface Device {

	void turnOn();
	
	void turnOff();
	
	void setChannel(int channel);
	
	int getChannel();
	
	void setVolume(int volume);

	int getVolume();

}
