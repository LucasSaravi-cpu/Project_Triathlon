package model.athlete;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import Events.EnergyEvent;
import listeners.EnergyListener;
import model.race.Race;
import model.race.discipline.Discipline;
import model.race.location.Country;

public abstract class Athlete implements Serializable {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
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
    private double energy;
    private final List<EnergyListener> listeners = new ArrayList<>();
    private List<Competition> competition;
	private Discipline currentDiscipline;
	private int stagesWinS;
	private int stagesWinP;
	private int stagesWinC;
	private int racedesertions;
	private int finishedRaces;
	private int points;
	private int victories;
	private int currentStation;
	private boolean neoprene;
    private int userSpeedAdjustment;

    
 
   //------------------------------------------------>||VARIABLE||<--------------------------------------------------------\\

	public static final double K = 3.5;
	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
    
	public Athlete(int num, String name,String surname, String id, Country nationality, Date birthDate, String gender, double weight,
			double height, double percEndedRaces, double economicBudget, int ranking, PhysicalConditions stats, List<Competition> competition) {
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
		this.competition=competition;
		this.stagesWinS=0;
		this.stagesWinC=0;
		this.stagesWinP=0;
		this.finishedRaces=0;
		this.racedesertions=0;
		this.points=0;
		this.victories=0;
		this.currentStation=0;
	}
	
	   //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
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

	public int getStagesWinS() {
		return stagesWinS;
	}

	public void setStagesWinS(int stagesWinS) {
		this.stagesWinS = stagesWinS;
	}

	public int getStagesWinP() {
		return stagesWinP;
	}

	public void setStagesWinP(int stagesWinP) {
		this.stagesWinP = stagesWinP;
	}

	public int getStagesWinC() {
		return stagesWinC;
	}

	public void setStagesWinC(int stagesWinC) {
		this.stagesWinC = stagesWinC;
	}

	public int getVictories() {
		return victories;
	}

	public void setVictories(int victories) {
		this.victories = victories;
	}

	public int getRacedesertions() {
		return racedesertions;
	}

	public void setRacedesertions(int racedesertions) {
		this.racedesertions = racedesertions;
	}

	public int getFinishedRaces() {
		return finishedRaces;
	}

	public void setFinishedRaces(int finishedRaces) {
		this.finishedRaces = finishedRaces;
	}

	public void setPoints(int points){
		this.points=points;
	}
	public int getPoints(){
		return points;
	}



	public List<Competition> getCompetition() {
		return competition;
	}

	public void setCompetition(List<Competition> competition) {
		this.competition = competition;
	}
	
	   public double getEnergy() {
				return energy;
			
	   }
	   public void setEnergy(double energy) {
			
			this.energy = energy;
	        notifyEnergyChange(energy);
		}
		public int getCurrentStation(){
		    return currentStation;
		}
		public void setCurrentStation(int station){
		    this.currentStation = station;
		}

	public void setCurrentDiscipline(Discipline currentDiscipline) {
		this.currentDiscipline = currentDiscipline;
	}
	
	
	
	public Discipline getCurrentDiscipline() {
		return currentDiscipline;
	}
	public void setNewDiscipline(){
		this.currentDiscipline = currentDiscipline.getNewDiscipline();
	}
	public int getUserSpeedAdjustment(){
		return userSpeedAdjustment;
	}
	public void setUserSpeedAdjustment(int userSpeedAdjustment){
		this.userSpeedAdjustment=userSpeedAdjustment;
	}
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\

	public int getSpeed() {
		return 500 - (userSpeedAdjustment-5) * 50;
	}
	public int getPositionChange(Race race) {
		Random random = new Random();
		return 7 + (int) (currentDiscipline.getBaseSpeed(stats, race.getCurrentWeatherCondition())/4) + random.nextInt(5);
	}

	public int getAge() {
		LocalDate birthDateLocal = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate currentDate = LocalDate.now();
		return Period.between(birthDateLocal, currentDate).getYears();
	}
	

   public abstract String getCategory();
    
   public abstract String  setNeopreneUsage(double distance, double waterTemperature);
   
   public abstract Double setMaximumNeopreneTime(double distance);
   
   public void updateEnergy(double height, double weight, double mentalStrength, double stamina) {
       double newEnergy = K * (height * weight) * (stamina + mentalStrength);
       setEnergy(newEnergy);
       
   }
    /*
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
	*/
	
	public String listStats(){
		return stats.toString();
	}
	
	 public void addEnergyListener(EnergyListener listener) {
	        listeners.add(listener);
	    }

	    public void removeEnergyListener(EnergyListener listener) {
	        listeners.remove(listener);
	    }
	    
	    public void decreaseEnergy(double amount) {
	        energy -= amount;
	        if (energy < 0) {
	            energy = 0;
	        }
	        setEnergy(energy);
	    }

	    public void increaseEnergy(double amount) {
	        setEnergy(energy + amount);
	    }
	    
	 
	    private void notifyEnergyChange(double newEnergy) {
	        EnergyEvent event = new EnergyEvent(this, newEnergy);
	        for (EnergyListener listener : listeners) {
	            listener.energyChanged(event);
	        }
	    }

	public void updatePoints(int position) {
		switch(position) {
			case 1:
				points += 100;
				break;
			case 2:
				points += 85;
				break;
			case 3:
				points += 70;
				break;
			case 4:
				points += 60;
				break;
			case 5:
				points += 50;
				break;
			default:
				points += (43 - position);
				break;
		}
	}

    public void addStageWin(){
		switch (currentDiscipline.toString()) {
			case "Swimming":
				addStageWinS();
				break;
			case "Cycling":
				addStageWinC();
				break;
			default:
				addStageWinP();
		}
	}
	public void addStageWinS() {
		stagesWinS++;
	}
	public void addStageWinC(){
		stagesWinC++;
	}
	public void addStageWinP(){
		stagesWinP++;
	}
	public void addFinishedRaces(){
		finishedRaces++;
	}
	public void addRaceDesertions(){
		racedesertions++;
	}
	public void addVictory(){
		victories++;
	}
	

	
	public boolean isUsingNeoprene() {
		return neoprene;
	}
	public void setNeoprene(boolean neoprene) {
		this.neoprene = neoprene;
	}

	public String toString(){
	   StringBuilder sb = new StringBuilder();
		sb.append("\n");

		sb.append(" Surname: ").append(surname).append("\n Name: ").append(name)
				.append("\n Nationality: ").append(nationality).append("\n Category: ").append(getCategory()) .append("\n Stage Wins (Swimming): ").append(stagesWinS)
				.append("\n Stage Wins (Pedestrianism): ").append(stagesWinP).append("\n Stage Wins (Cycling): ").append(stagesWinC)
				.append("\n Races Won: ").append(victories).append("\n Race Desertions: ").append(racedesertions)
				.append("\n Races Completed: ").append(finishedRaces);

		sb.append("\n");
	   return sb.toString();
	}
}
