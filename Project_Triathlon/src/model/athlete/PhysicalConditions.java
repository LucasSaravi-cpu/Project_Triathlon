package model.athlete;

import java.io.Serializable;

public class PhysicalConditions implements Serializable {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
    private double swimmingAptitude;
    private double cyclingAptitude;
    private double pedestrianismAptitude;
    private double stamina;
    private double mentalStrength;
    
    
  //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
    
    
	public PhysicalConditions(double swimmingAptitude, double cyclingAptitude, double pedestrianismAptitude,
							  double stamina, double mentalStrength) {
		super();
		this.swimmingAptitude = swimmingAptitude;
		this.cyclingAptitude = cyclingAptitude;
		this.pedestrianismAptitude = pedestrianismAptitude;
		this.stamina = stamina;
		this.mentalStrength = mentalStrength;
	}
	
	  //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
    
	
	public double getSwimmingAptitude() {
		return swimmingAptitude;
	}
	public void setSwimmingAptitude(double swimmingAptitude) {
		this.swimmingAptitude = swimmingAptitude;
	}
	public double getCyclingAptitude() {
		return cyclingAptitude;
	}
	public void setCyclingAptitude(double cyclingAptitude) {
		this.cyclingAptitude = cyclingAptitude;
	}
	public double getPedestrianismAptitude() {
		return pedestrianismAptitude;
	}
	public void setPedestrianismAptitude(double pedestrianismAptitude) {
		this.pedestrianismAptitude = pedestrianismAptitude;
	}
	public double getStamina() {
		return stamina;
	}
	public void setStamina(double stamina) {
		this.stamina = stamina;
	}
	public double getMentalStrength() {
		return mentalStrength;
	}
	public void setMentalStrength(double mentalStrength) {
		this.mentalStrength = mentalStrength;
	}
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" \n\t Swimming Aptitude: ").append(swimmingAptitude).append("\n\t Cycling Aptitude: ").append(cyclingAptitude)
		  .append("\n\t Pedestrianism Aptitude: ").append(pedestrianismAptitude).append("\n\t Stamina: ").append(stamina)
		  .append("\n\t Mental Strength: ").append(mentalStrength);
		return sb.toString();
	}
	
    
    

}
