package model.athlete;

import model.athlete.penalties.AthletePenalty;
import model.race.Race;
import model.race.discipline.DisciplineDistance;

import java.util.ArrayList;
import java.util.List;

public class Competition {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	private int position;
	private String athleteName;
	private List<DisciplineDistance> distances;
	private List<AthletePenalty> penalties;
	private Race race;
    private String TimeTot;

	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	public Competition(int position, String athleteName, List<DisciplineDistance> distances,
			List<AthletePenalty> penalties, Race race) {
		super();
		this.position = position;
		this.athleteName = athleteName;
		this.distances = distances;
		this.penalties = penalties;
		this.race = race;
		this.distances = new ArrayList<>();
	}
	
	 //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	public List<DisciplineDistance> getDistances() {
		return distances;
	}

	public void setDistances(List<DisciplineDistance> distances) {
		this.distances = distances;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getAthleteName() {
		return athleteName;
	}

	public void setAthleteName(String athleteName) {
		this.athleteName = athleteName;
	}

	public List<AthletePenalty> getPenalties() {
		return penalties;
	}

	public void setPenalties(List<AthletePenalty> penalties) {
		this.penalties = penalties;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}


	
		
	
	public String getTimeTot() {
		return TimeTot;
	}

	public void setTimeTot(String timeTot) {
		TimeTot = timeTot;
	}

}

