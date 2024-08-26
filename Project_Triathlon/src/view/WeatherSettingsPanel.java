package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class WeatherSettingsPanel extends JFrame
{
    private JPanel contentPane;
    public WeatherSettingsPanel()
    {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(5, 2, 10, 10));
        setResizable(false);
        JLabel descriptionLabel = new JLabel("Condition Description:");
        JTextField descriptionField = new JTextField();

        JLabel unitLabel = new JLabel("Measurement Unit:");
        JTextField unitField = new JTextField();

        JLabel upperTierLabel = new JLabel("Upper Tier (Max Value):");
        JTextField upperTierField = new JTextField();

        JLabel lowerTierLabel = new JLabel("Lower Tier (Min Value):");
        JTextField lowerTierField = new JTextField();
        add(descriptionLabel);
        add(descriptionField);
        add(unitLabel);
        add(unitField);
        add(upperTierLabel);
        add(upperTierField);
        add(lowerTierLabel);
        add(lowerTierField);
    }
}

