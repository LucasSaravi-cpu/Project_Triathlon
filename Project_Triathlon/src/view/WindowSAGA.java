package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class WindowSAGA extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JFXPanel fxPanel;
    private WindowStart windowStart;

    public WindowSAGA(WindowStart windowStart) {
        this.windowStart = windowStart;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 960, 540);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Initialize JFXPanel
        fxPanel = new JFXPanel();
        fxPanel.setBounds(0, 0, 960, 540); // Full screen
        contentPane.add(fxPanel);

        // Start JavaFX in a separate thread
        SwingUtilities.invokeLater(() -> {
            Platform.runLater(() -> {
                initializeMediaPlayer();
            });
        });
    }

    private void initializeMediaPlayer() {
        String path = getClass().getResource("/Image/SAGA.mp4").toExternalForm();
        Media media = new Media(path);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        StackPane root = new StackPane(mediaView);
        Scene scene = new Scene(root, 960, 540);
        fxPanel.setScene(scene);

        // Play music and video simultaneously
        mediaPlayer.setOnReady(() -> {
            mediaPlayer.play();  // Start video
            MusicPlayer.music("/music/SAGAMusic.wav");  // Prepare music
            MusicPlayer.playMusic();  // Start music
        });
        mediaPlayer.setOnEndOfMedia(() -> {
            SwingUtilities.invokeLater(() -> { // Pass the necessary objects
                setVisible(false);  // Hide WindowSAGA
                windowStart.setVisible(true);  // Show WindowStart
            });
        });
    }
}