package view;

import javax.swing.*;
import java.awt.*;

public class WeatherSettingsWindow extends JFrame
{
    private JPanel contentPane;
    public WeatherSettingsWindow()  {
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
        InputPanel inputPanel = new InputPanel();
        contentPane.add(inputPanel, BorderLayout.NORTH);

        TablePanel tablePanel = new TablePanel();
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

}

