package model;

import java.util.Date;
import java.util.List;

public class Career {
    private City city;
    private Date date;
    private List <WeatherConditions> condition;
    private List<Athlete> athlete;
    private Modality modality;
    private double kmswimming;
    private double kmcyclism;
    private double  kmpedestrianism;
    private List<Stations> stations ;
    



	public Career(City city, Date date, List<Athlete> athlete, Modality modality, double kmswimming, double kmcyclism,
			double kmpedestrianism, List<Stations> stations) {
		super();
		this.city = city;
		this.date = date;
		this.athlete = athlete;
		this.modality = modality;
		this.kmswimming = kmswimming;
		this.kmcyclism = kmcyclism;
		this.kmpedestrianism = kmpedestrianism;
		this.stations = stations;
	}



	public City getCity() {
		return city;
	}



	public void setCity(City city) {
		this.city = city;
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

	

   
    

}
