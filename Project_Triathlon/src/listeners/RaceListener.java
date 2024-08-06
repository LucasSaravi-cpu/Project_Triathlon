package listeners;

import model.RaceThread;

public interface RaceListener {
	void positionChanged(RaceThread thread, int newPositionX);
	
}
