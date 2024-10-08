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

public class Cycling extends Discipline implements Serializable {

    //------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\

    @Override
    public String time(String modality) {

        String time = "00:00:00";

        if (modality.equals("MediumDistance")) {
            time = "03:33:00";
        } else if (modality.equals("LongDistance")) {
            time = "07:06:00";
        } else if (modality.equals("Sprint")) {
            time = "00:50:00";
        } else if (modality.equals("OlympicDistance")) {
            time = "02:27:00";
        }

        return time;


    }

    @Override
    public boolean surpassed(int positionX, Race race, int startX, int endX) {
        int cyclingToPedestrianismPoint = startX + (int) ((endX - startX) * race.getDisciplineChangePoints().get(1)) - 30;
        return positionX >= cyclingToPedestrianismPoint && positionX < cyclingToPedestrianismPoint + 25;
    }

    @Override
    public double getPoints(List<Double> disciplineChangePoints, Stations station, Race race, int startX, int endX) {

        double difference = disciplineChangePoints.get(1) - (disciplineChangePoints.get(0) + 100.0 / (endX - startX));
        return ((disciplineChangePoints.get(0) + 100.0 / (endX - startX) + station.getDistancing() * difference / race.getKmcycling()) * (endX - startX));
    }

    public String toString() {
        return "Cycling";
    }

    @Override
    public Discipline getNewDiscipline() {
        return new Pedestrianism();
    }

    public Cycling createInstance() {
        return new Cycling();
    }
    @Override
    public int getIconIndex(){
        return 2;
    }
    @Override
    public ImageIcon getNewIcon() {
        return new ImageIcon(getClass().getResource("/Image/cycling.png"));
    }

    @Override
    public void setMaxTime(Race race, int time) {
        race.setT2(time);
    }

    @Override
    public double getBaseSpeed(PhysicalConditions stats, WeatherConditions wc) {
        return stats.getCyclingAptitude() * 1.4 * (1 + wc.getCyclingImpact() / 100);
        //return 1500 - stats.getCyclismAptitude() * 70 - 10 * wc.getCyclingweathering();
    }

    @Override
    public String setTime(Athlete athlete, Chronometer chronometer, int raceIndex) {
        return Chronometer.subtractTimes(chronometer.getTime(), athlete.getCompetition().get(raceIndex).getDistances().get(0).getTime());
    }

    @Override
    public boolean isBeforePosition(int minutes, Race race) {
        return minutes > race.getT1() + race.getT2();
    }

    @Override
    public double getStat(PhysicalConditions stats) {
        return stats.getCyclingAptitude();
    }

    @Override
    public double getWeatherImpact(WeatherConditions wc) {
        return wc.getCyclingImpact();
    }
    @Override
    public double getKmInDiscipline(Race race, int positionX, int startX, int endX){
        double progress = (positionX - (race.getDisciplineChangePoints().getFirst()*(endX-startX)+100) - startX) / (race.getDisciplineChangePoints().get(1)*(endX-startX) - (race.getDisciplineChangePoints().getFirst()*(endX-startX)+100));
        return progress * race.getKmcycling();
    }
    @Override
    public int getBaseIndex(int baseIndex) {
        return baseIndex+1;
    }
}
