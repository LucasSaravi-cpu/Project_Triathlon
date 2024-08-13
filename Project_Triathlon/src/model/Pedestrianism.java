package model;

public class Pedestrianism extends Discipline {
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	@Override
	public double time(String modality) {
		
		double time = 0;
		
		if (modality.equals("MediumDistance")) {
		    time = 90;
		} else if (modality.equals("LongDistance")) {
		    time = 180;
		} else if (modality.equals("Sprint")) {
		    time = 30;
		} else if (modality.equals("OlympicDistance")) {
		    time = 60;
		}
		
		return time;

}
}


