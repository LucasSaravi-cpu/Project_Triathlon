package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import model.weather.WeatherConditions;

import controller.Championship;

public class Weatherboard extends JFrame {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextPane textPane;
    //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\

    public Weatherboard() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(10, 10, 267, 200);
        setResizable(false);
        // Custom JPanel with background image
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(getClass().getResource("/Image/weatherBackground.png"));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        contentPane.setLayout(null); // Disable layout manager for manual positioning
        setContentPane(contentPane);
        ImageIcon weathericon = new ImageIcon(getClass().getResource("/Image/dialog.png"));
        Image scaledweatherImage = weathericon.getImage().getScaledInstance(170, 110, Image.SCALE_SMOOTH);
        JLabel dialogLabel = new JLabel(new ImageIcon(scaledweatherImage));
        dialogLabel.setBounds(80, 10, 170, 110);
        contentPane.add(dialogLabel);
        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setBounds(120, 45, 98, 60);
        textPane.setOpaque(false);
        textPane.setFocusable(false);
        textPane.setForeground(Color.BLACK);
        Font font = FontCharger.loadCustomFont("/fonts/ChocoShake.ttf").deriveFont(10f);
        textPane.setFont(font);

        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        contentPane.add(textPane);
        contentPane.setComponentZOrder(textPane, 0);
        contentPane.setComponentZOrder(dialogLabel, 1);
        // Bear icon label - Bottom Left
        ImageIcon icon = new ImageIcon(getClass().getResource("/Image/weatherman.png"));
        Image scaledImage = icon.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        JLabel bearLabel = new JLabel(new ImageIcon(scaledImage));
        bearLabel.setBounds(10, 60, 110, 110); // Positioned 10px margin from left and bottom
        contentPane.add(bearLabel);
    }

  //------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
    public void updateWeatherLabel(WeatherConditions weatherCondition) {
        if (weatherCondition != null) {
            textPane.setText(Championship.getWeatherConditionsList(weatherCondition));

        }
    }

}