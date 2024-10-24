package model.race.modality;

import model.race.discipline.DisciplineDistance;

import java.io.Serializable;
import java.util.List;

public class LongDistance extends Modality implements Serializable {
	
	
	  //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
    public LongDistance(List<DisciplineDistance> disciplinedistance) {
        super(disciplinedistance);
    }
    
  //------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
    @Override
    public long getRaceTime(){
        return 1;
    }
}
