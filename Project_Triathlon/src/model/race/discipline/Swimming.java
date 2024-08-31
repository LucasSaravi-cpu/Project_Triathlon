package model.race.discipline;

import model.athlete.Athlete;
import model.athlete.PhysicalConditions;
import model.race.Chronometer;
import model.race.Race;
import model.race.Stations;
import model.weather.WeatherConditions;

import javax.swing.*;
import java.util.List;

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
		int swimmingToCyclingPoint = startX + (int) ((endX - startX) * race.getDisciplineChangePoints().getFirst()) + 70;
		return positionX >= swimmingToCyclingPoint && positionX < startX + (int) ((endX - startX) * race.getDisciplineChangePoints().getFirst()) + 95;
	}
	@Override
	public double getPoints(List<Double> disciplineChangePoints, Stations station, Race race, int startX, int endX){
		double difference = disciplineChangePoints.get(0)+ 100.0 / (endX-startX);
		return station.getDistancing() * difference *(endX-startX)/race.getKmswimming();
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
	@Override
	public int getIconIndex(){
		return 1;
	}
	@Override

	public ImageIcon getNewIcon(){
		return null;
	}
	@Override
	public void setMaxTime(Race race, int time) {
		race.setT1(time);
	}
	@Override
	public double getBaseSpeed(PhysicalConditions stats, WeatherConditions wc){
		return stats.getSwimmingAptitude() * (1 + wc.getSwimmingWeathering()/100);
		// return 1500 - stats.getSwimmingAptitude() * 30 - 10 * wc.getSwimmingweathering();
	}
	@Override
	public String setTime(Athlete athlete, Chronometer chronometer, int raceIndex){
		return chronometer.getTime();
	}
	@Override
	public boolean isBeforePosition(int minutes, Race race){
		return minutes > race.getT1();
	}
	@Override
	public double getStat(PhysicalConditions stats){
		return stats.getSwimmingAptitude();
	}
	@Override
	public double getWeatherImpact(WeatherConditions wc){
		return wc.getSwimmingWeathering();
	}
}
