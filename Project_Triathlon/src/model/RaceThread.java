package model;

import javax.swing.JLabel;

public class RaceThread extends Thread {
   
	private JLabel jlabel;
	private int posicionX ;
 

    public RaceThread(JLabel jlabel,int posicionX) {
        this.jlabel =jlabel;
        this.posicionX = posicionX;
      
    }

    @Override
    public void run() {
        try {

        	 while (true) {
                moverLabel();
                Thread.sleep(100); // Ajusta este valor para cambiar la velocidad de movimiento
        	 }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void moverLabel() {
        // Aquí defines cómo se mueve el JLabel
        posicionX += 10; // Incrementa la posición X (ajusta según sea necesario)
        jlabel.setLocation(posicionX, jlabel.getY());

      }
}