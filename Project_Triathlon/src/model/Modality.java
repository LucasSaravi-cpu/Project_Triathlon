package model;

import java.util.List;

public class Modality {
	
	private String name;
	private List<DisciplineDistance> disciplinedistance; // Solo tendre 3 disciplinas segun su modalidad para saber sus kg totales

	
	public Modality(String name) {
		super();
		this.name = name;
	}


	@Override
	public String toString() {
		return "Modality"  + name;
	}
	
	
	

}
