package view;

import javax.swing.*;
import java.awt.*;

public class AthletePanel extends JPanel {
    private JButton increase;
    private JButton decrease;
    private JLabel athleteLabel;
    private JLabel energyLabel;
    private int startX;
    private int endX;
    public JLabel getAthleteLabel(){
        return athleteLabel;
    }
    public AthletePanel(int startX, int endX){
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
           ImageIcon icon = new ImageIcon(getClass().getResource("/Image/swimming.png"));
           Image image = icon.getImage(); // get ImageIcon
           Image scaledImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Scale image
           ImageIcon scaledIcon = new ImageIcon(scaledImage);
           athleteLabel = new JLabel(scaledIcon);
           athleteLabel.setBounds(137, 8, 110, 30);
           add(athleteLabel);
           energyLabel = new JLabel("Energy");
           energyLabel.setBounds(31, 32, 100, 20);
           energyLabel.setFont(energyLabel.getFont().deriveFont(11f));
           add(energyLabel);
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
            energyLabel.setText(String.format("Energy: %.2f%%", energy));
    }
}
