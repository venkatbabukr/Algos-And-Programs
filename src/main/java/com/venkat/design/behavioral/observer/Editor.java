package com.venkat.design.behavioral.observer;

import java.util.Map;

public class Editor {

	private EditorEventManager editorEventManager;

	public Editor() {
		editorEventManager = new EditorEventManager();
		editorEventManager.subscribe(EditorEvent.FILE_OPEN, new FileOpenEventLogger());
		editorEventManager.subscribe(EditorEvent.FILE_SAVE, new FileSaveEmailNotifier());
	}
	
	public void openFile(String fileName) {
		//
		// All code related to file open
		//
		editorEventManager.notifyEvent(EditorEvent.FILE_OPEN, Map.of("fileName", fileName));
	}

	public void saveFile(String fileName) {
		//
		// All code related to file save
		//
		editorEventManager.notifyEvent(EditorEvent.FILE_SAVE, Map.of("fileName", fileName));
	}

}
