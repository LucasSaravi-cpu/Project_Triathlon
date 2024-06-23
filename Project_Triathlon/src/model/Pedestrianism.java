package model;

public class Pedestrianism extends Discipline {
	
	
	@Override
	public double km(String modality) {
		
		double km = 0;
		
		if (modality.equals("MediumDistance")) {
		    km = 21.1;
		} else if (modality.equals("LongDistance")) {
		    km = 42.2;
		} else if (modality.equals("Sprint")) {
		    km = 5;
		} else if (modality.equals("OlympicDistance")) {
		    km = 10;
		}
		
		return km;

}
}


