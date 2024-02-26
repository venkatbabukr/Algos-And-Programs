package com.venkat.design.structural.bridge;

import lombok.Data;

@Data
public class Radio implements Device {

	private int channel;
	private int volume;

	@Override
	public void turnOn() {
		System.out.println("Turning on Radio");
	}

	@Override
	public void turnOff() {
		System.out.println("Turning off Radio");
	}
	
}
