package view;

import Init.RunSimulation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowEndChampionship extends JFrame {

        public WindowEndChampionship(String results) {
            setTitle("End of Championship");
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            JPanel panel = new JPanel(new BorderLayout())  {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    ImageIcon icon = new ImageIcon(getClass().getResource("/Image/endChampionshipBackground.png"));
                    g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            };
            TitleLabel title = new TitleLabel("End of championship", FontCharger.loadCustomFont("/fonts/Dinofiles.ttf").deriveFont(20f));
            title.setHorizontalAlignment(SwingConstants.CENTER);
            title.setBorder(new EmptyBorder(10, 10, 10, 10));
            panel.add(title, BorderLayout.NORTH);

            JTextArea resultsArea = new JTextArea(results);
            resultsArea.setEditable(false);
            resultsArea.setOpaque(false);
            resultsArea.setBackground(new Color(0, 0, 0, 0));
            resultsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
            JScrollPane scrollPane = new JScrollPane(resultsArea);
            panel.add(scrollPane, BorderLayout.CENTER);
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setBorder(null);
            scrollPane.setBackground(new Color(0, 0, 0, 0));
            add(panel);
            ImageIcon newIcon = new ImageIcon(getClass().getResource("/Image/newGameButton.png"));
            Image scaledImage = newIcon.getImage().getScaledInstance(218, 43, Image.SCALE_SMOOTH);
            JButton restart = new JButton(new ImageIcon(scaledImage));
            restart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    RunSimulation.restartGame();
                    showWindow(false);
                }
            });
            restart.setSize(218, 43);
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.setOpaque(false);
            buttonPanel.add(restart);

            panel.add(buttonPanel, BorderLayout.SOUTH);
        }

        public void showWindow(boolean yes) {
            setVisible(true);
        }

}
