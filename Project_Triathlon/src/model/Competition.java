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
	private int points;
	private int stagesWinS;
    private int stagesWinP;
    private int stagesWinC;
    private int racerWin;
    private int racerdesertion;
    private int racercomplete;
    private String TimeTot;

	
	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	
	public Competition(int position, String athleteName, Set<DisciplineDistance> distances,
			List<AthletePenalty> penalties, Race race) {
		super();
		this.position = position;
		this.athleteName = athleteName;
		this.distances = distances;
		this.penalties = penalties;
		this.race = race;
		this.points=0;
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

	public void setPoints(int points){
		this.points=points;
	}
	public int getPoints(){
		return points;
	}
	
		
	
	public String getTimeTot() {
		return TimeTot;
	}

	public void setTimeTot(String timeTot) {
		TimeTot = timeTot;
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
				points += (43 - position); // Los siguientes atletas reciben menos puntos según su posición
				break;
		}
	}
	
	
	
	
}

