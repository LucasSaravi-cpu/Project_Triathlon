package model.neoprene;

import model.athlete.Athlete;
import model.race.Race;
import model.race.thread.RaceThread;

import java.io.Serializable;

public class NeopreneManager implements Serializable {
    private Athlete athlete;
    private Race race;
    private int raceIndex;
    private  RaceThread raceThread;

    public NeopreneManager(Athlete athlete, Race race, int raceIndex, RaceThread raceThread) {
        this.athlete = athlete;
        this.race = race;
        this.raceIndex = raceIndex;
        this.raceThread = raceThread;
    }

    public void setNeoprenePolicy(int minutes)
    {
        String neoprene = athlete.setNeopreneUsage(
                race.getModality().getDisciplinedistance().get(0).getDistance(),
                race.getCurrentWeatherCondition().getCurrentTemperature()
        );
        NeoprenePolicy policy;

        if (neoprene.equalsIgnoreCase("Forbidden") || !race.isCurrentneoprene()) {
            policy = new NeopreneForbidden(athlete, race, raceIndex,  raceThread);
        } else if (neoprene.equalsIgnoreCase("Mandatory")) {
            policy = new NeopreneMandatory(athlete, race, raceIndex,raceThread);
        } else {
            policy = new NeopreneAllowed(athlete, race, raceIndex,raceThread);
        }
        policy.applyPolicy(minutes);
    }
}
