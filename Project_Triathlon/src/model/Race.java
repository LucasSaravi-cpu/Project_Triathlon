package model;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Race {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
    private City city;
    private Country country;
    private Date date;
    private List <WeatherConditions> condition;
    private List<Athlete> athlete;
    private Modality modality;
    private double kmswimming;
    private double kmcyclism;
    private double  kmpedestrianism;
    private List<Stations> stations;
    


    //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
    
	public Race(City city, Country country, Date date, Modality modality, double kmswimming, double kmcyclism,
			double kmpedestrianism, List<Stations> stations,List<WeatherConditions> condition) {
		super();
		this.city = city;
		this.country = country;
		this.date = date;
		this.modality = modality;
		this.kmswimming = kmswimming;
		this.kmcyclism = kmcyclism;
		this.kmpedestrianism = kmpedestrianism;
		this.stations = stations;
		 this.athlete = new ArrayList<Athlete>();
		 this.condition = new ArrayList<WeatherConditions>();
	}


	  //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	public City getCity() {
		return city;
	}


	public void setCity(City city) {
		this.city = city;
	}
	
	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}

	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}

	public List<WeatherConditions> getCondition() {
		return condition;
	}

	public void setCondition(List<WeatherConditions> condition) {
		this.condition = condition;
	}

	public List<Athlete> getAthlete() {
		return athlete;
	}

	public void setAthlete(List<Athlete> athlete) {
		this.athlete = athlete;
	}

	public Modality getModality() {
		return modality;
	}

	public void setModality(Modality modality) {
		this.modality = modality;
	}

	public double getKmswimming() {
		return kmswimming;
	}

	public void setKmswimming(double kmswimming) {
		this.kmswimming = kmswimming;
	}



	public double getKmcyclism() {
		return kmcyclism;
	}


	public void setKmcyclism(double kmcyclism) {
		this.kmcyclism = kmcyclism;
	}



	public double getKmpedestrianism() {
		return kmpedestrianism;
	}



	public void setKmpedestrianism(double kmpedestrianism) {
		this.kmpedestrianism = kmpedestrianism;
	}



	public List<Stations> getStations() {
		return stations;
	}



	public void setStations(List<Stations> stations) {
		this.stations = stations;
	}

	
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	  // Static method to generate a random boolean
    public static boolean UseOfDraftingInRacer() {
        Random random = new Random();
        return random.nextBoolean();
    }
    
    
	public double getTotalDistance() {
		return modality.getDisciplinedistance().stream().mapToDouble(DisciplineDistance::getDistance).sum();
	}
	
	
	public List<Double> getDisciplineChangePoints() {
		List<Double> points = new ArrayList<>();
		double totalDistance = getTotalDistance();
		double cumulativeDistance = 0.0;

		for (DisciplineDistance disciplineDistance : modality.getDisciplinedistance()) {
			cumulativeDistance += disciplineDistance.getDistance();
			points.add(cumulativeDistance / totalDistance);
		}
		points.remove(2);
		return points;

	}
	
	public List<Double> getStationPoints(List<Double> disciplineChangePoints){
		List<Double> points = new ArrayList<>();
		double totalDistance = getTotalDistance();
		double distance = 0.0;

		for (Stations  station: stations) {

			if (station.getType().equals("Cycling"))
			   points.add(station.getDistancing() / totalDistance + disciplineChangePoints.get(0));
			else if (station.getType().equals("Pedestrianism"))
			   points.add(station.getDistancing() / totalDistance + disciplineChangePoints.get(1));
			else
			   points.add(station.getDistancing()/totalDistance);
		}
		return points;
	}
	
	

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    StringBuilder sb = new StringBuilder();
	    sb.append("Race: \n City: ").append(city).append("\n Date: ").append(dateFormat.format(date)).append("\n Condition: ")
	      .append(condition).append("\n");
	    for (Athlete a: athlete) {
	    	sb.append(a);
	    }
	    sb.append("\n Modality: ").append(modality).append("\n Km Swimming: ").append(kmswimming).append("\n Km Cyclism: ")
	      .append(kmcyclism).append("\n Km Pedestrianism: ").append(kmpedestrianism).append("\n Stations: ");
	    for (Stations s: stations) {
	    	sb.append(s);
	    }
	    sb.append("\n");
	    
		return sb.toString();
	}
	
	

}
