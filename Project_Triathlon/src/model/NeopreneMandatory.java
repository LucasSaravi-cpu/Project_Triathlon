package model;

public class NeopreneMandatory extends NeoprenePolicy{

    public NeopreneMandatory(Athlete athlete, Race race, int raceIndex)
    {
        super(athlete, race, raceIndex);
    }

    @Override
    public void applyPolicy(int minutes) {
        System.out.println("El neopreno es obligatorio");
        double maxAllowedTime = athlete.setMaximumNeopreneTime(race.getModality().getDisciplinedistance().get(raceIndex).getDistance());
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
