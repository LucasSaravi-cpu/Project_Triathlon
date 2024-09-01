package view;

import dataaccess.WeatherDAO;
import model.weather.MeasurementUnit;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class WeatherSettingsWindow extends JFrame
{
    private JPanel contentPane;
    private InputPanel inputPanel;
    private TablePanel tablePanel;
    private WeatherDAO weatherDAO;
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

        // Add specific panels
        inputPanel = new InputPanel();
        contentPane.add(inputPanel, BorderLayout.NORTH);

        tablePanel = new TablePanel(inputPanel, weatherDAO);
        contentPane.add(tablePanel, BorderLayout.CENTER);

        ButtonPanel buttonPanel = new ButtonPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        buttonPanel.getExitButton().addActionListener(e -> dispose());
        buttonPanel.getConfirmButton().addActionListener(e -> {
            // Logic to confirm
        });
        buttonPanel.getCancelButton().addActionListener(e -> {
            // Logic to cancel
        });
    }
    private void insertWeatherConditions() {
        String description = inputPanel.getDescription();
        String measurementUnit = inputPanel.getMeasurementUnit();
        double upperTier = inputPanel.getUpperTier();
        double lowerTier = inputPanel.getLowerTier();
        double swimmingWeathering = inputPanel.getSwimmingWeathering();
        double cyclingWeathering = inputPanel.getCyclingWeathering();
        double pedestrianismWeathering = inputPanel.getPedestrianismWeathering();

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

        long weatherId = (long) tablePanel.getWeatherTable().getValueAt(selectedRow, 0);
        String description = inputPanel.getDescription();
        String measurementUnit = inputPanel.getMeasurementUnit();
        try {
            double upperTier = inputPanel.getUpperTier();
            double lowerTier = inputPanel.getLowerTier();
            double swimmingWeathering = inputPanel.getSwimmingWeathering();
            double cyclingWeathering = inputPanel.getCyclingWeathering();
            double pedestrianismWeathering = inputPanel.getPedestrianismWeathering();

            weatherDAO.updateWeatherCondition((int) weatherId, new MeasurementUnit(measurementUnit), upperTier, lowerTier,
                    swimmingWeathering, cyclingWeathering, pedestrianismWeathering, description);

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
            long weatherId = (long) tablePanel.getWeatherTable().getValueAt(selectedRow, 0); // Weather condition ID from the table

            try {
                weatherDAO.deleteWeatherCondition(weatherId);
                JOptionPane.showMessageDialog(this, "Weather condition deleted successfully");
                clearFields();
                tablePanel.loadWeatherConditions(weatherDAO); // Reload the table after deletion
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting weather condition: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void clearFields() {
        inputPanel.setDescription("");
        inputPanel.setMeasurementUnit("");
        inputPanel.setUpperTier("");
        inputPanel.setLowerTier("");
        inputPanel.setSwimmingWeathering("");
        inputPanel.setCyclingWeathering("");
        inputPanel.setPedestrianismWeathering("");
    }

}

