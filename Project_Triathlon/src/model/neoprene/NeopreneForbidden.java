package model.neoprene;

import model.athlete.Athlete;
import model.race.Race;
import model.race.thread.RaceThread;

import java.io.Serializable;

public class NeopreneForbidden extends  NeoprenePolicy implements Serializable {

 


    public NeopreneForbidden(Athlete athlete, Race race, int raceIndex, RaceThread raceThread) {
		super(athlete, race, raceIndex, raceThread);
	}

	@Override
    public void applyPolicy(int minutes) {
      // System.out.println("Neoprene is NOT allowed");

        if (!athlete.isUsingNeoprene()) {
          //  System.out.println("The athlete is not using neoprene.");
        } else {
           // System.out.println("The athlete is using neoprene despite the prohibition. They should be penalized.");
           raceThread.stopathlete();
        }
    }
}
