package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Events.EnergyEvent;
import Events.DisciplineChangeEvent;
import listeners.EnergyListener;
import listeners.RaceListener;
import listeners.DisciplineChangeListener;
import java.util.concurrent.atomic.AtomicInteger;
import controller.Championship;
public class RaceThread extends Thread {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
    private int startX;
    private int positionX;
    private int endX;
    private RaceListener listener;
    private final List<EnergyListener> listeners = new ArrayList<>();
    private final List<DisciplineChangeListener> disciplineListeners = new ArrayList<>();
    private final Athlete athlete;
    private static AtomicInteger activeThreads = new AtomicInteger(0);
    private final Championship controller;
    private RaceManager raceManager;
    private Race race;
 
    
    //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

    public RaceThread(int startX, int positionX, int endX, RaceListener listener, Athlete athlete, Championship controller, RaceManager raceManager, Race race) {
        this.startX=startX;
        this.positionX = positionX;
        this.listener = listener;
        this.endX = endX;
        this.athlete = athlete;
        this.controller=controller;
        activeThreads.incrementAndGet();
        this.raceManager = raceManager;
        this.race=race;
    }
    public int getPositionX(){
        return positionX;
    }
    public Athlete getAthlete(){
        return athlete;
    }
    
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
    
    @Override
    public  void run() {
        Chronometer chronometer = Championship.getChronometer();
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Random random = new Random();
                moveLabel(chronometer);
                athlete.decreaseEnergy(10); // Adjust decrement
                Thread.sleep(random.nextInt((int)athlete.getBaseSpeed())); // Adjust thread speed
                // Notify Energy Change
                notifyEnergyChange(athlete.getEnergy());
               
                // Verifies if the athlete has energy
                if (athlete.getEnergy() <= 0) {
                    Thread.currentThread().interrupt(); // Stops thread if the athlete has no energy
                    athlete.getCompetition().addRaceDesertions();
                }
                
                    
            }
        } catch (InterruptedException e) {

        }  finally {
            if (activeThreads.decrementAndGet() == 0) {
                controller.allThreadsCompleted();
            }
        }

    }

    
    
    private void moveLabel(Chronometer chronometer) {
        if (positionX+10<=endX)
    	    positionX += 10; // Increments X position
        else {
        	positionX=endX;
        	Thread.currentThread().interrupt();
            athlete.getCompetition().addStageWinS();
            athlete.getCompetition().getDistances().add(new DisciplineDistance(race.getKmpedestrianism(), chronometer.getTime(), new Pedestrianism()));
            athlete.getCompetition().setTimeTot(Championship.getChronometer().getTime());
        	raceManager.notifyAthleteFinished(athlete);
        	 
        }
        checkForDisciplineChange(chronometer);
        if (listener != null) {
            listener.positionChanged(this, positionX);
        }
    }
    
    
    public void addEnergyListener(EnergyListener listener) {
        listeners.add(listener);
    }

    public void removeEnergyListener(EnergyListener listener) {
        listeners.remove(listener);
    }

    private void notifyEnergyChange(double newEnergy) {
        EnergyEvent event = new EnergyEvent(this, newEnergy);
        for (EnergyListener listener : listeners) {
            listener.energyChanged(event);
        }
    }
    private void checkForDisciplineChange(Chronometer chronometer) {
        double progress = (double) (positionX - startX) / (endX - startX);
        String currentTime = chronometer.getTime();
        if (progress >= race.getDisciplineChangePoints().get(0) + 70.0 / (endX-startX) && progress < race.getDisciplineChangePoints().get(0)+90.0/(endX-startX)) { // first line
            notifyDisciplineChange("cycling");
            if (!athlete.getCurrentDiscipline().getClass().equals(Cycling.class)){
                athlete.setCurrentDiscipline(new Cycling());
                athlete.getCompetition().addStageWinS();
                athlete.getCompetition().getDistances().add(new DisciplineDistance(race.getKmswimming(), currentTime, new Swimming()));
            }

        } else if (progress >= race.getDisciplineChangePoints().get(1)-30.0/(endX-startX) && progress<race.getDisciplineChangePoints().get(1)-10.0/(endX-startX)) { // Second line
            notifyDisciplineChange("running");
            if (!athlete.getCurrentDiscipline().getClass().equals(Pedestrianism.class)) {
                athlete.setCurrentDiscipline(new Pedestrianism());
                athlete.getCompetition().addStageWinC();
                athlete.getCompetition().getDistances().add(new DisciplineDistance(race.getKmcyclism(), currentTime, new Cycling()));
            }
        }
    }

    public void addDisciplineChangeListener(DisciplineChangeListener listener) {
        disciplineListeners.add(listener);
    }

    public void removeDisciplineChangeListener(DisciplineChangeListener listener) {
        disciplineListeners.remove(listener);
    }

    private void notifyDisciplineChange(String newDiscipline) {
        DisciplineChangeEvent event = new DisciplineChangeEvent(this, newDiscipline);
        for (DisciplineChangeListener listener : disciplineListeners) {
            listener.disciplineChanged(event);
        }
    }
    
    
 


}
