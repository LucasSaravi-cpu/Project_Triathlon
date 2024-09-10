package model.neoprene;

import model.athlete.Athlete;
import model.race.Race;

import java.io.Serializable;

public class NeopreneAllowed extends NeoprenePolicy implements Serializable {

    public NeopreneAllowed(Athlete athlete, Race race, int raceIndex) {
        super(athlete, race, raceIndex);
    }
    @Override
    public void applyPolicy(int minutes) {
        System.out.println("Neoprene use is optional.");
        double maxAllowedTime = athlete.setMaximumNeopreneTime(race.getModality().getDisciplinedistance().get(0).getDistance());
        if(athlete.isUsingNeoprene()) {
            if(maxAllowedTime < minutes) {
                System.out.println("The athlete is using neoprene and has exceeded the maximum allowed time. They will be penalized.");
            }
        } else {
            System.out.println("The athlete is NOT using neoprene.");
        }
    }
}
