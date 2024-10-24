package view;

import dataaccess.WeatherDAO;
import model.weather.MeasurementUnit;
import model.weather.WeatherConditions;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class TablePanel extends JPanel {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
    private JTable table;
    private DefaultTableModel tableModel;
    
    //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
    public TablePanel(InputPanel inputPanel, WeatherDAO weatherDAO) {
        setLayout(new BorderLayout());
        setOpaque(false);

        String[] columnNames = {"Id", "Description", "Measurement Unit", "Lower Tier", "Upper Tier", "Swimming Impact", "Cycling Impact", "Pedestrianism Impact"};
        tableModel = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
        };
        table = new JTable(tableModel);
        table.setOpaque(false);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane, BorderLayout.CENTER);

        loadWeatherConditions(weatherDAO);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        String description = (String) tableModel.getValueAt(selectedRow, 1);
                        MeasurementUnit measurementUnitObj = (MeasurementUnit) tableModel.getValueAt(selectedRow, 2);
                        String measurementUnit = measurementUnitObj.toString();
                        double lowerTier = (double) tableModel.getValueAt(selectedRow, 3);
                        double upperTier = (double) tableModel.getValueAt(selectedRow, 4);
                        double swimmingImpact = (double) tableModel.getValueAt(selectedRow, 5);
                        double cyclingImpact = (double) tableModel.getValueAt(selectedRow, 6);
                        double pedestrianismImpact = (double) tableModel.getValueAt(selectedRow, 7);
                        inputPanel.setDescription(description);
                        inputPanel.setMeasurementUnit(measurementUnit);
                        inputPanel.setLowerTier(String.valueOf(lowerTier));
                        inputPanel.setUpperTier(String.valueOf(upperTier));
                        inputPanel.setSwimmingImpact(String.valueOf(swimmingImpact));
                        inputPanel.setCyclingImpact(String.valueOf(cyclingImpact));
                        inputPanel.setPedestrianismImpact(String.valueOf(pedestrianismImpact));

                    }
                }
            }
        });
    }
    
  //------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
    
    public JTable getWeatherTable() {
        return table;
    }
    
  //------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
    
    public void loadWeatherConditions(WeatherDAO weatherDAO) {
        WeatherDAO dao = new WeatherDAO();
        tableModel.setRowCount(0);
        try {
            List<WeatherConditions> weatherConditionsList = dao.getAllWeatherConditions();
            for (WeatherConditions weather : weatherConditionsList) {
                Object[] rowData = {
                        weather.getId(),
                        weather.getDescription(),
                        weather.getMeasurementUnit(),
                        weather.getLowerTier(),
                        weather.getUpperTier(),
                        weather.getSwimmingImpact(),
                        weather.getCyclingImpact(),
                        weather.getPedestrianismImpact()
                };
                tableModel.addRow(rowData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading weather conditions: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
   
}