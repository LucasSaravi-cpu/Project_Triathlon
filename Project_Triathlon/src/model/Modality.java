package model;

import java.util.List;

public class Modality {
	
	private String name;
	private List<DisciplineDistance> disciplinedistance; // We'll only have 3 disciplines according its modality to know its total kilogrames 

	
	public Modality(String name) {
		super();
		this.name = name;
	}


	@Override
	public String toString() {
		return " " + name;
	}
	
	
	

}
