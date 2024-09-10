package model.neoprene;

import model.athlete.Athlete;
import model.race.Race;

import java.io.Serializable;

public class NeopreneMandatory extends NeoprenePolicy implements Serializable {

    public NeopreneMandatory(Athlete athlete, Race race, int raceIndex)
    {
        super(athlete, race, raceIndex);
    }


    @Override
    public void applyPolicy(int minutes) {
        System.out.println("Neoprene is mandatory.");
        double maxAllowedTime = athlete.setMaximumNeopreneTime(race.getModality().getDisciplinedistance().get(0).getDistance());

        if (athlete.isUsingNeoprene()) {
            if (maxAllowedTime < minutes) {
                System.out.println("The athlete has exceeded the maximum allowed time for neoprene use. They will be penalized.");
            }
        } else {
            System.out.println("The athlete is NOT using neoprene despite it being mandatory. They will be penalized.");
        }
    }
}
