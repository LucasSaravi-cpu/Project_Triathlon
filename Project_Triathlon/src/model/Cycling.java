package model;

public class Cycling extends Discipline{

	@Override
	public double time (String modality) {
		
		double time = 0;
		
		if (modality.equals("MediumDistance")) {
		    time = 120;
		} else if (modality.equals("LongDistance")) {
		    time = 300;
		} else if (modality.equals("Sprint")) {
		    time = 45;
		} else if (modality.equals("OlympicDistance")) {
		    time = 60;
		}
		
		return time;
		
		


	}

}
