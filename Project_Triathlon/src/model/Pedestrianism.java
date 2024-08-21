package model;

public class Pedestrianism extends Discipline {
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	@Override
	public String time(String modality) {
		
		String time = "00:00:00";

		if (modality.equals("MediumDistance")) {
		    time = "01:30:00";
		} else if (modality.equals("LongDistance")) {
		    time = "03:00:00";
		} else if (modality.equals("Sprint")) {
		    time = "00:30:00";
		} else if (modality.equals("OlympicDistance")) {
		    time = "01:00:00";
		}
		
		return time;

    }
	public String toString(){
		return "Pedestrianism";
	}
}


