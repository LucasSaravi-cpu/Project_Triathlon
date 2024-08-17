package view;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Championship;
import model.WeatherConditions;
import java.awt.BorderLayout;
import javax.swing.JTextPane;

public class Weatherboard extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel weatherLabel;
    
    // Map to store URLs for different weather conditions
    private static final Map<String, String> weatherIcons = new HashMap<>();

    static {
        // Initialize the map with URLs for different weather conditions
        weatherIcons.put("Very low temperature", "/Image/NormalTemperature.png");
        weatherIcons.put("Normal temperature", "/Image/NormalTemperature.png");
        weatherIcons.put("Warm temperature", "/Image/NormalTemperature.png");
        weatherIcons.put("High temperature", "/Image/NormalTemperature.png");
        weatherIcons.put("Tailwind", "/Image/NormalTemperature.png");
        weatherIcons.put("Headwind", "/Image/NormalTemperature.png");
    }

    public Weatherboard(Championship controller) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(10, 10, 267, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        weatherLabel = new JLabel("");
        weatherLabel.setBounds(5, 5, 107, 153);
        contentPane.add(weatherLabel);
    }

    public void updateWeatherLabel(WeatherConditions weatherCondition) {
        if (weatherCondition != null) {
            String description = weatherCondition.getDescription(); // Assuming this method returns the condition description
            String imageUrl = getWeatherImageUrl(description);

            if (imageUrl != null) {
                ImageIcon icon = new ImageIcon(getClass().getResource(imageUrl));
                Image image = icon.getImage(); // get ImageIcon
                Image scaledImage = image.getScaledInstance(70, 70, Image.SCALE_SMOOTH); // Scale image
                ImageIcon scaledIcon = new ImageIcon(scaledImage); // creates a new ImageIcon with Scaled image
                weatherLabel.setIcon(scaledIcon);
               
            } else {
                // Handle case where no image URL is found
                weatherLabel.setIcon(null);
            }
        }
        
   
    }
    
    
    private String getWeatherImageUrl(String description) {
        // Return the URL associated with the description
        return weatherIcons.get(description);
    }
}