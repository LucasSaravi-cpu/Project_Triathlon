package model.neoprene;

import model.athlete.Athlete;
import model.race.Race;

public class NeopreneManager {
    private Athlete athlete;
    private Race race;
    private int raceIndex;

    public NeopreneManager(Athlete athlete, Race race, int raceIndex) {
        this.athlete = athlete;
        this.race = race;
        this.raceIndex = raceIndex;
    }

    public void setNeoprenePolicy(int minutes)
    {
        String neoprene = athlete.setNeopreneUsage(
                race.getModality().getDisciplinedistance().get(0).getDistance(),
                race.getCurrentWeatherCondition().getCurrentTemperature()
        );
        NeoprenePolicy policy;

        if (neoprene.equalsIgnoreCase("Forbidden") || !race.isCurrentneoprene()) {
            policy = new NeopreneForbidden(athlete, race, raceIndex);
        } else if (neoprene.equalsIgnoreCase("Mandatory")) {
            policy = new NeopreneMandatory(athlete, race, raceIndex);
        } else {
            policy = new NeopreneAllowed(athlete, race, raceIndex);
        }
        policy.applyPolicy(minutes);
    }
}
