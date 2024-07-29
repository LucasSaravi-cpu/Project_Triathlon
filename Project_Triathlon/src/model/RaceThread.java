package model;
import listeners.RaceListener;
public class RaceThread extends Thread {
    private int positionX;
    private int endX;
    private RaceListener listener;

    public RaceThread(int positionX, int endX, RaceListener listener) {
        this.positionX = positionX;
        this.listener = listener;
        this.endX = endX;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                moveLabel();
                Thread.sleep(100); // Ajusta este valor para cambiar la velocidad de movimiento
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
}

