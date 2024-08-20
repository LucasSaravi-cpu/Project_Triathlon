package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import listeners.ChronometerListener;

public class Chronometer {
    private int Hours;
    private int  minutes;
    private int seconds;
    private Timer timer;
    private List<ChronometerListener> listeners;
    


    public  Chronometer() {
        this.Hours = 0;
        this. minutes = 0;
        this.seconds = 0;
        this.timer = new Timer();
        this.listeners = new ArrayList<>();
    }

    public void iniciar() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
            	seconds++;
                if (seconds == 60) {
                	seconds = 0;
                	 minutes++;
                }
                if ( minutes == 60) {
                	 minutes = 0;
                	 Hours++;
                }
                notifyListeners();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 10); // Increments every 100 ms
    }

    public void stop() {
        timer.cancel();
    }

    public void addListener(ChronometerListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (ChronometerListener listener : listeners) {
            listener.onTimeruptodate(Hours,  minutes, seconds);
        }
    }
}
