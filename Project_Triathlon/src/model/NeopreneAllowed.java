package model;

public class NeopreneAllowed extends NeoprenePolicy{

    public NeopreneAllowed(Athlete athlete, Race race, int raceIndex) {
        super(athlete, race, raceIndex);
    }
    @Override
    public void applyPolicy(int minutes) {
        System.out.println("El neopreno es de uso opcional");
        double maxAllowedTime = athlete.setMaximumNeopreneTime(race.getModality().getDisciplinedistance().get(raceIndex).getDistance());
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
