package model;

public class Cycling extends Discipline{

	@Override
	public double km(String modality) {
		
		double km = 0;
		
		if (modality.equals("MediumDistance")) {
		    km = 90;
		} else if (modality.equals("LongDistance")) {
		    km = 180;
		} else if (modality.equals("Sprint")) {
		    km = 20;
		} else if (modality.equals("OlympicDistance")) {
		    km = 40;
		}
		
		return km;
		
		


	}

}
