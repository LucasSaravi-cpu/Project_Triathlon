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

    
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
    
    @Override
    public  void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Random random = new Random();
                moveLabel();
                athlete.decreaseEnergy(10); // Adjust decrement
                Thread.sleep(random.nextInt((int) this.athlete.getBaseSpeed())); // Adjust thread speed
                checkForDisciplineChange();
                // Notify Energy Change
                notifyEnergyChange(athlete.getEnergy());
               
                // Verifica si el atleta ha agotado la energía
                if (athlete.getEnergy() <= 0) {
                    Thread.currentThread().interrupt(); // Detiene el hilo si la energía es 0
                  
                }
                
                    
            }
        } catch (InterruptedException e) {

        }  finally {
            if (activeThreads.decrementAndGet() == 0) {
                controller.allThreadsCompleted();
            }
        }

    }

    
    
    private void moveLabel() {
        if (positionX+10<=endX)
    	    positionX += 10; // Increments X position
        else {
        	positionX=endX;
        	Thread.currentThread().interrupt(); 
        	 raceManager.notifyAthleteFinished(athlete);
        }
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
    private void checkForDisciplineChange() {
        double progress = (double) (positionX - startX) / (endX - startX);
        if (progress >= race.getDisciplineChangePoints().get(0) + (100-startX) / (endX-startX) && progress < race.getDisciplineChangePoints().get(1)) { // Primera línea azul
            notifyDisciplineChange("cycling");
        } else if (progress >= race.getDisciplineChangePoints().get(1)) { // Segunda línea azul
            notifyDisciplineChange("running");
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
