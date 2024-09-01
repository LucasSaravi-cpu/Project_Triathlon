package view;
import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    private JTextField descriptionField;
    private JTextField measurementUnitField;
    private JTextField upperTierField;
    private JTextField lowerTierField;
    private JTextField swimmingWeatheringField;
    private JTextField cyclingWeatheringField;
    private JTextField pedestrianismWeatheringField;

    public InputPanel() {
        setOpaque(false);
        setLayout(new GridLayout(7, 2, 10, 10));

        // Description
        add(new JLabel("Description:"));
        descriptionField = new JTextField();
        add(descriptionField);

        // Measurement Unit
        add(new JLabel("Measurement Unit:"));
        measurementUnitField = new JTextField();
        add(measurementUnitField);

        // Upper Tier (Max Value)
        add(new JLabel("Upper Tier (Max Value):"));
        upperTierField = new JTextField();
        add(upperTierField);

        // Lower Tier (Min Value)
        add(new JLabel("Lower Tier (Min Value):"));
        lowerTierField = new JTextField();
        add(lowerTierField);

        // Swimming Weathering
        add(new JLabel("Swimming Weathering:"));
        swimmingWeatheringField = new JTextField();
        add(swimmingWeatheringField);

        // Cycling Weathering
        add(new JLabel("Cycling Weathering:"));
        cyclingWeatheringField = new JTextField();
        add(cyclingWeatheringField);

        // Pedestrianism Weathering
        add(new JLabel("Pedestrianism Weathering:"));
        pedestrianismWeatheringField = new JTextField();
        add(pedestrianismWeatheringField);
    }

    // Métodos para acceder a los valores ingresados
    public String getDescription() {
        return descriptionField.getText();
    }

    public String getMeasurementUnit() {
        return measurementUnitField.getText();
    }

    public double getUpperTier() {
        return Double.parseDouble(upperTierField.getText());
    }

    public double getLowerTier() {
        return Double.parseDouble(lowerTierField.getText());
    }

    public double getSwimmingWeathering() {
        return Double.parseDouble(swimmingWeatheringField.getText());
    }

    public double getCyclingWeathering() {
        return Double.parseDouble(cyclingWeatheringField.getText());
    }

    public double getPedestrianismWeathering() {
        return Double.parseDouble(pedestrianismWeatheringField.getText());
    }
    public void setDescription(String description) {
        descriptionField.setText(description);
    }

    public void setMeasurementUnit(String measurementUnit) {
        measurementUnitField.setText(measurementUnit);
    }

    public void setUpperTier(String upperTier) {
        upperTierField.setText(upperTier);
    }

    public void setLowerTier(String lowerTier) {
        lowerTierField.setText(lowerTier);
    }

    public void setSwimmingWeathering(String swimmingWeathering) {
        swimmingWeatheringField.setText(swimmingWeathering);
    }

    public void setCyclingWeathering(String cyclingWeathering) {
        cyclingWeatheringField.setText(cyclingWeathering);
    }

    public void setPedestrianismWeathering(String pedestrianismWeathering) {
        pedestrianismWeatheringField.setText(pedestrianismWeathering);
    }
}