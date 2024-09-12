package model.race.discipline;

import model.athlete.Athlete;
import model.athlete.PhysicalConditions;
import model.race.Chronometer;
import model.race.Race;
import model.race.Stations;
import model.weather.WeatherConditions;

import javax.swing.*;
import java.io.Serializable;
import java.util.List;

public class Pedestrianism extends Discipline implements Serializable {
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	@Override
	public String time(String modality) {
		
		String time = switch (modality) {
            case "MediumDistance" -> "01:10:00";
            case "LongDistance" -> "02:05:00";
            case "Sprint" -> "00:18:00";
            case "OlympicDistance" -> "00:47:00";
            default -> "00:00:00";
        };

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
	public int getIconIndex(){
		return 3;
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
		return stats.getPedestrianismAptitude() * 1.2 * (1 + wc.getPedestrianismImpact()/100);

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
		return wc.getPedestrianismImpact();
	}
	@Override
	public double getKmInDiscipline(Race race, int positionX, int startX, int endX){
		double progress = (positionX - race.getDisciplineChangePoints().get(1)*(endX-startX) - startX) / (endX - race.getDisciplineChangePoints().get(1)*(endX-startX));
		return progress * race.getKmpedestrianism();
	}

}


