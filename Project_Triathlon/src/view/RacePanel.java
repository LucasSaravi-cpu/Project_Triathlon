package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class RacePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel lblNewLabel_1;
    private List<JButton> buttons;
    private int startX;
    private int endX;
    private ImageIcon finishLineIcon;
    private Image scaledFinishLineImage;
    public RacePanel() {
        setLayout(null);

        buttons = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            JButton btn = new JButton("New button");
            btn.setBounds(31 + (i % 2) * 46, 76 + (i / 2) * 46, 50, 21);
            buttons.add(btn);
            add(btn);
        }

        lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setBounds(137, 80, 45, 13);
        add(lblNewLabel_1);

        for (int i = 0; i < 9; i++) {
            JLabel lblNewLabel = new JLabel("New label");
            lblNewLabel.setBounds(137, 126 + i * 46, 45, 13);
            add(lblNewLabel);
        }
        finishLineIcon = new ImageIcon(getClass().getResource("/image/finish_line.png"));
        setPreferredSize(new Dimension(966, 613));
    }

    public JLabel getLblNewLabel_1() {
        return lblNewLabel_1;
    }

    public void setLblNewLabel_1(JLabel lblNewLabel_1) {
        this.lblNewLabel_1 = lblNewLabel_1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        JButton firstbutton = buttons.get(1);
        startX = firstbutton.getX() + firstbutton.getWidth() + 10;
        endX = getWidth() - 80;
        for (int i = 1; i < buttons.size(); i += 2) {
            JButton btn = buttons.get(i);
            int y = btn.getY() + btn.getHeight() / 2;
            g2d.drawLine(startX, y, endX, y);
            if (finishLineIcon != null) {
                g2d.drawImage(finishLineIcon.getImage(), endX - finishLineIcon.getIconWidth(), y - (finishLineIcon.getIconHeight() / 2), null);
            }
        }
        if (finishLineIcon != null) {
            g2d.drawImage(finishLineIcon.getImage(), endX - finishLineIcon.getIconWidth(), (int)buttons.get(buttons.size()/2).getAlignmentY() - (finishLineIcon.getIconHeight() / 2), null);
        }
    }
}
