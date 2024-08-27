package view;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    public InputPanel() {
        setOpaque(false);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Description:"));
        add(new JTextField());

        add(new JLabel("Measurement Unit:"));
        add(new JTextField());

        add(new JLabel("Upper Tier (Max Value):"));
        add(new JTextField());

        add(new JLabel("Lower Tier (Min Value):"));
        add(new JTextField());
    }
}