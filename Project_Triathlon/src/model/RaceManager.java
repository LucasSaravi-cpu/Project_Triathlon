package model;

import java.util.*;

public class RaceManager {
	
	//------------------------------------------------>||VARIABLE||<--------------------------------------------------------\\
	
    private static List<Athlete> finishedAthletes = new ArrayList<>();
    private String timerTot="";
    private int raceIndex;
    private Map<String, Boolean> disciplineWins = new HashMap<>();
    public static void clearFinishedAthletes(){
        finishedAthletes.clear();
    }

    //------------------------------------------------>||CONSTRUCTORS|<--------------------------------------------------------\\

    public RaceManager(int raceIndex){
        this.raceIndex=raceIndex;
        disciplineWins.put("swimming", false);
        disciplineWins.put("cycling", false);
        disciplineWins.put("running", false);
    }
    //------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
    public boolean isDisciplineWon(String discipline) {
        return disciplineWins.getOrDefault(discipline, false);
    }

    public void markDisciplineAsWon(String discipline) {
        disciplineWins.put(discipline, true);
    }
    public synchronized void notifyAthleteFinished(Athlete athlete) {
        if (finishedAthletes.size()==0) {
            athlete.addVictory();
            athlete.addStageWinP();
        }
        finishedAthletes.add(athlete);
        athlete.addFinishedRaces();
        athlete.updatePoints(finishedAthletes.size());
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
            sb.append(positionCounter).append(": ").append(finishedAthlete.getName()).append(" ").append(finishedAthlete.getSurname()).append("\n").append("Time : ").append(finishedAthlete.getCompetition().get(raceIndex).getTimeTot()).append("\n");


            if (positionCounter==1) {

                timerTot= finishedAthlete.getCompetition().get(raceIndex).getTimeTot();
                sb.append("-------------------------------------------------------------------- \n");

            }


            if (positionCounter!= 1) {

                sb.append("Difference: ").append(Chronometer.subtractTimes(timerTot,finishedAthlete.getCompetition().get(raceIndex).getTimeTot())).append("\n");
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
}