package model.neoprene;

import model.athlete.Athlete;
import model.race.Race;
import model.race.thread.RaceThread;

import java.io.Serializable;

public abstract class NeoprenePolicy implements Serializable {
    protected Athlete athlete;
    protected Race race;
    protected int raceIndex;
    protected RaceThread raceThread;

    public NeoprenePolicy(Athlete athlete, Race race, int raceIndex, RaceThread raceThread) {
        this.athlete = athlete;
        this.race = race;
        this.raceIndex = raceIndex;
        this.raceThread = raceThread;
    }

    public abstract void applyPolicy(int minutes);
}
