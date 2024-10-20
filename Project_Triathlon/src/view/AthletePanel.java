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
    public JLabel getAthleteLabel(){
        return athleteLabel;
    }
    public AthletePanel(int startX, int endX, ImageIcon icon){
           this.startX = startX;
           this.endX = endX;
           setLayout(null);
           setSize(966, 60);
           decrease = new JButton ("-");
           decrease.setBounds(31, 13, 50, 21);
           add(decrease);
           increase = new JButton("+");
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

    public JLabel getSpeedLabel() {
        return speedLabel;
    }
}
