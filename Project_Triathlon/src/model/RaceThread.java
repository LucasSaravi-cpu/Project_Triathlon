package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Events.EnergyEvent;
import listeners.EnergyListener;
import listeners.RaceListener;
import java.util.concurrent.atomic.AtomicInteger;
import controller.Championship;
public class RaceThread extends Thread {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
    private int positionX;
    private int endX;
    private RaceListener listener;
    private final List<EnergyListener> listeners = new ArrayList<>();
    private final Athlete athlete;
    private static AtomicInteger activeThreads = new AtomicInteger(0);
    private final Championship controller;
    private RaceManager raceManager;
    private Race race;
    
    //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

    public RaceThread(int positionX, int endX, RaceListener listener, Athlete athlete, Championship controller, RaceManager raceManager, Race race) {
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
                Thread.sleep(random.nextInt(1000)); // Adjust thread speed
                
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
    	    positionX += 10; // Incrementa la posición X (ajusta según sea necesario)
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




  
    
    
}
