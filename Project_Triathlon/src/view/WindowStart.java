package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import view.WindowRace;
import controller.Championship;
public class WindowStart extends JFrame {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private WindowRace wr;
    private Scoreboard dt;
    private static Clip music;
    //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	public WindowStart(Championship controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnNewButton_2 = new JButton("START");
		btnNewButton_2.setBounds(35, 314, 201, 35);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				music.close();
				wr = controller.getWindowRace();
				wr.setVisible(true);
				controller.startRace();
			    controller.getScoreboard().setVisible(true);
			    music("/music/Road_Runner.wav");
			    playMusic();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("LOAD");
		btnNewButton_1.setBounds(35, 426, 201, 35);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("EXIT");
		btnNewButton.setBounds(35, 371, 201, 35);
		contentPane.add(btnNewButton);
		
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


	
	
}
