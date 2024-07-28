package model;

import java.util.List;

public class Modality {
	
	private String name;
	private List<DisciplineDistance> disciplinedistance; // We'll only have 3 disciplines according its modality to know its total kilogrames 

	
	

	
	
	
	
	public Modality(String name, List<DisciplineDistance> disciplinedistance) {
		super();
		this.name = name;
		this.disciplinedistance = disciplinedistance;
	}






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






	@Override
	public String toString() {
		return " " + name;
	}
	
	
	

}
