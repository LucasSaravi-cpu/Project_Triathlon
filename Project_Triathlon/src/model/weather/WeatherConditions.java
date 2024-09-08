package model.weather;

import model.race.discipline.Discipline;

import java.io.Serializable;

public class WeatherConditions implements Serializable {
	
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
    private int id;
    private String description;
    private MeasurementUnit measurementUnit;
    private double lowertier;
    private double uppertier;
    private double swimmingImpact;
    private double cyclingImpact;
    private double pedestrianismImpact;
    private double CurrentTemperature;
    
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
    
	public WeatherConditions(int id, String description, MeasurementUnit measurementUnit, double lowertier,
							 double uppertier, double swimmingImpact, double cyclingImpact, double pedestrianismImpact) {
		super();
		this.id = id;
		this.description = description;
		this.measurementUnit = measurementUnit;
		this.lowertier = lowertier;
		this.uppertier = uppertier;
		this.swimmingImpact = swimmingImpact;
		this.cyclingImpact = cyclingImpact;
		this.pedestrianismImpact = pedestrianismImpact;
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	
	public MeasurementUnit getMeasurementUnit() {
		return measurementUnit;
	}
	public void setMeasurementUnit(MeasurementUnit measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	public double getLowerTier() {
		return lowertier;
	}
	public void setLowertier(double lowertier) {
		this.lowertier = lowertier;
	}
	public double getUpperTier() {
		return uppertier;
	}
	public void setUppertier(double uppertier) {
		this.uppertier = uppertier;
	}
	public double getWeather(Discipline discipline){
		switch (discipline.getClass().getSimpleName()) {
			case "Swimming": return swimmingImpact;
			case "Cycling": return cyclingImpact;
			default: return pedestrianismImpact;
		}
	}
	public double getSwimmingImpact() {
		return swimmingImpact;
	}
	public void setSwimmingImpact(double swimmingImpact) {
		this.swimmingImpact = swimmingImpact;
	}
	public double getCyclingImpact() {
		return cyclingImpact;
	}
	public void setCyclingImpact(double cyclingImpact) {
		this.cyclingImpact = cyclingImpact;
	}
	public double getPedestrianismImpact() {
		return pedestrianismImpact;
	}
	public void setPedestrianismImpact(double pedestrianismImpact) {
		this.pedestrianismImpact = pedestrianismImpact;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public double getCurrentTemperature() {
		return CurrentTemperature;
	}

	public void setCurrentTemperature(double currentTemperature) {
		CurrentTemperature = currentTemperature;
	}
    

	@Override
	public String toString() {
		return description +"\n"+ "("+lowertier+"/"+uppertier+")" + measurementUnit+ "\n" + "Decreased vel "+"\n"
				+"swimming "+ swimmingImpact + "%"+"\n"
				+"cycling "+ cyclingImpact + "%" +"\n"
				+"pedestrianism "+ pedestrianismImpact + "%";
	}


    
    
    
   

}
