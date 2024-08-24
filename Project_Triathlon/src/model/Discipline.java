package model;

import javax.swing.*;
import java.util.List;

public abstract class Discipline {

	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\

	public abstract String time (String modality);
    public abstract boolean surpassed(int positionX, Race race, int startX, int endX);
    public abstract Discipline getNewDiscipline();
	public abstract Discipline createInstance();
    public abstract double getPoints(List<Double> disciplineChangePoints, Stations station, Race race, int startX, int endX);
    public abstract ImageIcon getNewIcon();
    public abstract void setTime (Race race, int time);
    public abstract double getBaseSpeed(PhysicalConditions stats);
}
