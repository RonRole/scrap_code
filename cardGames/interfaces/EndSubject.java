package interfaces;

import java.util.*;

public abstract class EndSubject {
	private List<GameMasterListener> listeners = new ArrayList<>();

	public void addListener(GameMasterListener listener) {
		this.listeners.add(listener);
	}

	public void notifyListeners() {
		listeners.stream().forEach(i -> i.update(this));
	}
}
