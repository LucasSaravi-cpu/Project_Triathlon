package model.race.thread;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Events.EnergyEvent;
import Events.DisciplineChangeEvent;
import Events.NotifySpeedEvent;
import Events.SpeedChangeEvent;
import listeners.*;

import java.util.concurrent.atomic.AtomicInteger;
import controller.Championship;
import model.athlete.Athlete;
import model.race.discipline.Discipline;
import model.race.discipline.DisciplineDistance;
import model.race.Chronometer;
import model.race.Race;
import model.race.RaceManager;
import model.race.discipline.Swimming;

public class RaceThread extends Thread implements SpeedChangeListener {
	
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
    private NotifySpeedListener notifySpeedListener;
 
    
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
                move(chronometer);
                athlete.decreaseEnergy(10); // Adjust decrement
                Thread.sleep(athlete.getSpeed());

                // Notify Energy Change
                notifyEnergyChange(athlete.getEnergy());
               
                // Verifies if the athlete has energy
                if (athlete.getEnergy() <= 0) {
                    Thread.currentThread().interrupt(); // Stops thread if the athlete has no energy
                    athlete.addRaceDesertions();
                }

                double progress = (double) (positionX - startX) / (endX - startX);
                
              	int minutes = Chronometer.TimerMinutes(chronometer.getTime());
              	
              	if (athlete.getCurrentDiscipline().getClass().equals(Swimming.class)) {
              		
              		NeopreneController(minutes);
              		
              		
              	}
              	
              	/*

                if (athlete.getCurrentDiscipline().isBeforePosition(minutes, race)){
                    Thread.currentThread().interrupt();
                    athlete.addRaceDesertions();
                    athlete.getCompetition().get(raceIndex).getDistances().add(new DisciplineDistance(race.getTotalDistance()*progress,"Forfeited", athlete.getCurrentDiscipline().createInstance()));
                }
                */
            }
        } catch (InterruptedException e) {

        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
            if (activeThreads.decrementAndGet() == 0) {
                controller.allThreadsCompleted();
            }
        }

    }
    @Override
    public void speedChanged(SpeedChangeEvent event) {
        if (athlete.getUserSpeedAdjustment() >= 1 && athlete.getUserSpeedAdjustment() <= 10) {
            int newSpeed = athlete.getUserSpeedAdjustment() + event.getDelta();
            if (newSpeed >= 1 && newSpeed <= 10) {
                athlete.setUserSpeedAdjustment(newSpeed);
                notifySpeedChange(athlete.getUserSpeedAdjustment());
            }
        }
    }
    
    private void move(Chronometer chronometer) throws SQLException {

        if (positionX + athlete.getPositionChange(race)<endX){
            positionX += athlete.getPositionChange(race);
        }
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
    private void checkForDisciplineChange(Chronometer chronometer) throws SQLException {
        String currentTime = chronometer.getTime();
        boolean isFirst = !raceManager.isDisciplineWon(athlete.getCurrentDiscipline().getClass().getSimpleName().toLowerCase());
        if (athlete.getCurrentDiscipline().surpassed(positionX, race, startX, endX)){

            athlete.getCompetition().get(raceIndex).getDistances().add(new DisciplineDistance(race.getKm(athlete.getCurrentDiscipline()), athlete.getCurrentDiscipline().setTime(athlete, chronometer, raceIndex), athlete.getCurrentDiscipline().createInstance()));
            if (isFirst) {
                athlete.addStageWin();
                raceManager.markDisciplineAsWon(athlete.getCurrentDiscipline().getClass().getSimpleName().toLowerCase());
            }
            athlete.setNewDiscipline();
            notifyDisciplineChange(athlete.getCurrentDiscipline(), isFirst);
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

    private void notifyDisciplineChange(Discipline newDiscipline, boolean isFirst) throws SQLException {
        DisciplineChangeEvent event = new DisciplineChangeEvent(this, newDiscipline, isFirst);
        for (DisciplineChangeListener listener : disciplineListeners) {
            listener.disciplineChanged(event);
        }
    }
    public void addSpeedListener(NotifySpeedListener listener) {
        this.notifySpeedListener = listener;
    }


    private void notifySpeedChange(int userSpeed) {
        NotifySpeedEvent event = new NotifySpeedEvent(this, userSpeed);
        notifySpeedListener.speedChanged(event);
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


    public void NeopreneController(int minutes) {
        // Get the neoprene usage policy according to the race distance and current temperature
        /*
        System.out.println(athlete.getName());
        String neoprene = athlete.setNeopreneUsage(
            race.getModality().getDisciplinedistance().get(0).getDistance(),
            race.getCurrentWeatherCondition().getCurrentTemperature()
        );

        // Check if the athlete is using neoprene
        boolean isUsingNeoprene = athlete.isUsingNeoprene();

        // Get the maximum time allowed for the use of neoprene
        double maxAllowedMinutes = athlete.setMaximumNeopreneTime(
            race.getModality().getDisciplinedistance().get(0).getDistance()
        );

        // Check if the current race allows neoprene usage
        if (race.isCurrentneoprene()) {
            if (neoprene.equalsIgnoreCase("Forbidden")) {
                if (isUsingNeoprene) {
                    // Neoprene is forbidden and the athlete is using it
                    System.out.println("The use of neoprene is forbidden for this race.");
                    System.out.println("The athlete is using neoprene despite the prohibition.");
                    // Additional action to penalize the athlete or take measures
                }
            } else if (neoprene.equalsIgnoreCase("Obligatory")) {
                if (isUsingNeoprene) {
                    // Neoprene is mandatory and the athlete is currently using it
                    System.out.println("Neoprene is mandatory and the athlete is using it correctly.");
                    // Additional action if necessary
                } else {
                    // Neoprene is mandatory but the athlete is not using it
                    System.out.println("Neoprene is mandatory but the athlete is not using it.");
                    // Additional action to penalize the athlete or take measures
                }
            } else if (neoprene.equalsIgnoreCase("Usable")) {
                if (isUsingNeoprene) {
                    if (minutes >= maxAllowedMinutes) {
                        // Neoprene is allowed but the athlete has exceeded the maximum time
                        System.out.println("The athlete has exceeded the allowed neoprene usage time.");
                        // Additional action to penalize the athlete or take measures
                    } else {
                        // Neoprene is allowed and the athlete has not exceeded the maximum time
                        System.out.println("Neoprene is in use and the athlete is within the allowed time.");
                        // Additional action if necessary
                    }
                } else {
                    // Neoprene is allowed but the athlete is not using it
                    System.out.println("Neoprene is allowed but the athlete is not using it.");
                    // Additional action if necessary
                }
            }
        } else {
            // Race forbids the use of neoprene
            if (isUsingNeoprene) {
                System.out.println("The race does not allow the use of neoprene.");
                System.out.println("The athlete is using neoprene despite it being forbidden.");
                // Additional action to penalize the athlete or take measures
            } else {
                // Neoprene is forbidden and the athlete is not using it
                System.out.println("Neoprene is forbidden and the athlete is not using it.");
                // Additional action if necessary
            }
        }
        */
    }






}
