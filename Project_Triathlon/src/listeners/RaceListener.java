package listeners;

import model.RaceThread;

//------------------------------------------------>||INTERFACE||<--------------------------------------------------------\\

public interface RaceListener {
	void positionChanged(RaceThread thread, int newPositionX);
	
}
