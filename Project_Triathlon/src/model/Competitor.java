package model;

import java.time.LocalDate;
import java.util.Date;

public class Competitor extends Athlete {

	public Competitor(int num, String name, String surname, String id, Country nationality, Date birthDate,
			String gender, double weight, double height, double percEndedRaces, double economicBudget, int ranking,
			PhysicalConditions stats) {
		super(num, name, surname, id, nationality, birthDate, gender, weight, height, percEndedRaces, economicBudget, ranking,
				stats);
		// TODO Auto-generated constructor stub
	}

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
    public String checkNeopreneUsage(int distance, double waterTemperature) {

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

	
	
	
}
