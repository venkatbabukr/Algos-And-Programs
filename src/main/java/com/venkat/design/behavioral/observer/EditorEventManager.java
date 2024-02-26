package com.venkat.design.behavioral.observer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class EditorEventManager {

	private Map<EditorEvent, Set<EditorEventListener>> eventListenersMap = new HashMap<>();

	public void subscribe(EditorEvent event, EditorEventListener listener) {
		Set<EditorEventListener> listeners = eventListenersMap.get(event);
		if (listeners == null) {
			listeners = new HashSet<>();
			eventListenersMap.put(event, listeners);
		}
		listeners.add(listener);
	}

	public void unSubscribe(EditorEvent event, EditorEventListener listener) {
		Optional.ofNullable(eventListenersMap.get(event))
			.ifPresent(listeners -> listeners.remove(listener));
	}

	public void notifyEvent(EditorEvent event, Object data) {
		Optional.ofNullable(eventListenersMap.get(event))
			.ifPresent(listeners -> listeners.forEach(listener -> listener.onEvent(event, data)));
	}

}
