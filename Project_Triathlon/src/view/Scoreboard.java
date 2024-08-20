package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalRadioButtonUI;

import controller.Championship;
import model.Athlete;
import model.RaceManager;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Image/pauseMusicButton.png"));
		Image scaledImage = icon.getImage().getScaledInstance(100, 26, Image.SCALE_SMOOTH);
		JButton btnStopMusic = new JButton(new ImageIcon(scaledImage));
		btnStopMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				WindowStart.getMusic().stop();
				
			}
		});
		icon = new ImageIcon(getClass().getResource("/Image/playMusicButton.png"));
		scaledImage = icon.getImage().getScaledInstance(100, 26, Image.SCALE_SMOOTH);
		JButton btnPlayMusic = new JButton(new ImageIcon(scaledImage));
		btnPlayMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				WindowStart.playMusic();
			}
			
			
		});
		btnPlayMusic.setBounds(134, 603, 100, 26);
		contentPane.add(btnPlayMusic);
		btnStopMusic.setBounds(24, 603, 100, 26);
		contentPane.add(btnStopMusic);
		
		

		textArea = new TextArea();
		textArea.setBounds(21, 43, 517, 216);
		contentPane.add(textArea);
		icon = new ImageIcon(getClass().getResource("/Image/listAthletesStatsButton.png"));
		btnListAthletesStats = new JButton(scaleImage(icon));
		btnListAthletesStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for(Athlete athlete: Championship.getAthletes())
				{
					textArea.setText(athlete.listStats());
				}
			}
		});
		icon = new ImageIcon(getClass().getResource("/Image/cleanButton.png"));
		btnClean = new JButton(scaleImage(icon));
		btnClean.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		textArea.setText("");
		 	}
		 });
		btnClean.setBounds(24, 522, 218, 43);
		contentPane.add(btnClean);
		Font myFont = loadCustomFont("/fonts/Kanit-ThinItalic.ttf");
		rdbtnalphabetic = new JRadioButton("By alphabetic order");
		rdbtnalphabetic.setBounds(247, 347, 218, 43);
		rdbtnalphabetic.setFont(myFont);
		rdbtnalphabetic.setForeground(Color.WHITE);
		rdbtnalphabetic.setOpaque(false);
		rdbtnalphabetic.setUI(new MetalRadioButtonUI() {
			@Override
			protected void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setFont(myFont);

				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				g2.setColor(Color.BLACK);
				for (int i = -2; i <= 2; i++) {
					for (int j = -2; j <= 2; j++) {
						if (i != 0 || j != 0) {
							g2.drawString(text, textRect.x + i, textRect.y + j);
						}
					}
				}

				g2.setColor(Color.WHITE);
				g2.drawString(text, textRect.x, textRect.y);

				g2.dispose();
			}
		});
		contentPane.add(rdbtnalphabetic);
		rdbtnposition = new JRadioButton("By championship position");
		rdbtnposition.setBounds(248, 293, 218, 43);
		rdbtnposition.setFont(myFont);
		rdbtnposition.setForeground(Color.WHITE);
		rdbtnposition.setOpaque(false);
		rdbtnposition.setUI(new MetalRadioButtonUI() {
			@Override
			protected void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setFont(myFont);
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.BLACK);
				for (int i = -2; i <= 2; i++) {
					for (int j = -2; j <= 2; j++) {
						if (i != 0 || j != 0) {
							g2.drawString(text, textRect.x + i, textRect.y + j);
						}
					}
				}
				g2.setColor(Color.WHITE);
				g2.drawString(text, textRect.x, textRect.y);
				g2.dispose();
			}
		});
		contentPane.add(rdbtnposition);
		btnListAthletesStats.setBounds(24, 293, 218, 43);
		contentPane.add(btnListAthletesStats);
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnalphabetic);
		group.add(rdbtnposition);
		icon = new ImageIcon(getClass().getResource("/Image/listOfAthletesButton.png"));
		btnListAthletes = new JButton(scaleImage(icon));
		btnListAthletes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnalphabetic.isSelected())
					Championship.sortByAlphabeticOrder();
				for (Athlete athete : Championship.getAthletes()) {
					textArea.setText(Championship.ListAthletes());
				}

			}
		});
		btnListAthletes.setBounds(24, 393, 218, 43);
		contentPane.add(btnListAthletes);
		icon = new ImageIcon(getClass().getResource("/Image/listRaceStats.png"));
		listRaceStats = new JButton(scaleImage(icon));
		listRaceStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Method for listing race Stats
				textArea.setText(controller.updateRaceResults());
				
			}
		});
		listRaceStats.setBounds(24, 455, 218, 43);
		contentPane.add(listRaceStats);
		
		JLabel background = new JLabel("New label");
		background.setIcon(new ImageIcon(Scoreboard.class.getResource("/Image/Scoreboard_background.png")));
		background.setBounds(0, 0, 574, 686);
		contentPane.add(background);
		icon = new ImageIcon(getClass().getResource("/Image/newGameButton.png"));
		newRace = new JButton(scaleImage(icon));
		newRace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Method for listing race Stats
				textArea.setText("");
				if (Championship.getIndexRace()<4) {
					controller.getWindowRace().reset();
					controller.resetTimer();
					controller.startRace();
					newRace.setVisible(false);
				} else {
					controller.endChampionship();
                }

			}
		});
		newRace.setBounds(248, 522, 218, 43);
		contentPane.add(newRace);
		newRace.setVisible(false);
	
	}
	public ImageIcon scaleImage(ImageIcon newIcon){
		Image scaledImage = newIcon.getImage().getScaledInstance(218, 43, Image.SCALE_SMOOTH);
		return new ImageIcon(scaledImage);
	}
	public static Font loadCustomFont(String path) {
		try {
			InputStream is = Scoreboard.class.getResourceAsStream(path);
			if (is == null) {
				throw new IOException("Font not found: " + path);
			}

			Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFont);
			return customFont.deriveFont(Font.BOLD, 16f);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
			return null;
		}
	}
	 //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	public void setNewRace(){
		newRace.setVisible(true);
		contentPane.revalidate();
		contentPane.repaint();
	}
}
