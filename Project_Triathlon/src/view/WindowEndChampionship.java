package view;

import Init.RunSimulation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowEndChampionship extends JFrame {

        public WindowEndChampionship(String results, WindowTrophies windowTrophies) {
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

            String processedResults = replaceSpecialCharacters(results);

            JTextArea resultsArea = new JTextArea(processedResults) {
                    @Override
                    protected void paintComponent(Graphics g) {
                        Graphics2D g2d = (Graphics2D) g.create();
                        g2d.setFont(FontCharger.loadCustomFont("/fonts/CuteDino.ttf").deriveFont(14f));
                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                        String[] lines = getText().split("\n");
                        FontMetrics metrics = g2d.getFontMetrics();
                        int lineHeight = metrics.getHeight();

                        int x = getInsets().left;
                        int y = getInsets().top + metrics.getAscent();
                        y += lineHeight;
                        for (String line : lines) {
                            g2d.setColor(Color.BLACK);
                            for (int i = 1; i <= 2; i++) {
                                g2d.drawString(line, x - i, y - i);
                                g2d.drawString(line, x , y - i);
                                g2d.drawString(line, x , y + i);
                                g2d.drawString(line, x - i, y + i);
                                g2d.drawString(line, x + i, y - i);
                                g2d.drawString(line, x + i, y + i);
                                g2d.drawString(line, x + i, y);
                                g2d.drawString(line, x - i, y);
                            }

                            g2d.setColor(Color.WHITE);
                            g2d.drawString(line, x, y);

                            y += lineHeight;
                        }

                        g2d.dispose();
                    }
                };
            resultsArea.setEditable(false);
            resultsArea.setOpaque(false);
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
                    showWindow(false);
                    windowTrophies.setVisible(false);
                    RunSimulation.restartGame();
                }
            });
            restart.setSize(218, 43);
            restart.setContentAreaFilled(false);
            restart.setBorderPainted(false);
            restart.setFocusPainted(false);
            restart.setMargin(new Insets(0, 0, 0, 0));
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.setOpaque(false);
            buttonPanel.add(restart);

            panel.add(buttonPanel, BorderLayout.SOUTH);
        }

        public void showWindow(boolean yes) {
            setVisible(yes);
        }
    private String replaceSpecialCharacters(String input) {
        return input
                .replace("ñ", "n")
                .replace("Ñ", "N")
                .replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("Á", "A")
                .replace("É", "E")
                .replace("Í", "I")
                .replace("Ó", "O")
                .replace("Ú", "U")
                .replace("ã", "a");
    }
}
