package model.race.location;

import java.io.Serializable;

public class City implements Serializable {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
	private String namecity;
    private Country country;
    

    
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
    
	public City(String namecity, Country country) {
		super();
		this.namecity = namecity;
		this.country = country;
	}
	
	 //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
	public String getNamecity() {
		return namecity;
	}
	public void setNamecity(String namecity) {
		this.namecity = namecity;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}

	  //------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	@Override
	public String toString() {
		return namecity + ", " + country;
	}



    
	
	
    
    
    
}
