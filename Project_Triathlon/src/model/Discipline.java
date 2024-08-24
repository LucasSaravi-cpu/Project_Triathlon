package model;

public abstract class Discipline {

	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\

	public abstract String time (String modality);
    public abstract boolean surpassed(int positionX, Race race, int startX, int endX);
    public abstract Discipline getNewDiscipline();
	public abstract Discipline createInstance();
}
