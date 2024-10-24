package model.race.modality;

import model.race.discipline.DisciplineDistance;

import java.io.Serializable;
import java.util.List;

public class OlympicDistance extends Modality implements Serializable {
	
	  //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

    public OlympicDistance(List<DisciplineDistance> disciplinedistance) {
        super(disciplinedistance);
    }
    
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
    @Override
    public long getRaceTime(){
        return 3;
    }
}
