package view;

import java.awt.EventQueue;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Button;
import javax.swing.Box;
import java.awt.Canvas;
import java.awt.Color;

public class WindowRace extends JFrame {

	private static final long serialVersionUID = 1L;
    private RacePanel race;


	public RacePanel getRacePanel() {
		return race;
	}

	public void setRacePanel(RacePanel race) {
		this.race = race;
	}

	public WindowRace() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 966, 613);
		race = new RacePanel();
		add(race);
	}

	public void updateLabelPosition(int index, int newPositionX) {
		race.updateLabelPosition(index, newPositionX);
	}
	
	


	
	
}
