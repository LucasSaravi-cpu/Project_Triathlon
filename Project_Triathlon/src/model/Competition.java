package model;

import java.util.List;
import java.util.Set;

public class Competition {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	private int position;
	private String athleteName;
	private Set<DisciplineDistance> distances;
	private List<AthletePenalty> penalties;
	private Race race;

	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	public Competition(int position, String athleteName, Set<DisciplineDistance> distances,
			List<AthletePenalty> penalties, Race race) {
		super();
		this.position = position;
		this.athleteName = athleteName;
		this.distances = distances;
		this.penalties = penalties;
		this.race = race;
	}
	
	 //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	public Set<DisciplineDistance> getDistances() {
		return distances;
	}

	public void setDistances(Set<DisciplineDistance> distances) {
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

}

