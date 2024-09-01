package view;

import dataaccess.WeatherDAO;
import model.weather.MeasurementUnit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
public class WeatherSettingsWindow extends JFrame
{
    private JPanel contentPane;
    private InputPanel inputPanel;
    private TablePanel tablePanel;
    private WeatherDAO weatherDAO;
    private JLabel messageLabel;
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

        int weatherId =  (int) tablePanel.getWeatherTable().getValueAt(selectedRow, 0);
        String description = inputPanel.getDescription();
        String measurementUnit = inputPanel.getMeasurementUnit();
        try {
            double upperTier = inputPanel.getUpperTier();
            double lowerTier = inputPanel.getLowerTier();
            double swimmingWeathering = inputPanel.getSwimmingWeathering();
            double cyclingWeathering = inputPanel.getCyclingWeathering();
            double pedestrianismWeathering = inputPanel.getPedestrianismWeathering();

            weatherDAO.updateWeatherCondition(weatherId, new MeasurementUnit(measurementUnit), upperTier, lowerTier,
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
    private boolean validateFields(){
        boolean response = false;
        if (inputPanel.getDescription().isBlank()) {
            setMessage("The description is obligatory.");
            inputPanel.getDescriptionField().requestFocus();
        } else if (inputPanel.getLowerTier()>=inputPanel.getUpperTier()) {
            setMessage("The lower tier has to be lower than the upper tier.");
            inputPanel.getLowerTierField().requestFocus();
        } else if (!isBetween(inputPanel.getSwimmingWeathering(), -30, 30)) {
            setMessage("The weather impact in the swimming stage of the race has to be between -30 and 30.");
            inputPanel.getSwimmingWeatheringField().requestFocus();
        } else if (!isBetween(inputPanel.getCyclingWeathering(), -30, 30)) {
            setMessage("The weather impact in the cycling stage of the race has to be between -30 and 30.");
            inputPanel.getCyclingWeatheringField().requestFocus();
        } else if (!isBetween(inputPanel.getPedestrianismWeathering(), -30, 30)) {
            setMessage("The weather impact in the pedestrianism stage of the race has to be between -30 and 30.");
            inputPanel.getPedestrianismWeatheringField().requestFocus();
        } else
            response = true;
        return response;

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
        inputPanel.setSwimmingWeathering("");
        inputPanel.setCyclingWeathering("");
        inputPanel.setPedestrianismWeathering("");
        tablePanel.getWeatherTable().clearSelection();
    }

}

