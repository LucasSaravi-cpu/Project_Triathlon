package model.race.discipline;

import model.athlete.Athlete;
import model.athlete.PhysicalConditions;
import model.race.Chronometer;
import model.race.Race;
import model.race.Stations;
import model.weather.WeatherConditions;

import javax.swing.*;
import java.util.List;

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
	@Override
	public double getPoints(List<Double> disciplineChangePoints, Stations station, Race race, int startX, int endX){
		double difference = 1 - disciplineChangePoints.get(1);
		return ((disciplineChangePoints.get(1) + station.getDistancing() * difference/race.getKmpedestrianism()))* (endX-startX);

	}
	@Override
	public ImageIcon getNewIcon(){
		return new ImageIcon(getClass().getResource("/Image/running.png"));
	}
	public Pedestrianism createInstance(){
		return new Pedestrianism();
	}
	@Override
	public void setMaxTime(Race race, int time) {
		race.setT3(time);
	}
	@Override
	public double getBaseSpeed(PhysicalConditions stats, WeatherConditions wc){
		return stats.getPedestrianismAptitude() * (1 + wc.getPedestrianismweathering()/100);
		// return 1500 - stats.getPedestrianismAptitude() * 50 - 10 * wc.getPedestrianismweathering();
	}
	@Override
	public String setTime(Athlete athlete, Chronometer chronometer, int raceIndex){
		return Chronometer.subtractTimes(Chronometer.subtractTimes(chronometer.getTime(), athlete.getCompetition().get(raceIndex).getDistances().get(1).getTime()), athlete.getCompetition().get(raceIndex).getDistances().get(0).getTime());
	}
	@Override
	public boolean isBeforePosition(int minutes, Race race){
		return minutes > race.getT1() + race.getT2() + race.getT3();
	}
	@Override
	public double getStat(PhysicalConditions stats) {
		return stats.getPedestrianismAptitude();
	}
	@Override
	public double getWeatherImpact(WeatherConditions wc){
		return wc.getPedestrianismweathering();
	}
}


