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
	@Override
	public boolean surpassed(int positionX, Race race, int startX, int endX) {
		// When the athlete surpasses Pedestrianism, the race ends, so it should always return false!
		return false;
	}
	@Override
	public Discipline getNewDiscipline(){
		return new Swimming();
	}
	public Pedestrianism createInstance(){
		return new Pedestrianism();
	}
}


