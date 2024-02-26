package com.venkat.design.behavioral.observer;

public class FileOpenEventLogger implements EditorEventListener {

	@Override
	public void onEvent(EditorEvent event, Object data) {
		System.out.format("Logging File Open event %s, details: %s%n", event, data);
	}

}
