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
        System.out.println("El neopreno es de uso opcional");
        double maxAllowedTime = athlete.setMaximumNeopreneTime(race.getModality().getDisciplinedistance().get(0).getDistance());
        if(athlete.isUsingNeoprene())
        {
            if(maxAllowedTime < minutes)
            {
                System.out.println("El atleta esta usando neopreno y ha excedido el tiempo maximo de uso. Sera penalizado");
            }
        }
        else
            System.out.println("El atleta NO esta usando neopreno");
    }
}
