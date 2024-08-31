package model.race;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import listeners.ChronometerListener;
import model.race.modality.Modality;

public class Chronometer {
    private int Hours;
    private int  minutes;
    private int seconds;
    private Timer timer;
    private List<ChronometerListener> listeners;
    private Modality modality;



    public  Chronometer(Modality modality) {
        this.Hours = 0;
        this. minutes = 0;
        this.seconds = 0;
        this.timer = new Timer();
        this.listeners = new ArrayList<>();
        this.modality = modality;
    }

    public void start() {
        // Calculate factor of acceleration based on the new duration

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
        System.out.println(modality.getClass().getSimpleName());
        timer.scheduleAtFixedRate(task, 0, modality.getRaceTime()); // Increments every 100 ms
    }

    public void stop() {
        timer.cancel();
        System.out.println("Stopped");
    }

    public void reset() {
        this.Hours = 0;
        this.minutes = 0;
        this.seconds = 0;
        notifyListeners();
        this.timer = new Timer();
    }

    public void addListener(ChronometerListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (ChronometerListener listener : listeners) {
            listener.onTimeruptodate(Hours,  minutes, seconds);
        }
    }
    
    public String getTime() {
        return String.format("%02d:%02d:%02d", Hours, minutes, seconds);
    }
    
    public double getTimeInSeconds() {
        return Hours * 3600 + minutes * 60 + seconds;
    }
    public static String subtractTimes(String time1, String time2) {
        // Parse times
        int[] time1Components = Chronometer.parseTime(time1);
        int[] time2Components = Chronometer.parseTime(time2);

        // Convert both times to seconds
        int seconds1 = Chronometer.toSeconds(time1Components);
        int seconds2 = Chronometer.toSeconds(time2Components);

        // Substract seconds
        int differenceInSeconds = Math.abs(seconds1 - seconds2);

        // Convert difference to hours, minutes and seconds
        int hours = differenceInSeconds / 3600;
        int minutes = (differenceInSeconds % 3600) / 60;
        int seconds = differenceInSeconds % 60;

        // Format result
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static int[] parseTime(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        return new int[] { hours, minutes, seconds };
    }

    public static int toSeconds(int[] timeComponents) {
        return timeComponents[0] * 3600 + timeComponents[1] * 60 + timeComponents[2];
    }
    
    
    public static int TimerMinutes(String timer) {
        int[] timeComponents = Chronometer.parseTime(timer);

      
        int totalMinutes = timeComponents[0] * 60 + timeComponents[1];

        return totalMinutes;
    }
    
    
}