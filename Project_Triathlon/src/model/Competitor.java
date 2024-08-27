package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Competitor extends Athlete {
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

	public Competitor(int num, String name, String surname, String id, Country nationality, Date birthDate,
			String gender, double weight, double height, double percEndedRaces, double economicBudget, int ranking,
			PhysicalConditions stats, List<Competition> competition) {
		super(num, name, surname, id, nationality, birthDate, gender, weight, height, percEndedRaces, economicBudget, ranking,
				stats,competition);
		// TODO Auto-generated constructor stub
	}

	
	 //------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	@Override
	public String getCathegory() {
		int age = getAge();
		if (age < 15)
			return "not old enough";
		else if (age >= 15 && age <= 18)
			return "Junior";
		else if (age >=19 && age <= 23)
			return "Sub 23";
		else
			return "Elite";
	}
	
	
	@Override
	  // Method to check neoprene usage based on water temperature and distance
    public String checkNeopreneUsage(double distance, double waterTemperature) {

		String neoprene="";
	  
		
		double distanceInMeters = distance * 1000.0;

	    if (distanceInMeters <= 750) {
	        if (waterTemperature > 20) {
	            neoprene = "Prohibited";
	        } else if (waterTemperature >= 15 && waterTemperature <= 20) {
	            neoprene = "Usable";
	        } else if (waterTemperature < 15) {
	            neoprene = "Obligatory";
	        }
	    } else if (distanceInMeters > 750 && distanceInMeters <= 1500) {
	        if (waterTemperature > 20) {
	            neoprene = "Prohibited";
	        } else if (waterTemperature >= 15 && waterTemperature <= 20) {
	            neoprene = "Usable";
	        } else if (waterTemperature < 15) {
	            neoprene = "Obligatory";
	        }
	    } else if (distanceInMeters >= 1501 && distanceInMeters <= 3000) {
	        if (waterTemperature > 22) {
	            neoprene = "Prohibited";
	        } else if (waterTemperature >= 15 && waterTemperature <= 22) {
	            neoprene = "Usable";
	        } else if (waterTemperature < 15) {
	            neoprene = "Obligatory";
	        }
	    } else if (distanceInMeters >= 3001 && distanceInMeters <= 4000) {
	        if (waterTemperature > 23) {
	            neoprene = "Prohibited";
	        } else if (waterTemperature >= 16 && waterTemperature <= 23) {
	            neoprene = "Usable";
	        } else if (waterTemperature < 16) {
	            neoprene = "Obligatory";
	        }
	    }

        
        return neoprene;
        
    }

	@Override
	public Double allowedNeopreneMinutes(double distance) {
	
		
		double distanceInKm = distance/ 1000.0;
		double time=0;
		
		  if (distanceInKm <= 0.75) {
			  
			time=20; 
			  
		  }
	   else if (distanceInKm > 0.75 && distanceInKm <= 1.5) {
		   
		   time = 40;
	   }
		  
	   else if (distanceInKm >= 1.501 && distanceInKm <= 3.0) {
		   
		   time = 90;
	   }
	   else if (distanceInKm >= 3.001 && distanceInKm <= 4.0) {
		   
		   time = 140;
	   
	   }
		  
		
		return time;
	}

	
}
