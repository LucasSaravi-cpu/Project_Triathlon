package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Championship;
import model.WeatherConditions;

public class Weatherboard extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel weatherLabel;

    public Weatherboard(Championship controller) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(10, 10, 267, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        weatherLabel = new JLabel("");
        weatherLabel.setBounds(25, 47, 400, 125); 
        contentPane.add(weatherLabel);
    }

    public void updateWeatherLabel(WeatherConditions weatherCondition) {
        if (weatherCondition != null) {
            weatherLabel.setText(weatherCondition.getDescription());
            
        }
    }
}
