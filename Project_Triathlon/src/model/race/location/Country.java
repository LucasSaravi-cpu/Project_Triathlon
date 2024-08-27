package model.race.location;

public class Country {
	
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
