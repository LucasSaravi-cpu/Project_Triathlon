package model;
import listeners.RaceListener;
public class RaceThread extends Thread {
    private int positionX;
    private RaceListener listener;

    public RaceThread(int positionX, RaceListener listener) {
        this.positionX = positionX;
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            while (true) {
                moveLabel();
                Thread.sleep(100); // Ajusta este valor para cambiar la velocidad de movimiento
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void moveLabel() {
        positionX += 10; // Incrementa la posición X (ajusta según sea necesario)
        if (listener != null) {
            listener.positionChanged(this, positionX);
        }
    }
}

