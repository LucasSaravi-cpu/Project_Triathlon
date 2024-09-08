package model.race.modality;

import model.race.discipline.DisciplineDistance;

import java.io.Serializable;
import java.util.List;

public class OlympicDistance extends Modality implements Serializable {

    public OlympicDistance(List<DisciplineDistance> disciplinedistance) {
        super(disciplinedistance);
    }
    @Override
    public long getRaceTime(){
        return 3;
    }
}
