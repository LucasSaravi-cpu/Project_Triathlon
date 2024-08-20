package model;

import java.util.*;

public class RaceManager {
	
	//------------------------------------------------>||VARIABLE||<--------------------------------------------------------\\
	
    private static List<Athlete> finishedAthletes = new ArrayList<>();
    
    
    private String timerTot="";
    
    public static void clearFinishedAthletes(){
        finishedAthletes.clear();
    }
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
    
    public synchronized void notifyAthleteFinished(Athlete athlete) {
        finishedAthletes.add(athlete);
        athlete.getCompetition().updatePoints(finishedAthletes.size());
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
            sb.append(positionCounter).append(": ").append(finishedAthlete.getName()).append(" ").append(finishedAthlete.getSurname()).append("\n").append("Time : ").append(finishedAthlete.getCompetition().getTimeTot()).append("\n");
            
         
            if (positionCounter==1) {
            	
            	 timerTot= finishedAthlete.getCompetition().getTimeTot();
            	 sb.append("-------------------------------------------------------------------- \n");
            	
            }
            
            
            if (positionCounter!= 1) {
            	
            	sb.append("Difference: ").append(subtractTimes(timerTot,finishedAthlete.getCompetition().getTimeTot())).append("\n");
            	sb.append("-------------------------------------------------------------------- \n");
            }
            
            
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
    
    
    public static String subtractTimes(String time1, String time2) {
        // Parsear los tiempos
        int[] time1Components = parseTime(time1);
        int[] time2Components = parseTime(time2);
        
        // Convertir ambos tiempos a segundos
        int seconds1 = toSeconds(time1Components);
        int seconds2 = toSeconds(time2Components);
        
        // Restar los segundos
        int differenceInSeconds = Math.abs(seconds1 - seconds2);
        
        // Convertir la diferencia en horas, minutos y segundos
        int hours = differenceInSeconds / 3600;
        int minutes = (differenceInSeconds % 3600) / 60;
        int seconds = differenceInSeconds % 60;
        
        // Formatear el resultado
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private static int[] parseTime(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        return new int[] { hours, minutes, seconds };
    }

    private static int toSeconds(int[] timeComponents) {
        return timeComponents[0] * 3600 + timeComponents[1] * 60 + timeComponents[2];
    }
    
}