package model.neoprene;

import model.athlete.Athlete;
import model.race.Race;

public class NeopreneMandatory extends NeoprenePolicy{

    public NeopreneMandatory(Athlete athlete, Race race, int raceIndex)
    {
        super(athlete, race, raceIndex);
    }

    @Override
    public void applyPolicy(int minutes) {
        System.out.println("El neopreno es obligatorio");
        double maxAllowedTime = athlete.setMaximumNeopreneTime(race.getModality().getDisciplinedistance().get(0).getDistance());
        if(athlete.isUsingNeoprene())
        {
            if(maxAllowedTime < minutes)
            {
                System.out.println("El atleta ha excedido el tiempo maximo de uso del neopreno. Sera penalizado");
            }
        }
        else
            System.out.println("El atleta NO esta usando neopreno a pesar de ser obligatorio. Sera penalizado");
    }
}
