package model;
import java.util.ArrayList;
import java.util.List;

import Events.EnergyEvent;
import listeners.EnergyListener;
import listeners.RaceListener;
public class RaceThread extends Thread {
    private int positionX;
    private int endX;
    private RaceListener listener;
    private final List<EnergyListener> listeners = new ArrayList<>();
    private final Athlete athlete;
    

    public RaceThread(int positionX, int endX, RaceListener listener, Athlete athlete) {
        this.positionX = positionX;
        this.listener = listener;
        this.endX = endX;
        this.athlete = athlete;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                moveLabel();
                athlete.decreaseEnergy(100); // Ajusta el decremento
                Thread.sleep(100); // Ajusta este valor para cambiar la velocidad de movimiento
                
                // Notifica los cambios de energía
                notifyEnergyChange(athlete.getEnergy());

                // Verifica si el atleta ha agotado la energía
                if (athlete.getEnergy() <= 0) {
                    break; // Detiene el hilo si la energía es 0
                }
                
            }
        } catch (InterruptedException e) {}
    }

    private void moveLabel() {
        if (positionX+10<=endX)
    	    positionX += 10; // Incrementa la posición X (ajusta según sea necesario)
        else {
        	positionX=endX;
        	Thread.currentThread().interrupt(); 
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

