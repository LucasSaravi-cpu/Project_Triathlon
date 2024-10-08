package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import controller.Championship;

public class WindowStart extends JFrame {

	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\


	private static final long serialVersionUID = 1L;
	private Championship controller;
	private JPanel contentPane;
	private TitleLabel title;
	private WindowRace wr;
	private static Clip music;
	private Weatherboard weatherboard;
	private WeatherSettingsWindow cwp;

	//------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	public WindowStart(Championship controller) {
		this.controller = controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon icon = new ImageIcon(getClass().getResource("/Image/ImgStart.png"));
				g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		title = new TitleLabel("Triathlon Quest", FontCharger.loadCustomFont("/fonts/Dinofiles.ttf").deriveFont(60f));
		title.setBounds(100, 20, 648, 100);
		contentPane.add(title);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Image/startButton.png"));
		JButton startbutton = new JButton(scaleImage(icon));
		startbutton.setBounds(35, 314, 201, 35);
		startbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});

		contentPane.setLayout(null);
		contentPane.add(startbutton);
		icon = new ImageIcon(getClass().getResource("/Image/loadButton.png"));
		JButton loadButton = new JButton(scaleImage(icon));
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Championship.loadGameState("/Project_Triathlon/Project_Triathlon/src/savestates/gameState.dat");
				if (controller.getRaceIndex()<4)
                   startGame();
				else {
					SwingUtilities.invokeLater(() -> {
						MusicPlayer.close();
						controller.endChampionship();
						controller.setVisibleWindowTrophies(true);
						MusicPlayer.music("/music/EndMusic.wav");
						MusicPlayer.playMusic();
					});
				}

			}
		});
		loadButton.setBounds(35, 426, 201, 35);
		contentPane.add(loadButton);
		icon = new ImageIcon(getClass().getResource("/Image/customWeatherButton.png"));
		JButton weatherButton = new JButton(scaleImage(icon));
		weatherButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cwp = controller.getCustomWeatherPanel();
				cwp.setVisible(true);
			}
		});
		weatherButton.setBounds(35, 481, 201, 35);
		contentPane.add(weatherButton);
		icon = new ImageIcon(getClass().getResource("/Image/exitButton.png"));
		JButton exitButton = new JButton(scaleImage(icon));
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setBounds(35, 371, 201, 35);
		contentPane.add(exitButton);
		/*
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 841, 540);
		lblNewLabel.setIcon(new ImageIcon(WindowStart.class.getResource("/Image/ImgStart.png")));
		contentPane.add(lblNewLabel);*/
	}


	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\	

	public ImageIcon scaleImage(ImageIcon newIcon) {
		Image scaledImage = newIcon.getImage().getScaledInstance(201, 35, Image.SCALE_SMOOTH);
		return new ImageIcon(scaledImage);
	}


	public void setnotVisible() {
		setVisible(false);
	}

	private void startGame() {
		MusicPlayer.close();
		Championship.setWeatherConditions();
		controller.initializeRaceData();
		controller.promptNeopreneAndStartRace();
		controller.getScoreboard().setVisible(true);
		MusicPlayer.music("/music/Road_Runner.wav");
		MusicPlayer.playMusic();
		weatherboard = controller.getWeatherboard();
		weatherboard.setVisible(true);
		controller.getWindowChronometer().setVisible(true);
		setnotVisible();
	}
}