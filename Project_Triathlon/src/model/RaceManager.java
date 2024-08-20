package model;

import java.util.*;

public class RaceManager {
	
	//------------------------------------------------>||VARIABLE||<--------------------------------------------------------\\
	
    private static List<Athlete> finishedAthletes = new ArrayList<>();
    
    public static void clearFinishedAthletes(){
        finishedAthletes.clear();
    }
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
    
    public synchronized void notifyAthleteFinished(Athlete athlete) {
        finishedAthletes.add(athlete);
        athlete.getCompetition().updatePoints(finishedAthletes.size());
       }
     
    
    public static String updateRaceResults() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Race results: \n");
       
        for (int i = 0; i < finishedAthletes.size(); i++) {
        	sb.append((i + 1) + ": " + finishedAthletes.get(i).getName() + "\n");
        }
        
        return sb.toString();
    }
    public synchronized String getCurrentPositions(List<RaceThread> raceThreads) {
        StringBuilder sb = new StringBuilder();
        sb.append("Current Positions: \n");
        Map<Athlete, Integer> athletePositions = new HashMap<>();
        for (RaceThread thread: raceThreads) {
            Integer position = new Integer(thread.getPositionX());
            athletePositions.put(thread.getAthlete(), thread.getPositionX());
        }
        List<Map.Entry<Athlete, Integer>> sortedEntries = new ArrayList<>(athletePositions.entrySet());
        sortedEntries.sort(Map.Entry.<Athlete, Integer>comparingByValue().reversed());

        int positionCounter = 1;
        for (Athlete finishedAthlete : finishedAthletes) {
            sb.append(positionCounter).append(": ").append(finishedAthlete.getName()).append(" ").append(finishedAthlete.getSurname()).append("\n");
            positionCounter++;
        }

        for (Map.Entry<Athlete, Integer> entry : sortedEntries) {
            Athlete athlete = entry.getKey();
            if (!finishedAthletes.contains(athlete)) {
                sb.append(positionCounter).append(": ").append(athlete.getName()).append(" ").append(athlete.getSurname()).append("\n");
                positionCounter++;
            }
        }

        return sb.toString();
    }
}