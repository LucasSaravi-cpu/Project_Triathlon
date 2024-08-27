package view;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.weather.WeatherConditions;
import javax.swing.JTextArea;

import controller.Championship;

public class Weatherboard extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel weatherLabel;

    // Map to store URLs for different weather conditions
    private static final Map<String, String> weatherIcons = new HashMap<>();
    private JTextArea textArea;

    static {
        // Initialize the map with URLs for different weather conditions
        weatherIcons.put("Very low temperature", "/Image/LowTemperature.png");
        weatherIcons.put("Normal temperature", "/Image/NormalTemperature.png");
        weatherIcons.put("Warm temperature", "/Image/WarmTemperature.png");
        weatherIcons.put("High temperature", "/Image/HighTemperature.png");
        weatherIcons.put("Tailwind", "/Image/Tailwind.png");
        weatherIcons.put("Headwind", "/Image/Headwind.png");
    //    weatherIcons.put("Rain", "/Image/Rain.png");
    }   

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
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBounds(145, 93, 98, 60);
        textArea.setLineWrap(true); 
        textArea.setWrapStyleWord(true); 
        textArea.setOpaque(false); 
        textArea.setForeground(Color.BLACK); 
        
        contentPane.add(textArea);

        // Weather label - Top Right
        weatherLabel = new JLabel("");
        weatherLabel.setBounds(157, 10, 70, 70); // 70x70 size, positioned with 10px margin from right and top
        contentPane.add(weatherLabel);
        weatherLabel.setFocusable(false);
        weatherLabel.setRequestFocusEnabled(false);
        // Bear icon label - Bottom Left
        ImageIcon icon = new ImageIcon(getClass().getResource("/Image/weatherman.png"));
        Image scaledImage = icon.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        JLabel bearLabel = new JLabel(new ImageIcon(scaledImage));
        bearLabel.setBounds(10, 60, 110, 110); // Positioned 10px margin from left and bottom
        contentPane.add(bearLabel);
    }

    public void updateWeatherLabel(WeatherConditions weatherCondition) {
        if (weatherCondition != null) {
            String description = weatherCondition.getDescription();
            String imageUrl = getWeatherImageUrl(description);

            if (imageUrl != null) {
                ImageIcon icon = new ImageIcon(getClass().getResource(imageUrl));
                Image scaledImage = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                weatherLabel.setIcon(new ImageIcon(scaledImage));
                textArea.setText( Championship.GetListWeatherCondition(weatherCondition));
            } else {
                weatherLabel.setIcon(null);
            }
        }
    }

    private String getWeatherImageUrl(String description) {
        return weatherIcons.get(description);
    }
}