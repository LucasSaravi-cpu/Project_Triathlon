package model;

public class Swimming extends Discipline {
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public String time (String modality) {
		
	String time = "00:00:00";
		
		if (modality.equals("MediumDistance")) {
		   time = "00:10:00";
		} else if (modality.equals("LongDistance")) {
		    time = "0:10:00";
		} else if (modality.equals("Sprint")) {
		     time = "00:10:00";
		} else if (modality.equals("OlympicDistance")) {
		    time = "00:10:00";
		}
		
		return time;
		
	
    }
	@Override
	public boolean surpassed(int positionX, Race race, int startX, int endX) {
		int swimmingToCyclingPoint = startX + (int) ((endX - startX) * race.getDisciplineChangePoints().getFirst())+70;
		return positionX >= swimmingToCyclingPoint && positionX < startX + (int) ((endX - startX) * race.getDisciplineChangePoints().getFirst())+90;
	}
	public String toString(){
		return "Swimming";
	}
	@Override
    public Discipline getNewDiscipline(){
		return new Cycling();
	}
	public Swimming createInstance(){
		return new Swimming();
	}
}
