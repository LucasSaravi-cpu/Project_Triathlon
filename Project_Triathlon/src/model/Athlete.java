package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Events.EnergyEvent;
import listeners.EnergyListener;

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
    private double energy;
    private final List<EnergyListener> listeners = new ArrayList<>();
    private int stagesWinS;
    private int stagesWinP;
    private int stagesWinC;
    private int racerWin;
    private int racerdesertion;
    private int racercomplete;
    
 
   

	public static final double K = 3.5;
    
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


	public int getRacerWin() {
		return racerWin;
	}


	public void setRacerWin(int racerWin) {
		this.racerWin = racerWin;
	}


	public int getRacerdesertion() {
		return racerdesertion;
	}


	public void setRacerdesertion(int racerdesertion) {
		this.racerdesertion = racerdesertion;
	}


	public int getRacercomplete() {
		return racercomplete;
	}


	public void setRacercomplete(int racercomplete) {
		this.racercomplete = racercomplete;
	}


	public abstract String getCathegory();
    
   public abstract String checkNeopreneUsage(int distance, double waterTemperature);
   
   public void updateEnergy(double height, double weight, double mentalStrength, double stamina) {
       double newEnergy = K * (height * weight) * (stamina + mentalStrength);
       setEnergy(newEnergy);
       
   }
    
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
	public String listStats()
	{
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
	        energy += amount;
	        if (energy > 100) { 
	            energy = 100;
	        }
	        setEnergy(energy);
	    }
	    
	    public double getEnergy() {
			return energy;
		}
	    private void notifyEnergyChange(double newEnergy) {
	        EnergyEvent event = new EnergyEvent(this, newEnergy);
	        for (EnergyListener listener : listeners) {
	            listener.energyChanged(event);
	        }
	    }

		public void setEnergy(double energy) {
			
			this.energy = energy;
	        notifyEnergyChange(energy);
		}
}
