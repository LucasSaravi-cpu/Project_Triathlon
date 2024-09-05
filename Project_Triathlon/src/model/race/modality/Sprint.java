package model.race.modality;

import model.race.discipline.DisciplineDistance;

import java.io.Serializable;
import java.util.List;

public class Sprint extends Modality implements Serializable {

    public Sprint(List<DisciplineDistance> disciplinedistance) {
        super(disciplinedistance);
    }
    @Override
    public long getRaceTime(){
        return 8;
    }
}
