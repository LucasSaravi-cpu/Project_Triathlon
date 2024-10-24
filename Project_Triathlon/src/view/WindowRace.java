package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class WindowRace extends JFrame {

	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	private static final long serialVersionUID = 1L;
	public TitleLabel title;
	private RacePanel racePanel;
	private JScrollPane scrollPane;
	private BackgroundPanel backgroundPanel;  // Changed JPanel to BackgroundPanel
	private Image backgroundImage;  // Attribute for the background image

	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	public WindowRace() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 200, 976, 625);
		setResizable(false);

		// Load the background image
		backgroundImage = new ImageIcon(getClass().getResource("/Image/raceBackground.png")).getImage();  // Load the image
		backgroundPanel = new BackgroundPanel(backgroundImage);  // Use BackgroundPanel instead of JPanel
		backgroundPanel.setLayout(new BorderLayout());  // Ensure it uses BorderLayout

		// Add the backgroundPanel to the JFrame
		getContentPane().add(backgroundPanel);

		// Create and configure the RacePanel
		racePanel = new RacePanel();
		scrollPane = new JScrollPane(racePanel);
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setOpaque(false); // Make the JScrollPane transparent
		scrollPane.getViewport().setOpaque(false);
		// Add the scrollPane to the center of the backgroundPanel
		backgroundPanel.add(scrollPane, BorderLayout.CENTER);
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
			title = new TitleLabel(racetitle, FontCharger.loadCustomFont("/fonts/ThisCafe.ttf").deriveFont(30f));
			title.setHorizontalAlignment(JLabel.CENTER);
			title.setBorder(new EmptyBorder(20, 0, 20, 0));
			backgroundPanel.add(title, BorderLayout.NORTH);
		} else {
			title.setText(racetitle);
		}
		revalidate();
		repaint();
	}

	public void reset() {
		for (AthletePanel athletePanel : racePanel.getAthletePanels()) {
			JLabel label = athletePanel.getAthleteLabel();
			label.setLocation(racePanel.getStartX(), label.getY());
			label.setIcon(racePanel.getImageFromMap(1));
			athletePanel.getSpeedLabel().setText("Speed: 5");
		}
		racePanel.revalidate();
		racePanel.repaint();
	}
}
