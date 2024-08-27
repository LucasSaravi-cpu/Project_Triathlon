package model.neoprene;

import model.athlete.Athlete;
import model.race.Race;

public abstract class NeoprenePolicy {
    protected Athlete athlete;
    protected Race race;
    protected int raceIndex;

    public NeoprenePolicy(Athlete athlete, Race race, int raceIndex) {
        this.athlete = athlete;
        this.race = race;
        this.raceIndex = raceIndex;
    }

    public abstract void applyPolicy(int minutes);
}
