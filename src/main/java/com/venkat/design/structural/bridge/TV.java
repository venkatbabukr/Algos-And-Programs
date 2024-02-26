package com.venkat.design.structural.bridge;

import lombok.Data;

@Data
public class TV implements Device {

	private int channel;
	private int volume;
	
	@Override
	public void turnOn() {
		System.out.println("Turning on TV");
	}

	@Override
	public void turnOff() {
		System.out.println("Turning on TV");
	}

}
