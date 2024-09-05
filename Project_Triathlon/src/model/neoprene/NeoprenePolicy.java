package model.neoprene;

import model.athlete.Athlete;
import model.race.Race;

import java.io.Serializable;

public abstract class NeoprenePolicy implements Serializable {
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
