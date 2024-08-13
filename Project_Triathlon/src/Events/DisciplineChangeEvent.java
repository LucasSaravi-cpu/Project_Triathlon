package Events;

import java.util.EventObject;

public class DisciplineChangeEvent extends EventObject {
    private final String newDiscipline;

    public DisciplineChangeEvent(Object source, String newDiscipline) {
        super(source);
        this.newDiscipline = newDiscipline;
    }

    public String getNewDiscipline() {
        return newDiscipline;
    }
}