package model;

public class Swimming extends Discipline {
	
	
	@Override
	public double km(String modality) {   
		
		double km = 0;
		
		if (modality.equals("MediumDistance")) {
		    km = 1.9;
		} else if (modality.equals("LongDistance")) {
		    km = 3.8;
		} else if (modality.equals("Sprint")) {
		    km = 0.75;
		} else if (modality.equals("OlympicDistance")) {
		    km = 1.5;
		}
		
		return km;


}
	
	

}
