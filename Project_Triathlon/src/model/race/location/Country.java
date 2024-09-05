package model.race.location;

import java.io.Serializable;

public class Country implements Serializable {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

	private String namecountry;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	public Country(String namecountry) {
		super();
		this.namecountry = namecountry;
	}
	
	 //------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\

	@Override
	public String toString() {
		return namecountry;
	}
	


	
	
	

}
