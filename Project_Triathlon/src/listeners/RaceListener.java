package listeners;

import model.race.thread.RaceThread;

//------------------------------------------------>||INTERFACE||<--------------------------------------------------------\\

public interface RaceListener {
	void positionChanged(RaceThread thread, int newPositionX);
	
}
