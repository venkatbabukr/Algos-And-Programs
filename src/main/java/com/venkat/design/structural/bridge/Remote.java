package com.venkat.design.structural.bridge;

/**
 * This is the abstraction that clients will use to send requests to concrete implementations.
 * 
 * @author venkateshbabukr
 */
public class Remote {

	private Device receiverDevice;
	
	private Boolean turnOnDevice = true;

	protected Device getDevice() {
		return receiverDevice;
	}
	
	public void powerButtonPress() {
		if (turnOnDevice) {
			receiverDevice.turnOn();
		} else {
			receiverDevice.turnOff();
		}
		turnOnDevice = !turnOnDevice;
	}

	public void incrementVolume() {
		receiverDevice.setVolume(receiverDevice.getVolume() + 1);
	}

	public void decrementVolume() {
		receiverDevice.setVolume(receiverDevice.getVolume() - 1);
	}

	public void incrementChannel() {
		receiverDevice.setChannel(receiverDevice.getChannel() + 1);
	}

	public void decrementChannel() {
		receiverDevice.setChannel(receiverDevice.getChannel() - 1);
	}

}
