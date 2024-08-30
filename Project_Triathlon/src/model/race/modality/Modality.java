package model.race.modality;

import model.race.discipline.DisciplineDistance;

import java.util.List;

public abstract class Modality {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
	private String name;
	private List<DisciplineDistance> disciplinedistance; // We'll only have 3 disciplines according its modality to know its total kilogrames

	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	
	public Modality(List<DisciplineDistance> disciplinedistance) {
		super();
		this.disciplinedistance = disciplinedistance;
	}


	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\



	public List<DisciplineDistance> getDisciplinedistance() {
		return disciplinedistance;
	}


	public void setDisciplinedistance(List<DisciplineDistance> disciplinedistance) {
		this.disciplinedistance = disciplinedistance;
	}



	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
    public abstract double getRaceTime();

	@Override
	public String toString() {
		return " " + name;
	}
	
	
	

}
