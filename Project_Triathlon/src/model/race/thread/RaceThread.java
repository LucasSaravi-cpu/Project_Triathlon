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
import model.neoprene.NeopreneAllowed;
import model.neoprene.NeopreneForbidden;
import model.neoprene.NeopreneMandatory;
import model.neoprene.NeoprenePolicy;
import model.race.discipline.Discipline;
import model.race.discipline.DisciplineDistance;
import model.race.Chronometer;
import model.race.Race;
import model.race.RaceManager;
import model.race.discipline.Swimming;

public class RaceThread extends Thread implements SpeedChangeListener {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
    private final int startX;
    private int positionX;
    private final int endX;
    private final RaceListener listener;
    private final List<EnergyListener> listeners = new ArrayList<>();
    private final List<DisciplineChangeListener> disciplineListeners = new ArrayList<>();
    private final Athlete athlete;
    private final static AtomicInteger activeThreads = new AtomicInteger(0);
    private final Championship controller;
    private final RaceManager raceManager;
    private final Race race;
    private final int raceIndex;
    private NotifySpeedListener notifySpeedListener;
    private long draftingStartTime = 0;
    private boolean isInDraftingZone = false;
    private static final int DRAFTING_ZONE_LENGTH = 7; // in meters
    private static final int DRAFTING_ZONE_TIME_LIMIT = 15000; // 15 seconds in milliseconds
    private boolean neoprenePolicyApplied;
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
    public static AtomicInteger getActiveThreads(){
        return activeThreads;
    }

	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
    
    @Override
    public  void run() {
        Chronometer chronometer = Championship.getChronometer();
        try {
            while (!Thread.currentThread().isInterrupted()) {
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
              	
              	if (athlete.getCurrentDiscipline().getClass().equals(Swimming.class) 
              			&& minutes >= athlete.setMaximumNeopreneTime(race.getModality().getDisciplinedistance().get(0).getDistance()) && race.isCurrentneoprene()==true ) {
              		
              		NeopreneController(minutes);
              		neoprenePolicyApplied = true; 

              	}
     
                
                if (athlete.getCurrentDiscipline().isBeforePosition(minutes, race)){

                	stopathlete();
                    // Thread.currentThread().interrupt();
                    //   athlete.addRaceDesertions();
                   //   athlete.getCompetition().get(raceIndex).getDistances().add(new DisciplineDistance(athlete.getCurrentDiscipline().getKmInDiscipline(race, positionX, startX, endX),"Forfeited", athlete.getCurrentDiscipline().createInstance()));
                }
                
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
            athlete.getCompetition().get(raceIndex).getDistances().add(new DisciplineDistance(race.getKm(athlete.getCurrentDiscipline()), athlete.getCurrentDiscipline().setTime(athlete, chronometer, raceIndex), athlete.getCurrentDiscipline().createInstance()));
            athlete.getCompetition().get(raceIndex).setTimeTot(Championship.getChronometer().getTime());
            raceManager.notifyAthleteFinished(athlete);
            Thread.currentThread().interrupt();
        	 
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
        boolean isFirst = !raceManager.isDisciplineWon(athlete.getCurrentDiscipline().getClass().getSimpleName().toLowerCase());
        if (athlete.getCurrentDiscipline().surpassed(positionX, race, startX, endX)){

            athlete.getCompetition().get(raceIndex).getDistances().add(new DisciplineDistance(race.getKm(athlete.getCurrentDiscipline()), athlete.getCurrentDiscipline().setTime(athlete, chronometer, raceIndex), athlete.getCurrentDiscipline().createInstance()));
            if (isFirst) {
                athlete.addStageWin();
                raceManager.markDisciplineAsWon(athlete.getCurrentDiscipline().getClass().getSimpleName().toLowerCase());
            }
            athlete.setNewDiscipline();
            notifyDisciplineChange(athlete.getCurrentDiscipline(), isFirst);
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
      
    
        String neopreneUsage = athlete.setNeopreneUsage(
            race.getModality().getDisciplinedistance().get(0).getDistance(),
            race.getCurrentWeatherCondition().getCurrentTemperature()
        );

      
        NeoprenePolicy policy;

        switch (neopreneUsage.toLowerCase()) {
            case "forbidden":
                policy = new NeopreneForbidden(athlete, race, raceIndex,this);
                break;
            case "mandatory":
                policy = new NeopreneMandatory(athlete, race, raceIndex,this);
                break;
            case "allowed":
                policy = new NeopreneAllowed(athlete, race, raceIndex,this);
                break;
            default:
                System.out.println("Invalid neoprene usage.");
                return;
        }

     
        policy.applyPolicy(minutes);
    }
    
    
    
    public void stopathlete() {
    	
    	  this.interrupt(); 
    	  athlete.addRaceDesertions();
          athlete.getCompetition().get(raceIndex).getDistances().add(new DisciplineDistance(athlete.getCurrentDiscipline().getKmInDiscipline(race, positionX, startX, endX),"Forfeited", athlete.getCurrentDiscipline().createInstance()));
    }

    

    
    }




    /*public void checkForDrafting()
    {
        List<RaceThread> threadsCopy = Championship.getRaceThreads();
        for(RaceThread thread: threadsCopy)
        {
            if(this != thread)
            {
                int distance = Math.abs(this.positionX - thread.getPositionX()); //Calculates the distance between 2 athletes to check if drafting is happening
                if(distance <= DRAFTING_ZONE_LENGTH*100) //If it returns true, the athlete is within drafting zone
                {
                    if(!isInDraftingZone) //Starts counting for drafting time
                    {
                        draftingStartTime = System.currentTimeMillis();
                        isInDraftingZone = true;
                    }
                    else if(System.currentTimeMillis() - draftingStartTime >= DRAFTING_ZONE_TIME_LIMIT) //Checks if the amount of time the athlete has stayed on the drafting zone exceeds the allowed time
                    {
                        //TODO: Add Penalization
                        isInDraftingZone = false;
                    }
                }
                else
                {
                    isInDraftingZone = false;
                }
            }
        }
    }*/

