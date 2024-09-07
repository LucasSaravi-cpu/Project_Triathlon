package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadingFrame extends JFrame {

    private JProgressBar progressBar;
    private JLabel loadingLabel;
    private int dotCount = 0;
    private Timer timer;

    public LoadingFrame() {
        setTitle("Loading");
        setSize(960, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Custom panel for background image
        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(getClass().getResource("/Image/loadingFrameBackground.png"));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        });
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Create invisible components for vertical spacing (glue)
        getContentPane().add(Box.createVerticalGlue()); // Pushes the label down

        // Create loading label with centered alignment
        loadingLabel = new TitleLabel("Loading game", FontCharger.loadCustomFont("/fonts/Dinofiles.ttf").deriveFont(50f));
        loadingLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center label horizontally
        getContentPane().add(loadingLabel);

        // Create invisible glue for spacing between label and progress bar
        getContentPane().add(Box.createVerticalGlue()); // Pushes the progress bar down

        // Create progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0); // Initialize at 0
        progressBar.setStringPainted(true);
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT); // Center progress bar horizontally
        getContentPane().add(progressBar);

        // Timer for loading animation
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dotCount = (dotCount + 1) % 4;
                String dots = ".".repeat(dotCount);
                loadingLabel.setText("Loading game" + dots);
            }
        });
        timer.start();
    }

    public void updateProgress(int progress) {
        progressBar.setValue(progress);
    }

    public void close() {
        timer.stop();
        dispose();
    }
}
