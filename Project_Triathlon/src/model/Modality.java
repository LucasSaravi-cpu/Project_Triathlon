package model;

import model.race.discipline.DisciplineDistance;

import java.util.List;

public class Modality {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
	private String name;
	private List<DisciplineDistance> disciplinedistance; // We'll only have 3 disciplines according its modality to know its total kilogrames

	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	
	public Modality(String name, List<DisciplineDistance> disciplinedistance) {
		super();
		this.name = name;
		this.disciplinedistance = disciplinedistance;
	}


	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<DisciplineDistance> getDisciplinedistance() {
		return disciplinedistance;
	}


	public void setDisciplinedistance(List<DisciplineDistance> disciplinedistance) {
		this.disciplinedistance = disciplinedistance;
	}



	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\


	@Override
	public String toString() {
		return " " + name;
	}
	
	
	

}
