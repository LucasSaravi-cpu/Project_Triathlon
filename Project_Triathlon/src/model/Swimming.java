package model;

public class Swimming extends Discipline {
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public String time (String modality) {
		
	String time = "00:00:00";
		
		if (modality.equals("MediumDistance")) {
		   time = "00:40:00";
		} else if (modality.equals("LongDistance")) {
		    time = "01:00:00";
		} else if (modality.equals("Sprint")) {
		     time = "00:15:00";
		} else if (modality.equals("OlympicDistance")) {
		    time = "00:30:00";
		}
		
		return time;
		
	
    }
	public String toString(){
		return "Swimming";
	}
}
