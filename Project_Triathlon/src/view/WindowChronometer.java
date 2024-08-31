package view;

import java.awt.*;

import javax.swing.*;

import listeners.ChronometerListener;


public class WindowChronometer extends JFrame implements ChronometerListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel labelChronometer;



    public WindowChronometer() {
        setTitle("Chronometer");
        setSize(267, 200);
        setBounds(300, 10, 267, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(getClass().getResource("/Image/weatherBackground.png"));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setLayout(new BorderLayout());

        setContentPane(contentPane);
        labelChronometer = new JLabel("00:00:00", SwingConstants.CENTER);
        labelChronometer.setFont(FontCharger.loadCustomFont("/fonts/ChocoShake.ttf").deriveFont(40f));
        contentPane.add(labelChronometer, BorderLayout.CENTER);

        setVisible(false);
    }


    @Override
    public void onTimeruptodate(int Hours, int minutes, int seconds) {
        labelChronometer.setText(String.format("%02d:%02d:%02d", Hours, minutes,seconds));
    }

}