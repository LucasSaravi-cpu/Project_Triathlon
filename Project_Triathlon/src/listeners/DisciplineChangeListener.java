package listeners;

import java.sql.SQLException;

import Events.DisciplineChangeEvent;

public interface DisciplineChangeListener {
    void disciplineChanged(DisciplineChangeEvent event) throws SQLException;
}