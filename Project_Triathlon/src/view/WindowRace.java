package view;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Box;

public class WindowRace extends JFrame {

	private static final long serialVersionUID = 1L;
	public JLabel title;
    private RacePanel race;


	public RacePanel getRacePanel() {
		return race;
	}

	public void setRacePanel(RacePanel race) {
		this.race = race;
	}

	public WindowRace() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 100, 966, 625);
		race = new RacePanel();
		getContentPane().add(race);
		repaint();
	}

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
		}
		race.revalidate();
		race.repaint();
	}
}
