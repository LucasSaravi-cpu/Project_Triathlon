package model.race.modality;

import model.race.discipline.DisciplineDistance;

import java.util.List;

public class OlympicDistance extends Modality {

    public OlympicDistance(List<DisciplineDistance> disciplinedistance) {
        super(disciplinedistance);
    }
    @Override
    public long getRaceTime(){
        return 4;
    }
}
