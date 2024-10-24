package Events;

import model.race.discipline.Discipline;

import java.util.EventObject;

public class DisciplineChangeEvent extends EventObject {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
    private final Discipline newDiscipline;
    private boolean isFirst;
    
    //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
    
    public DisciplineChangeEvent(Object source, Discipline newDiscipline, boolean isFirst) {
        super(source);
        this.newDiscipline = newDiscipline;
        this.isFirst = isFirst;
    }
    
  //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

    public Discipline getNewDiscipline() {
        return newDiscipline;
    }
   
    public boolean getIsFirst() { return isFirst; }
}