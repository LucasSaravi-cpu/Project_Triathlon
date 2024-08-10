package model;

public class WeatherConditions {
	
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
    private int id;
    private String description;
    private MeasurementUnit measurementUnit;
    private double lowertier;
    private double uppertier;
    private double swimmingweathering;
    private double cyclingweathering;
    private double pedestrianismweathering;
    
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
    
	public WeatherConditions(int id, String description, MeasurementUnit measurementUnit, double lowertier,
			double uppertier, double swimmingweathering, double cyclingweathering, double pedestrianismweathering) {
		super();
		this.id = id;
		this.description = description;
		this.measurementUnit = measurementUnit;
		this.lowertier = lowertier;
		this.uppertier = uppertier;
		this.swimmingweathering = swimmingweathering;
		this.cyclingweathering = cyclingweathering;
		this.pedestrianismweathering = pedestrianismweathering;
	}
	
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	
	public MeasurementUnit getMeasurementUnit() {
		return measurementUnit;
	}
	public void setMeasurementUnit(MeasurementUnit measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	public double getLowertier() {
		return lowertier;
	}
	public void setLowertier(double lowertier) {
		this.lowertier = lowertier;
	}
	public double getUppertier() {
		return uppertier;
	}
	public void setUppertier(double uppertier) {
		this.uppertier = uppertier;
	}
	public double getSwimmingweathering() {
		return swimmingweathering;
	}
	public void setSwimmingweathering(double swimmingweathering) {
		this.swimmingweathering = swimmingweathering;
	}
	public double getCyclingweathering() {
		return cyclingweathering;
	}
	public void setCyclingweathering(double cyclingweathering) {
		this.cyclingweathering = cyclingweathering;
	}
	public double getPedestrianismweathering() {
		return pedestrianismweathering;
	}
	public void setPedestrianismweathering(double pedestrianismweathering) {
		this.pedestrianismweathering = pedestrianismweathering;
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

	@Override
	public String toString() {
		return "WeatherConditions [id=" + id + ", description=" + description + ", measurementUnit=" + measurementUnit
				+ ", lowertier=" + lowertier + ", uppertier=" + uppertier + ", swimmingweathering=" + swimmingweathering
				+ ", cyclingweathering=" + cyclingweathering + ", pedestrianismweathering=" + pedestrianismweathering
				+ "]";
	}
    
    
    
    
   

}
