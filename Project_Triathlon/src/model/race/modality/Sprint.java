package model.race.modality;

import model.race.discipline.DisciplineDistance;

import java.util.List;

public class Sprint extends Modality {

    public Sprint(List<DisciplineDistance> disciplinedistance) {
        super(disciplinedistance);
    }
    @Override
    public long getRaceTime(){
        return 8;
    }
}
