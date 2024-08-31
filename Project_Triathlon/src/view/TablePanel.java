package view;

import dataaccess.WeatherDAO;
import model.weather.WeatherConditions;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class TablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    public TablePanel() {
        setLayout(new BorderLayout());
        setOpaque(false);

        String[] columnNames = {"Id", "Description", "Measurement Unit", "Lower Tier", "Upper Tier", "Swimming Weathering", "Cycling Weathering", "Pedestrianism Weathering"};
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

        loadWeatherConditions();
    }
    private void loadWeatherConditions() {
        WeatherDAO dao = new WeatherDAO();
        try {
            List<WeatherConditions> weatherConditionsList = dao.getAllWeatherConditions();
            for (WeatherConditions weather : weatherConditionsList) {
                Object[] rowData = {
                        weather.getId(),
                        weather.getDescription(),
                        weather.getMeasurementUnit(),
                        weather.getLowerTier(),
                        weather.getUpperTier(),
                        weather.getSwimmingWeathering(),
                        weather.getCyclingWeathering(),
                        weather.getPedestrianismWeathering()
                };
                tableModel.addRow(rowData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading weather conditions: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public JTable getTable() {
        return table;
    }
}