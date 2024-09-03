package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.sun.media.jfxmedia.MediaException;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import view.MusicPlayer;

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
        contentPane.setBackground(Color.BLACK);

        // Initialize JFXPanel
        fxPanel = new JFXPanel();
        fxPanel.setBounds(0, 0, 960, 540); // Full screen
        contentPane.add(fxPanel);

        // Start JavaFX in a separate thread
        SwingUtilities.invokeLater(() -> {
            Platform.runLater(() -> {
                try {
                    initializeMediaPlayer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
    }

    private void initializeMediaPlayer() {
        try {
            String path = getClass().getResource("/Image/SAGA.mp4").toExternalForm();
            Media media = new Media(path);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);
            StackPane root = new StackPane(mediaView);
            Scene scene = new Scene(root, 960, 540);
            fxPanel.setScene(scene);
            MusicPlayer.music("/music/SAGAMusic.wav");
            mediaPlayer.setOnReady(() -> {
                System.out.println("MediaPlayer is ready");
                mediaPlayer.play();
                setVisible(true); // Prepare music
                MusicPlayer.playMusic();
            });

            mediaPlayer.setOnEndOfMedia(() -> {
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);  // Hide WindowSAGA
                    windowStart.setVisible(true);  // Show WindowStart
                });
            });
            mediaPlayer.setOnError(() -> {
                System.err.println("Error loading video: " + mediaPlayer.getError().getMessage());
                showWindowSAGAAndSwitch();
            });

        } catch (IllegalArgumentException e) {
            System.err.println("IllegalArgument: " + e.getMessage());
            e.printStackTrace();
        } catch (MediaException e) {
            System.err.println("Error when charging video: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalStateException e) {
            System.err.println("Error in JFXPanel state: " + e.getMessage());
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Resource not found: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void showWindowSAGAAndSwitch() {
        setVisible(true);
        MusicPlayer.playMusic();

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                windowStart.setVisible(true);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}
