package view;

import java.awt.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

public class RacePanel extends JPanel {


	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\


    private static final long serialVersionUID = 1L;
    private List<AthletePanel> athletePanels;
    private List<Double> disciplineChangePoints;
    private List<Double> stationPoints;
    private static final int startX = 137;
    private static final int endX = 886;
    private ImageIcon finishLineIcon;
    private Image scaledFinishLineImage;
    private static Map<Integer, ImageIcon> map;

  //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

    public int getStartX() {
		return startX;
	}
	public int getEndX() {
		return endX;
	}
    public List<AthletePanel> getAthletePanels(){
        return athletePanels;
    }

    public void setDisciplineChangePoints(List<Double> points) {
        this.disciplineChangePoints = points;
        repaint();
    }
    public void setStationPoints(List<Double> points){
        this.stationPoints=points;
        repaint();
    }
    public ImageIcon getImageFromMap(int index){
        return map.get(index);
    }

	  //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
    public RacePanel(){
        athletePanels = new ArrayList<>();
        loadMap();
        for (int i = 0; i < 43; i++){
            AthletePanel athletePanel = new AthletePanel(startX, endX, getImageFromMap(1));
            athletePanel.setLocation(0, i*60);
            athletePanels.add(athletePanel);
            add(athletePanel);
        }
        setPreferredSize(new Dimension(966, 2600));
        setLayout(null);
        finishLineIcon = new ImageIcon(getClass().getResource("/Image/finish_line.png"));
    }
    
  //------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
    public void updateLabelPosition(int index, int newPositionX) {
        if (index >= 0 && index < athletePanels.size()) {
            athletePanels.get(index).updateLabelPosition(newPositionX);
        }
    }
    public void updateEnergyLabel(int index, double energy) {
        if (index >= 0 && index < athletePanels.size()) {
            athletePanels.get(index).updateEnergyLabel(energy);
        }
    }
    public void updateSpeedLabel(int index, int speed) {
        if (index >= 0 && index < athletePanels.size()) {
            athletePanels.get(index).updateSpeedLabel(speed);
        }
    }
    public void setIcon(int athleteIndex, int iconIndex){
        athletePanels.get(athleteIndex).getAthleteLabel().setIcon(map.get(iconIndex));
    }
    private void loadMap(){
        map = new HashMap<>();
        ImageIcon swimmingIcon = scaleImage(new ImageIcon(getClass().getResource("/Image/swimming.png")));
        map.put(new Integer(1), swimmingIcon);
        ImageIcon cyclingIcon = scaleImage(new ImageIcon(getClass().getResource("/Image/cycling.png")));
        map.put(new Integer(2), cyclingIcon);
        ImageIcon runningIcon = scaleImage(new ImageIcon(getClass().getResource("/Image/running.png")));
        map.put(new Integer(3), runningIcon);

    }
    private ImageIcon scaleImage(ImageIcon newIcon){
        Image scaledImage = newIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (finishLineIcon != null) {
            int finishLineWidth = finishLineIcon.getIconWidth();
            int finishLineHeight = finishLineIcon.getIconHeight();

            for (int y = 0; y < getHeight(); y += finishLineHeight) {
                g2d.drawImage(finishLineIcon.getImage(), endX, y, null);
            }
        }
        g2d.setColor(Color.BLUE);
        for (Double point : disciplineChangePoints) {
            int x = startX + (int) ((endX - startX) * point);
            if (disciplineChangePoints.indexOf(point)==0)
                x+=100;
            g2d.drawLine(x, 0, x, getHeight());
        }
        g2d.setColor(Color.GREEN);
        for (Double point : stationPoints) {
            int x = startX + point.intValue();
            g2d.drawLine(x, 0, x, getHeight());
        }
    }

}