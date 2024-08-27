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
    private int raceIndex;
 
    
    //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

    public RaceThread(int startX, int positionX, int endX, RaceListener listener, Athlete athlete, Championship controller, RaceManager raceManager, Race race, int raceIndex) {
        this.startX=startX;
        this.positionX = positionX;
        this.listener = listener;
        this.endX = endX;
        this.athlete = athlete;
        this.controller=controller;
        activeThreads.incrementAndGet();
        this.raceManager = raceManager;
        this.race=race;
        this.raceIndex= raceIndex;
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
                    athlete.addRaceDesertions();
                }

                double progress = (double) (positionX - startX) / (endX - startX);
                
              	int minutes = Chronometer.TimerMinutes(chronometer.getTime());
              	
              	if ( athlete.getCurrentDiscipline().getClass().equals(Swimming.class)) {
              		
              		ControllerNeomprene(minutes);
              		
              		
              	}
              	
              	
              	
                
               //We should manage this in Swimming, Cycling and Pedestrianism classes
                if (athlete.getCurrentDiscipline().isBeforePosition(minutes, race)){
                    Thread.currentThread().interrupt();
                    athlete.addRaceDesertions();
                    athlete.getCompetition().get(raceIndex).getDistances().add(new DisciplineDistance(race.getTotalDistance()*progress,"Forfeited", athlete.getCurrentDiscipline().createInstance()));
                }
                /*
                if (minutes>race.getT1() && athlete.getCurrentDiscipline().getClass().equals(Swimming.class))  {
                    Thread.currentThread().interrupt();
                    athlete.addRaceDesertions();
                    athlete.getCompetition().get(raceIndex).getDistances().add(new DisciplineDistance(race.getTotalDistance()*progress,"Forfeited", new Swimming()));
           
                }
                
                if (minutes >race.getT1()+race.getT2() && athlete.getCurrentDiscipline().getClass().equals(Cycling.class)){
                	  Thread.currentThread().interrupt();
                      athlete.addRaceDesertions();
                	  athlete.getCompetition().get(raceIndex).getDistances().add(new DisciplineDistance(race.getTotalDistance()*progress,"Forfeited" , new Cycling()));
                }
                
                if (minutes>race.getT1()+race.getT2()+race.getT3()  && athlete.getCurrentDiscipline().getClass().equals(Pedestrianism.class)) {
              	     Thread.currentThread().interrupt();
                	 athlete.getCompetition().get(raceIndex).getDistances().add(new DisciplineDistance(race.getTotalDistance()*progress, "Forfeited", new Pedestrianism()));
              	 
                }*/

                
                    
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
            athlete.getCompetition().get(raceIndex).getDistances().add(new DisciplineDistance(race.getKm(athlete.getCurrentDiscipline()), athlete.getCurrentDiscipline().setTime(athlete, chronometer, raceIndex), athlete.getCurrentDiscipline().createInstance()));
            athlete.getCompetition().get(raceIndex).setTimeTot(Championship.getChronometer().getTime());
        	raceManager.notifyAthleteFinished(athlete);
        	 
        }
        checkForDisciplineChange(chronometer);
        if (athlete.getCurrentStation()<race.getStationPoints().size())
            checkForStationPass();
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
        String currentTime = chronometer.getTime();
        if (athlete.getCurrentDiscipline().surpassed(positionX, race, startX, endX)){
            athlete.getCompetition().get(raceIndex).getDistances().add(new DisciplineDistance(race.getKm(athlete.getCurrentDiscipline()), athlete.getCurrentDiscipline().setTime(athlete, chronometer, raceIndex), athlete.getCurrentDiscipline().createInstance()));
            if (!raceManager.isDisciplineWon(athlete.getCurrentDiscipline().getClass().getSimpleName().toLowerCase())) {
                athlete.addStageWin();
                raceManager.markDisciplineAsWon(athlete.getCurrentDiscipline().getClass().getSimpleName().toLowerCase());
            }
            athlete.setNewDiscipline();
            notifyDisciplineChange(athlete.getCurrentDiscipline());
            //Change the wheather conditions
            //WeatherConditions weatherconditions =  Championship.getRandomWeatherCondition(Championship.loadDatabase());
            //Championship.notifyWeatherUpdate(weatherconditions);
        }
    }

    public void addDisciplineChangeListener(DisciplineChangeListener listener) {
        disciplineListeners.add(listener);
    }

    public void removeDisciplineChangeListener(DisciplineChangeListener listener) {
        disciplineListeners.remove(listener);
    }

    private void notifyDisciplineChange(Discipline newDiscipline) {
        DisciplineChangeEvent event = new DisciplineChangeEvent(this, newDiscipline);
        for (DisciplineChangeListener listener : disciplineListeners) {
            listener.disciplineChanged(event);
        }
    }
    private synchronized void checkForStationPass() {
        int stationPoint = startX + race.getStationPoints().get(athlete.getCurrentStation()).intValue();
        if (stationPoint <= positionX+30) {
            Random random = new Random();

            if (random.nextBoolean()) {
                athlete.increaseEnergy(1000);
            }
            athlete.setCurrentStation(athlete.getCurrentStation() + 1);


        }
    }
    
    
    public void ControllerNeomprene(int minutes) {
        // Obtener la política de uso del neopreno según la distancia de la carrera y la temperatura actual
    	
    	System.out.println(athlete.getName());
        String neoprene = athlete.checkNeopreneUsage(
            race.getModality().getDisciplinedistance().get(raceIndex).getDistance(), 
            race.getCurrentWeatherCondition().getCurrentTemperature()
        );

        // Verificar si el atleta está usando neopreno
        boolean isUsingNeoprene = athlete.isNeoprene();
        
        // Obtener el tiempo máximo permitido para el uso de neopreno
        double maxAllowedMinutes = athlete.allowedNeopreneMinutes(
            race.getModality().getDisciplinedistance().get(raceIndex).getDistance()
        );

        // Verificar si la carrera permite el uso de neopreno
        if (race.isCurrentneoprene()) {
            if (neoprene.equalsIgnoreCase("Prohibited")) {
                if (isUsingNeoprene) {
                    // Neopreno está prohibido y el atleta lo está usando
                    System.out.println("El uso de neopreno está prohibido para esta carrera.");
                    System.out.println("El atleta está usando neopreno a pesar de la prohibición.");
                    // Acción adicional para penalizar al atleta o tomar medidas
                }
            } else if (neoprene.equalsIgnoreCase("Obligatory")) {
                if (isUsingNeoprene) {
                    // Neopreno es obligatorio y el atleta lo está usando
                    System.out.println("El neopreno es obligatorio y el atleta lo está usando correctamente.");
                    // Acción adicional si es necesario
                } else {
                    // Neopreno es obligatorio pero el atleta no lo está usando
                    System.out.println("El neopreno es obligatorio pero el atleta no lo está usando.");
                    // Acción adicional para penalizar al atleta o tomar medidas
                }
            } else if (neoprene.equalsIgnoreCase("Usable")) {
                if (isUsingNeoprene) {
                    if (minutes > maxAllowedMinutes) {
                        // Neopreno es usable pero el atleta ha excedido el tiempo permitido
                        System.out.println("El atleta ha excedido el tiempo permitido de uso de neopreno.");
                        // Acción adicional para penalizar al atleta o tomar medidas
                    } else {
                        // Neopreno es usable y el atleta está dentro del tiempo permitido
                        System.out.println("El neopreno está en uso y el atleta está dentro del tiempo permitido.");
                        // Acción adicional si es necesario
                    }
                } else {
                    // Neopreno es usable pero el atleta no lo está usando
                    System.out.println("El neopreno es usable pero el atleta no lo está usando.");
                    // Acción adicional si es necesario
                }
            }
        } else {
            // La carrera no permite el uso de neopreno
            if (isUsingNeoprene) {
                System.out.println("La carrera no permite el uso de neopreno.");
                System.out.println("El atleta está usando neopreno a pesar de no estar permitido.");
                // Acción adicional para penalizar al atleta o tomar medidas
            } else {
                // Neopreno no está permitido y el atleta no lo está usando
                System.out.println("El neopreno no está permitido y el atleta no lo está usando.");
                // Acción adicional si es necesario
            }
        }
    }
	
	 
 


}
