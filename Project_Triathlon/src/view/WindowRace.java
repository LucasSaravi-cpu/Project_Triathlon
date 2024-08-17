package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class WindowRace extends JFrame {

	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	private static final long serialVersionUID = 1L;
	public JLabel title;
    private RacePanel racePanel;
    private JScrollPane scrollPane;

	public void setRacePanel(RacePanel race) {
		this.racePanel = race;
	}
	  //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	public WindowRace() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 200, 976, 625);
		racePanel = new RacePanel();
		getContentPane().add(racePanel);
		repaint();
		scrollPane = new JScrollPane(racePanel);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}

	
	  //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
		public RacePanel getRacePanel() {
			return racePanel;
		}

		 //------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\	
		
	public void updateLabelPosition(int index, int newPositionX) {
		racePanel.updateLabelPosition(index, newPositionX);
	}
	public void setRaceTitle(String racetitle) {
		if (title == null) {
			title = new JLabel(racetitle);
			title.setFont(new Font("Georgia", Font.BOLD, 24));
			title.setHorizontalAlignment(JLabel.CENTER);
			title.setBorder(new EmptyBorder(20, 0, 20, 0));
			getContentPane().add(title, BorderLayout.NORTH);
		} else {
			title.setText(racetitle);
		}
		revalidate();
		repaint();
	}
	public void reset(){
		for (JLabel label : racePanel.getLabels()) {
			label.setLocation(racePanel.getStartX(), label.getY());
			ImageIcon icon = new ImageIcon(getClass().getResource("/Image/swimming.png"));
			Image image = icon.getImage();
			Image scaledImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			ImageIcon scaledIcon = new ImageIcon(scaledImage);
            label.setIcon(scaledIcon);
		}
		racePanel.revalidate();
		racePanel.repaint();
	}
}
