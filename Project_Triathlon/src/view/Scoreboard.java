package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.Championship;
import model.Athlete;
import model.RaceManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;

public class Scoreboard extends JFrame {
	
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnListAthletesStats;
	private JButton btnClean;
    private JRadioButton rdbtnalphabetic;
    private JRadioButton rdbtnposition;
    private JButton btnListAthletes;
    private JButton listRaceStats;
    private JButton newRace;
    private JButton btnPlayMusic;
	private TextArea textArea;

    //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
    
	public Scoreboard(Championship controller) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(977, 100, 578, 699);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStopMusic = new JButton("Stop Music");
		btnStopMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				WindowStart.getMusic().stop();
				
			}
		});
		
		btnPlayMusic = new JButton("Play Music");
		btnPlayMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				WindowStart.playMusic();
			}
			
			
		});
		btnPlayMusic.setBounds(134, 603, 92, 26);
		contentPane.add(btnPlayMusic);
		btnStopMusic.setBounds(24, 603, 100, 26);
		contentPane.add(btnStopMusic);
		
		

		textArea = new TextArea();
		textArea.setBounds(21, 43, 517, 216);
		contentPane.add(textArea);
		
		
		btnListAthletesStats = new JButton("List Athletes' stats");
		btnListAthletesStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for(Athlete athlete: Championship.getSelectionAthletes())
				{
					textArea.setText(athlete.listStats());
				}
			}
		});
		
		btnClean = new JButton("Clean ");
		btnClean.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		textArea.setText("");
		 	}
		 });
		btnClean.setBounds(24, 522, 218, 38);
		contentPane.add(btnClean);
		rdbtnalphabetic = new JRadioButton("By alphabetic order");
		rdbtnalphabetic.setBounds(247, 347, 219, 43);
		contentPane.add(rdbtnalphabetic);
		rdbtnposition = new JRadioButton("By championship position");
		rdbtnposition.setBounds(248, 293, 218, 43);
		contentPane.add(rdbtnposition);
		btnListAthletesStats.setBounds(24, 293, 218, 43);
		contentPane.add(btnListAthletesStats);
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnalphabetic);
		group.add(rdbtnposition);
		btnListAthletes = new JButton("List of athletes");
		btnListAthletes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnalphabetic.isSelected())
					Championship.sortByAlphabeticOrder();
				for (Athlete athete : Championship.getSelectionAthletes()) {
					textArea.setText(Championship.ListAthletes());
				}

			}
		});
		btnListAthletes.setBounds(24, 393, 218, 43);
		contentPane.add(btnListAthletes);
		
		listRaceStats = new JButton("List Race Stats");
		listRaceStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Method for listing race Stats
				textArea.setText( RaceManager.updateRaceResults());
				
			}
		});
		listRaceStats.setBounds(24, 455, 218, 43);
		contentPane.add(listRaceStats);
		
		JLabel background = new JLabel("New label");
		background.setIcon(new ImageIcon(Scoreboard.class.getResource("/Image/Scoreboard_background.png")));
		background.setBounds(0, 0, 574, 686);
		contentPane.add(background);

		newRace = new JButton("New Race");
		newRace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Method for listing race Stats
				textArea.setText("");
				if (Championship.getIndexRace()<4) {
					controller.getWindowRace().reset();
					controller.startRace();
					newRace.setVisible(false);
				} else {

                }

			}
		});
		newRace.setBounds(248, 522, 218, 38);
		contentPane.add(newRace);
		newRace.setVisible(false);
	
	}
	
	 //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	public void setNewRace(){
		newRace.setVisible(true);
		contentPane.revalidate();
		contentPane.repaint();
	}
}
