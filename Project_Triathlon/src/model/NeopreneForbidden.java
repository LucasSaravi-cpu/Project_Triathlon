package model;

public class NeopreneForbidden extends  NeoprenePolicy{

    public NeopreneForbidden(Athlete athlete, Race race, int raceIndex)
    {
        super(athlete, race, raceIndex);
    }


    @Override
    public void applyPolicy(int minutes)
    {
        System.out.println("El neopreno NO esta permitido");

        if(!athlete.isUsingNeoprene())
        {
            System.out.println("El atleta no esta usando neopreno");
        }
        else
            System.out.println("El atleta esta usando neopreno a pesar de la prohibicion. Debera ser sancionado");
    }
}
