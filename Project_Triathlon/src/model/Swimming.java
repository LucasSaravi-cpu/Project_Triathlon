package model;

public class Swimming extends Discipline {
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public double time (String modality) {   
		
	double time = 0 ;
		
		if (modality.equals("MediumDistance")) {
		   time = 40;
		} else if (modality.equals("LongDistance")) {
		    time = 60;
		} else if (modality.equals("Sprint")) {
		     time = 15;
		} else if (modality.equals("OlympicDistance")) {
		    time = 30;
		}
		
		return time;
		
	
    }
}
