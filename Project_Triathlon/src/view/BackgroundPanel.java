package view;
import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
    private Image backgroundImage;

    //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
    
    public BackgroundPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
    
  //------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\ 

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}