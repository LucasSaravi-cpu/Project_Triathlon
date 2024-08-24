package model;

public class Cycling extends Discipline{
	
	 //------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\

	@Override
	public String time (String modality) {
		
		String time = "00:00:00";
		
		if (modality.equals("MediumDistance")) {
		    time = "2:00:00";
		} else if (modality.equals("LongDistance")) {
		    time = "5:00:00";
		} else if (modality.equals("Sprint")) {
		    time = "00:45:00";
		} else if (modality.equals("OlympicDistance")) {
		    time = "01:00:00";
		}
		
		return time;
		
		


	}
	@Override
	public boolean surpassed(int positionX, Race race, int startX, int endX) {
		int cyclingToPedestrianismPoint = startX + (int) ((endX - startX) * race.getDisciplineChangePoints().get(1))-30;
		return positionX >= cyclingToPedestrianismPoint && positionX<cyclingToPedestrianismPoint+20;
	}
    public String toString(){
		return "Cycling";
	}
	@Override
	public Discipline getNewDiscipline(){
		return new Pedestrianism();
	}
	public Cycling createInstance(){
		return new Cycling();
	}
}
