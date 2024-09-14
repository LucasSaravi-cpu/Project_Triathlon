package model.race;

import model.athlete.Athlete;
import model.athlete.type.Amateur;
import model.athlete.type.Competitor;
import model.race.thread.RaceThread;

import java.util.*;
import java.util.stream.Collectors;

public class RaceManager {
	
	//------------------------------------------------>||ATRIBUTTES||<--------------------------------------------------------\\
	
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
    
    public List<Athlete> getFinishedAthletes() {
        return finishedAthletes;
    }

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



    public synchronized String getCurrentPositions(List<RaceThread> raceThreads, String category, String subcategory) {
        StringBuilder sb = new StringBuilder();
        sb.append("Current Positions: \n");

        Map<Athlete, Integer> athletePositions = new HashMap<>();
        for (RaceThread thread : raceThreads) {
            athletePositions.put(thread.getAthlete(), thread.getPositionX());
        }

        List<Athlete> sortedFinishedAthletes = finishedAthletes.stream()
                .filter(athlete -> {
                    if ("All".equalsIgnoreCase(category)) {
                        return true;
                    }
                    if ("All".equalsIgnoreCase(subcategory)) {
                        if ("Competitor".equalsIgnoreCase(category) && athlete instanceof Competitor) {
                            return true;
                        } else if ("Amateur".equalsIgnoreCase(category) && athlete instanceof Amateur) {
                            return true;
                        }
                    }
                    return athlete.getCategory().equalsIgnoreCase(subcategory);
                })
                .sorted(Comparator.comparing(a -> a.getCompetition().get(raceIndex).getTimeTot()))
                .collect(Collectors.toList());

        int positionCounter = 1;
        for (Athlete finishedAthlete : sortedFinishedAthletes) {
            sb.append(positionCounter).append(": ").append(finishedAthlete.getName()).append(" ").append(finishedAthlete.getSurname()).append("\n")
                    .append("Time : ").append(finishedAthlete.getCompetition().get(raceIndex).getTimeTot()).append("\n");

            if (positionCounter == 1) {
                timerTot = finishedAthlete.getCompetition().get(raceIndex).getTimeTot();
                sb.append("-------------------------------------------------------------------- \n");
            } else {
                sb.append("Difference: ").append(Chronometer.subtractTimes(timerTot, finishedAthlete.getCompetition().get(raceIndex).getTimeTot())).append("\n")
                        .append("-------------------------------------------------------------------- \n");
            }

            positionCounter++;
        }

        List<Athlete> sortedNotFinishedAthletes = athletePositions.keySet().stream()
                .filter(athlete -> !finishedAthletes.contains(athlete))
                .filter(athlete -> {
                    if ("All".equalsIgnoreCase(category)) {
                        return true;
                    }
                    if ("All".equalsIgnoreCase(subcategory)) {
                        if ("Competitor".equalsIgnoreCase(category) && athlete instanceof Competitor) {
                            return true;
                        } else if ("Amateur".equalsIgnoreCase(category) && athlete instanceof Amateur) {
                            return true;
                        }
                    }
                    return athlete.getCategory().equalsIgnoreCase(subcategory);
                })
                .sorted(Comparator.comparingInt(athletePositions::get).reversed())
                .collect(Collectors.toList());

        for (Athlete athlete : sortedNotFinishedAthletes) {
            sb.append(positionCounter).append(": ").append(athlete.getName()).append(" ").append(athlete.getSurname()).append("\n");

            athlete.getCompetition().get(raceIndex).getDistances().stream()
                    .filter(distance -> "Forfeited".equals(distance.getTime()))
                    .findFirst()
                    .ifPresent(distance -> sb.append("Forfeited - Discipline: ").append(distance.getDiscipline()).append(" - Distance: ").append(distance.getDiscipline().getTotalDistance(athlete.getCompetition().get(raceIndex).getDistances())).append(" km. \n"));
            positionCounter++;
        }

        return sb.toString();
    }
}