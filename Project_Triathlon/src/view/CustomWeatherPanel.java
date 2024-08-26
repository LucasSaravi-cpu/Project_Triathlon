package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CustomWeatherPanel extends JFrame
{
    private JPanel contentPane;
    private JButton addButton;
    public CustomWeatherPanel()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(977, 100, 578, 699);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        addButton = new JButton();
        add(addButton);
    }
}
