package view;

import Events.SpeedChangeEvent;
import listeners.SpeedChangeListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class AthletePanel extends JPanel {
	
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
	
    private JButton increase;
    private JButton decrease;
    private JLabel athleteLabel;
    private JLabel energyLabel;
    private JLabel speedLabel;
    private int startX;
    private int endX;
    private SpeedChangeListener speedChangeListener;
    private ImageIcon buttonIcon;
  
    

    //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
    public AthletePanel(int startX, int endX, ImageIcon icon){
           this.startX = startX;
           this.endX = endX;
           setLayout(null);
           setSize(966, 60);
           buttonIcon = new ImageIcon(getClass().getResource("/Image/buttonDesign.png"));
           decrease = createButtonWithTextAndIcon("-",scaleImage(buttonIcon));
           decrease.setBounds(31, 13, 50, 21);
           add(decrease);
           increase = createButtonWithTextAndIcon("+",scaleImage(buttonIcon));
           increase.setBounds(81, 13, 50, 21);
           add(increase);
           increase.addActionListener(e -> notifySpeedChange(1));
           decrease.addActionListener(e -> notifySpeedChange(-1));
           athleteLabel = new JLabel(icon);
           athleteLabel.setBounds(137, 8, 110, 30);
           add(athleteLabel);
           energyLabel = new JLabel("Energy");
           energyLabel.setBounds(31, 32, 80, 15);
           energyLabel.setFont(energyLabel.getFont().deriveFont(9f));
           add(energyLabel);
           speedLabel = new JLabel("Speed: 5");
           speedLabel.setBounds(31, 41, 80, 15);
           speedLabel.setFont(speedLabel.getFont().deriveFont(9f));
           add(speedLabel);

        setOpaque(false);
    }
    
  //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
    
    public JLabel getSpeedLabel() {
        return speedLabel;
    }
    
    public JLabel getAthleteLabel(){
        return athleteLabel;
    }


  //------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\ 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.drawLine(startX, 23, endX, 23);
    }
    public void updateLabelPosition(int newPositionX) {
        athleteLabel.setLocation(newPositionX, athleteLabel.getY());
    }
    public void updateEnergyLabel(double energy) {
            energyLabel.setText(String.format("Energy: %.2f", energy));
    }
    public void updateSpeedLabel(int speed) {
        speedLabel.setText(String.format("Speed: %d", speed));
    }
    private void notifySpeedChange(int delta) {
        SpeedChangeEvent event = new SpeedChangeEvent(this, delta);
        speedChangeListener.speedChanged(event);
    }
    public void addSpeedChangeListener(SpeedChangeListener listener) {
        this.speedChangeListener = listener;
    }
    private ImageIcon scaleImage(ImageIcon newIcon){
        Image scaledImage = newIcon.getImage().getScaledInstance(50, 21, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
    private JButton createButtonWithTextAndIcon(String text, ImageIcon icon) {
        JButton button = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Draw the background image (icon)
                g2d.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);

                // Set font and calculate text metrics for centering
                g2d.setFont(new Font("Arial", Font.BOLD, 15));  // Font for the text
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(text)) / 2;  // Center the text horizontally
                int y = (getHeight() + fm.getAscent()) / 2 - 2;   // Center the text vertically

                // Draw a white border (shadow effect) around the text
                g2d.setColor(Color.WHITE);
                g2d.drawString(text, x - 1, y - 1);
                g2d.drawString(text, x - 1, y + 1);
                g2d.drawString(text, x + 1, y - 1);
                g2d.drawString(text, x + 1, y + 1);
                g2d.drawString(text, x , y + 1);
                g2d.drawString(text, x, y - 1);
                g2d.drawString(text, x + 1, y);
                g2d.drawString(text, x - 1, y);

                // Draw the actual text in black
                g2d.setColor(Color.BLACK);
                g2d.drawString(text, x, y);
                g2d.setStroke(new BasicStroke(1)); // Set the stroke width to 1 for minimal border
                g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            }
        };
        button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        return button;
    }

}
