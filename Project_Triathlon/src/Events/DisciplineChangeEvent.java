package Events;

import model.race.discipline.Discipline;

import java.util.EventObject;

public class DisciplineChangeEvent extends EventObject {
    private final Discipline newDiscipline;

    public DisciplineChangeEvent(Object source, Discipline newDiscipline) {
        super(source);
        this.newDiscipline = newDiscipline;
    }

    public Discipline getNewDiscipline() {
        return newDiscipline;
    }
}