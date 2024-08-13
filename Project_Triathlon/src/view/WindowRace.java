package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class WindowRace extends JFrame {

	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	private static final long serialVersionUID = 1L;
	public JLabel title;
    private RacePanel race;

  
	public void setRacePanel(RacePanel race) {
		this.race = race;
	}
	  //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	public WindowRace() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 100, 966, 625);
		race = new RacePanel();
		getContentPane().add(race);
		repaint();
	}

	
	  //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
		public RacePanel getRacePanel() {
			return race;
		}

		 //------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\	
		
	public void updateLabelPosition(int index, int newPositionX) {
		race.updateLabelPosition(index, newPositionX);
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
		revalidate();  // Revalidar el contenedor
		repaint();     // Repintar para asegurar la actualización de la UI
	}
	public void reset(){
		for (JLabel label : race.getLabels()) {
			label.setLocation(race.getStartX(), label.getY());
			ImageIcon icon = new ImageIcon(getClass().getResource("/Image/swimming.png"));
			Image image = icon.getImage(); // Obtén la imagen del ImageIcon
			Image scaledImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Escala la imagen
			ImageIcon scaledIcon = new ImageIcon(scaledImage); // Crea un nuevo ImageIcon con la imagen escalada
            label.setIcon(scaledIcon);
		}
		race.revalidate();
		race.repaint();
	}
}
