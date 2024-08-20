package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import model.Chronometer;
import view.WindowRace;
import controller.Championship;
import model.Chronometer;
public class WindowStart extends JFrame {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TitleLabel title;
    private WindowRace wr;
    private static Clip music;
    private Weatherboard weatherboard;
    //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	public WindowStart(Championship controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 564);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		title = new TitleLabel("Triathlon Quest", Scoreboard.loadCustomFont("/fonts/Dinofiles.ttf").deriveFont(60f));
		title.setBounds(100, 20, 648, 100);
		contentPane.add(title);
		ImageIcon icon = new ImageIcon(getClass().getResource("/Image/startButton.png"));
		JButton startbutton = new JButton(scaleImage(icon));
		startbutton.setBounds(35, 314, 201, 35);
		startbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				music.close();
				wr = controller.getWindowRace();
				wr.setVisible(true);
				controller.startRace();
				controller.getScoreboard().setVisible(true);
				music("/music/Road_Runner.wav");
				playMusic();
				weatherboard = controller.getWeatherboard();
				weatherboard.setVisible(true);

				WindowsChronometer windows = new WindowsChronometer();
				controller.addChronometerListener(windows);
				controller.startChronometer();
			    
			}
		});

		contentPane.setLayout(null);
		contentPane.add(startbutton);
		icon = new ImageIcon(getClass().getResource("/Image/loadButton.png"));
		JButton loadButton = new JButton(scaleImage(icon));
		loadButton.setBounds(35, 426, 201, 35);
		contentPane.add(loadButton);
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 841, 540);
		lblNewLabel.setIcon(new ImageIcon(WindowStart.class.getResource("/Image/ImgStart.png")));
		contentPane.add(lblNewLabel);
	}
	
	
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\	
	public void music(String url) { 
		   
		   	   try {
	            // Load music
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(url));
	            music = AudioSystem.getClip();
	            music.open(audioInputStream);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }	   
  }
	


public static void playMusic() {
 if (music != null && !music.isRunning()) {
     // Play music in a continuous loop
     music.loop(Clip.LOOP_CONTINUOUSLY);
 }
}



public static Clip getMusic() {
	return music;
}


public static void setMusic(Clip music) {
	WindowStart.music = music;
}
public ImageIcon scaleImage(ImageIcon newIcon){
	Image scaledImage = newIcon.getImage().getScaledInstance(201, 35, Image.SCALE_SMOOTH);
	return new ImageIcon(scaledImage);
}

	
	
}
