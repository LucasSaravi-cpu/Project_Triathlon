package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public abstract class Athlete {
    private int num;
    private String surname;
    private String name;
  
    private String id;
    private Country nationality;
    private Date birthDate;
    private String gender;
    private double weight;
    private double height;
    private double percEndedRaces;
    private double economicBudget;
    private int ranking;
    private PhysicalConditions stats;
    
    
    
    
	public Athlete(int num, String name,String surname, String id, Country nationality, Date birthDate, String gender, double weight,
			double height, double percEndedRaces, double economicBudget, int ranking, PhysicalConditions stats) {
		super();
		this.num = num;
		this.name = name;
		this.surname = surname;
		this.id = id;
		this.nationality = nationality;
		this.birthDate = birthDate;
		this.gender = gender;
		this.weight = weight;
		this.height = height;
		this.percEndedRaces = percEndedRaces;
		this.economicBudget = economicBudget;
		this.ranking = ranking;
		this.stats = stats;
	}
	
	
	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Country getNationality() {
		return nationality;
	}
	public void setNationality(Country nationality) {
		this.nationality = nationality;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getPercEndedRaces() {
		return percEndedRaces;
	}
	public void setPercEndedRaces(double percEndedRaces) {
		this.percEndedRaces = percEndedRaces;
	}
	public double getEconomicBudget() {
		return economicBudget;
	}
	public void setEconomicBudget(double economicBudget) {
		this.economicBudget = economicBudget;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public PhysicalConditions getStats() {
		return stats;
	}
	public void setStats(PhysicalConditions stats) {
		this.stats = stats;
	}
	public int getAge() {
        LocalDate birthDateLocal = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDateLocal, currentDate).getYears();
    }
    public abstract String getCathegory();
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Athlete: ").append(num).append("\n Surname: ").append(surname).append("\n Name: ").append(name)
		  .append("\n ID: ").append(id).append("\n Nationality: ").append(nationality).append(" \n Birth Date: ")
		  .append(birthDate).append("\n Gender: ").append(gender).append("\n Weight: ").append(weight).append("\n Height: ")
		  .append(height).append("\n Percentage of Ended Races: ").append(percEndedRaces).append("\n Economic Budget: ")
		  .append(economicBudget).append("\n Ranking: ").append(ranking).append("\n Stats: ").append(stats).append("\n");
		
		return sb.toString();
	}

}
