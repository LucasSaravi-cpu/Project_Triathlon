package view;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {
    private JTable table;

    public TablePanel() {
        setLayout(new BorderLayout());
        setOpaque(false);
        String[] columnNames = {"Parameter", "Value"};
        Object[][] data = {
                {"Temperature", "20°C"},
                {"Humidity", "80%"},
        };
        table = new JTable(data, columnNames);
        table.setOpaque(false); // Ensure the table is not opaque
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTable getTable() {
        return table;
    }
}