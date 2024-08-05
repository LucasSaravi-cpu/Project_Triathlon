package model;

import java.util.Date;

public class Amateur extends Athlete {

	public Amateur(int num, String name, String surname, String id, Country nationality, Date birthDate, String gender,
			double weight, double height, double percEndedRaces, double economicBudget, int ranking,
			PhysicalConditions stats) {
		super(num, name, surname, id, nationality, birthDate, gender, weight, height, percEndedRaces, economicBudget, ranking,
				stats);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getCathegory() {
		int age = getAge();
		if (age >= 19 && age <= 24)
			return "19-24 years old";
		else if (age >= 25 && age <= 29)
			return "25-29 years old";
		else if (age >= 30 && age <= 34)
			return "30-34 years old";
		else if (age >= 35 && age <= 39)
			return "35-39 years old";
		else if (age >= 40 && age <= 44)
			return "40-44 years old";
		else if (age >= 45 && age <= 49)
			return "45-49 years old";
		else if (age >= 50 && age <= 54)
			return "50-54 years old";
		else if (age >= 55 && age <= 59)
			return "55-59 years old";
		else if (age >= 60 && age <= 64)
			return "60-64 years old";
		else if (age >= 65 && age >= 69)
			return "65-69 years old";
		else if (age >= 70 && age >= 74)
			return "70-74 years old";
		else if (age >= 75)
			return "more than 75 years old";
		else 
			return "not old enough";
	}
  

	
	@Override
	public String checkNeopreneUsage(int distanceInMeters, double waterTemperature) {
	    String neoprene = "";

	    double distanceInKm = distanceInMeters / 1000.0;

	    if (distanceInKm <= 0.75) {
	        if (waterTemperature > 15 && waterTemperature <= 20) {
	            neoprene = "Usable";
	        } else if (waterTemperature <= 15) {
	            neoprene = "Obligatory";
	        }
	    } else if (distanceInKm > 0.75 && distanceInKm <= 1.5) {
	        if (waterTemperature > 15 && waterTemperature <= 20) {
	            neoprene = "Usable";
	        } else if (waterTemperature <= 15) {
	            neoprene = "Obligatory";
	        }
	    } else if (distanceInKm >= 1.501 && distanceInKm <= 3.0) {
	        if (waterTemperature > 16 && waterTemperature <= 22) {
	            neoprene = "Usable";
	        } else if (waterTemperature <= 16) {
	            neoprene = "Obligatory";
	        }
	    } else if (distanceInKm >= 3.001 && distanceInKm <= 4.0) {
	        if (waterTemperature > 17 && waterTemperature <= 23) {
	            neoprene = "Usable";
	        } else if (waterTemperature <= 17) {
	            neoprene = "Obligatory";
	        }
	    }

	    return neoprene;
	}




}
