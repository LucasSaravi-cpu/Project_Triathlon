package model;

public class City {
	
	private String namecity;
    private Country country;
    

    
    
    
	public City(String namecity, Country country) {
		super();
		this.namecity = namecity;
		this.country = country;
	}
	
	
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


	@Override
	public String toString() {
		return namecity + ", " + country;
	}



    
	
	
    
    
    
}
