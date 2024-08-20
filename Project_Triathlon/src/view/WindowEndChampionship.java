package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class WindowEndChampionship extends JFrame {

        public WindowEndChampionship(String results) {
            setTitle("End of Championship");
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            JPanel panel = new JPanel(new BorderLayout());
            TitleLabel title = new TitleLabel("End of championship", Scoreboard.loadCustomFont("/fonts/Dinofiles.ttf").deriveFont(20f));
            title.setHorizontalAlignment(SwingConstants.CENTER);
            title.setBorder(new EmptyBorder(10, 10, 10, 10));
            panel.add(title, BorderLayout.NORTH);

            JTextArea resultsArea = new JTextArea(results);
            resultsArea.setEditable(false);
            resultsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
            JScrollPane scrollPane = new JScrollPane(resultsArea);
            panel.add(scrollPane, BorderLayout.CENTER);
            add(panel);
        }

        public void showWindow() {
            setVisible(true);
        }

}
