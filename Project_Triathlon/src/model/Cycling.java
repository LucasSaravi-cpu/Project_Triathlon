package model;

import javax.swing.*;
import java.util.List;

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
	@Override
	public double getPoints(List<Double> disciplineChangePoints, Stations station, Race race, int startX, int endX){

		double difference = disciplineChangePoints.get(1) - (disciplineChangePoints.get(0) + 100.0 /(endX-startX));
		return ((disciplineChangePoints.get(0) + 100.0 / (endX - startX)+ station.getDistancing() * difference/race.getKmcyclism())*(endX-startX));
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
	@Override
	public ImageIcon getNewIcon(){
		return new ImageIcon(getClass().getResource("/Image/cycling.png"));
	}
	@Override
	public void setMaxTime(Race race, int time) {
		race.setT2(time);
	}
	@Override
	public double getBaseSpeed(PhysicalConditions stats){
		return 1000 + stats.getCyclismAptitude() * 100;
	}
	@Override
	public String setTime(Athlete athlete, Chronometer chronometer, int raceIndex){
		return Chronometer.subtractTimes(chronometer.getTime(), athlete.getCompetition().get(raceIndex).getDistances().get(0).getTime());
	}
	@Override
	public boolean isBeforePosition(int minutes, Race race){
		return minutes > race.getT1() + race.getT2();
	}
}
