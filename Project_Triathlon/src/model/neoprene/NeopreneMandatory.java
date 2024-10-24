package model.neoprene;

import model.athlete.Athlete;
import model.race.Race;
import model.race.thread.RaceThread;

import java.io.Serializable;

public class NeopreneMandatory extends NeoprenePolicy implements Serializable {

	 //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

    public NeopreneMandatory(Athlete athlete, Race race, int raceIndex, RaceThread raceThread) {
		super(athlete, race, raceIndex, raceThread);
	}

  //------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\
    
	@Override
    public void applyPolicy(int minutes) {
    //    System.out.println("Neoprene is mandatory.");
        double maxAllowedTime = athlete.setMaximumNeopreneTime(race.getModality().getDisciplinedistance().get(0).getDistance());

        if (athlete.isUsingNeoprene()) {
            if (maxAllowedTime < minutes) {
             //   System.out.println("The athlete has exceeded the maximum allowed time for neoprene use. They will be penalized.");
                raceThread.stopathlete();
            }
        } /*else {
            System.out.println("The athlete is NOT using neoprene despite it being mandatory. They will be penalized.");
        }*/
    }
}
