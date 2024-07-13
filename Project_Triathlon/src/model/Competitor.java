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
}
