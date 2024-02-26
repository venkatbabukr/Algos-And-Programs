package com.venkat.design.behavioral.observer;

public class FileSaveEmailNotifier implements EditorEventListener {

	@Override
	public void onEvent(EditorEvent event, Object data) {
		System.out.format("E-mailing File Save event %s, details: %s%n", event, data);
	}

}
