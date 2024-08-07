package model;

import java.util.ArrayList;
import java.util.List;

public class RaceManager {
    private static List<Athlete> finishedAthletes = new ArrayList<>();
    
    public synchronized void notifyAthleteFinished(Athlete athlete) {
        finishedAthletes.add(athlete);
       }
    
    public static String updateRaceResults() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Orden de llegada: \n");
       
        for (int i = 0; i < finishedAthletes.size(); i++) {
        	sb.append((i + 1) + ": " + finishedAthletes.get(i).getName() + "\n");
        }
        
        return sb.toString();
    }
}