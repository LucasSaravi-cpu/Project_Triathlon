package model.race.modality;

import model.race.discipline.DisciplineDistance;

import java.io.Serializable;
import java.util.List;

public class MediumDistance extends Modality implements Serializable {
    public MediumDistance(List<DisciplineDistance> disciplinedistance) {
        super(disciplinedistance);
    }
    @Override
    public long getRaceTime(){
        return 2;
    }
}
