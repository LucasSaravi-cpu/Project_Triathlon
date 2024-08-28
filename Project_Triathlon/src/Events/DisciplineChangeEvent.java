package Events;

import model.race.discipline.Discipline;

import java.util.EventObject;

public class DisciplineChangeEvent extends EventObject {
    private final Discipline newDiscipline;
    private boolean isFirst;
    public DisciplineChangeEvent(Object source, Discipline newDiscipline, boolean isFirst) {
        super(source);
        this.newDiscipline = newDiscipline;
        this.isFirst = isFirst;
    }

    public Discipline getNewDiscipline() {
        return newDiscipline;
    }
    public boolean getIsFirst() { return isFirst; }
}