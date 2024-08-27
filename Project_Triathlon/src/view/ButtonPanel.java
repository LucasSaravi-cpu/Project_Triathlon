package view;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private JButton confirmButton;
    private JButton cancelButton;
    private JButton exitButton;

    public ButtonPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setOpaque(false);
        confirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");
        exitButton = new JButton("Exit");

        add(confirmButton);
        add(cancelButton);
        add(exitButton);
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}