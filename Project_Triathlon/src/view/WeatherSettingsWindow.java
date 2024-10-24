package view;

import dataaccess.WeatherDAO;
import model.weather.MeasurementUnit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
public class WeatherSettingsWindow extends JFrame{
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
    private JPanel contentPane;
    private InputPanel inputPanel;
    private TablePanel tablePanel;
    private WeatherDAO weatherDAO;
    private JLabel messageLabel;
    
    //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
    public WeatherSettingsWindow()  {
        weatherDAO = new WeatherDAO();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 20, 800, 640);
        setResizable(false);
        contentPane = new JPanel(new BorderLayout(10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(getClass().getResource("/Image/weatherSettingsBackground.png"));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        messageLabel = new JLabel(" ", SwingConstants.CENTER);
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setBackground(Color.RED);
        messageLabel.setOpaque(false);
        contentPane.add(messageLabel, BorderLayout.SOUTH);

        // Add specific panels
        inputPanel = new InputPanel();
        contentPane.add(inputPanel, BorderLayout.NORTH);

        tablePanel = new TablePanel(inputPanel, weatherDAO);
        contentPane.add(tablePanel, BorderLayout.CENTER);

        ButtonPanel buttonPanel = new ButtonPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        buttonPanel.getExitButton().addActionListener(e -> dispose());
        buttonPanel.getConfirmButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    int selectedRow = tablePanel.getWeatherTable().getSelectedRow();
                    if (selectedRow == -1)
                        insertWeatherConditions();
                    else
                        updateWeatherCondition();
                }
            }
        });
        buttonPanel.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteWeatherCondition();
            }
        });
        buttonPanel.getCancelButton().addActionListener(e -> {
            clearFields();
        });
    }
    


  //------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
    private void insertWeatherConditions() {
        String description = inputPanel.getDescription();
        String measurementUnit = inputPanel.getMeasurementUnit();
        double upperTier = inputPanel.getUpperTier();
        double lowerTier = inputPanel.getLowerTier();
        double swimmingWeathering = inputPanel.getSwimmingImpact();
        double cyclingWeathering = inputPanel.getCyclingImpact();
        double pedestrianismWeathering = inputPanel.getPedestrianismImpact();

        try {
            weatherDAO.insertWeatherCondition(new MeasurementUnit(measurementUnit), upperTier, lowerTier,
                    swimmingWeathering, cyclingWeathering, pedestrianismWeathering, description);
            JOptionPane.showMessageDialog(this, "Weather condition inserted.");
            clearFields();
            tablePanel.loadWeatherConditions(weatherDAO);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error when inserting weather condition: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void updateWeatherCondition() {
        int selectedRow = tablePanel.getWeatherTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a weather condition to update",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int weatherId =  (int) tablePanel.getWeatherTable().getValueAt(selectedRow, 0);
        String description = inputPanel.getDescription();
        String measurementUnit = inputPanel.getMeasurementUnit();
        try {
            double upperTier = inputPanel.getUpperTier();
            double lowerTier = inputPanel.getLowerTier();
            double swimmingImpact = inputPanel.getSwimmingImpact();
            double cyclingImpact = inputPanel.getCyclingImpact();
            double pedestrianismImpact = inputPanel.getPedestrianismImpact();

            weatherDAO.updateWeatherCondition(weatherId, new MeasurementUnit(measurementUnit), lowerTier, upperTier,
                    swimmingImpact, cyclingImpact, pedestrianismImpact, description);

            JOptionPane.showMessageDialog(this, "Weather condition updated.");
            clearFields();
            tablePanel.loadWeatherConditions(weatherDAO);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please insert numbers in number fields.", "Format error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error when updating weather condition: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteWeatherCondition() {
        int selectedRow = tablePanel.getWeatherTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a weather condition to delete",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this weather condition?",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            int weatherId = (int) tablePanel.getWeatherTable().getValueAt(selectedRow, 0);
            long weatherIdLong = (long) weatherId;
            try {
                weatherDAO.deleteWeatherCondition(weatherIdLong);
                JOptionPane.showMessageDialog(this, "Weather condition deleted successfully");
                clearFields();
                tablePanel.loadWeatherConditions(weatherDAO); // Reload the table after deletion
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting weather condition: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private boolean validateFields() {
        if (inputPanel.getDescription().isBlank()) {
            setMessage("The description is obligatory.");
            inputPanel.getDescriptionField().requestFocus();
            return false;
        }

        if (inputPanel.getMeasurementUnit().isBlank()) {
            setMessage("Measurement unit is obligatory.");
            inputPanel.getMeasurementUnitField().requestFocus();
            return false;
        }

        try {
            double lowerTier = inputPanel.getLowerTier();
            double upperTier = inputPanel.getUpperTier();

            if (lowerTier >= upperTier) {
                setMessage("The lower tier has to be lower than the upper tier.");
                inputPanel.getLowerTierField().requestFocus();
                return false;
            }

            double swimmingWeathering = inputPanel.getSwimmingImpact();
            if (!isBetween(swimmingWeathering, -30, 30)) {
                setMessage("The weather impact in the swimming stage of the race has to be between -30 and 30.");
                inputPanel.getSwimmingImpactField().requestFocus();
                return false;
            }

            double cyclingWeathering = inputPanel.getCyclingImpact();
            if (!isBetween(cyclingWeathering, -30, 30)) {
                setMessage("The weather impact in the cycling stage of the race has to be between -30 and 30.");
                inputPanel.getCyclingImpactField().requestFocus();
                return false;
            }

            double pedestrianismWeathering = inputPanel.getPedestrianismImpact();
            if (!isBetween(pedestrianismWeathering, -30, 30)) {
                setMessage("The weather impact in the pedestrianism stage of the race has to be between -30 and 30.");
                inputPanel.getPedestrianismImpactField().requestFocus();
                return false;
            }

        } catch (NumberFormatException e) {
            setMessage("Please ensure all numeric fields contain valid numbers.");
            return false;
        }

        return true;
    }
    private boolean isBetween(double number, int lower, int upper){
        return number<= upper && number >= lower;
    }

    private void setMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }
    private void clearFields() {
        inputPanel.setDescription("");
        inputPanel.setMeasurementUnit("");
        inputPanel.setUpperTier("");
        inputPanel.setLowerTier("");
        inputPanel.setSwimmingImpact("");
        inputPanel.setCyclingImpact("");
        inputPanel.setPedestrianismImpact("");
        tablePanel.getWeatherTable().clearSelection();
    }

}

