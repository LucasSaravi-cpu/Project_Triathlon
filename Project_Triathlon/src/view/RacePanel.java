package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class RacePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel lblNewLabel_1;
    private List<JButton> buttons;
    private List<JLabel> labels;
    private int startX;
    private int endX;
    private ImageIcon finishLineIcon;
    private Image scaledFinishLineImage;
    public List<JButton> getButtons(){
    	return buttons;
    }
    public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public RacePanel() {
        setLayout(null);

        buttons = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            JButton btn = new JButton("New button");
            btn.setBounds(31 + (i % 2) * 46, 76 + (i / 2) * 46, 50, 21);
            buttons.add(btn);
            add(btn);
        }
        labels=new ArrayList<>();
        for (int i = 0, j = 0; i < 10; i++, j+=2) { // Suponiendo 10 corredores
            JLabel lblNewLabel = new JLabel("Corredor " + (i + 1));
            lblNewLabel.setBounds(31, buttons.get(j).getY(), 100, 20); // Start position X set to 31
            labels.add(lblNewLabel);
            add(lblNewLabel);
        }
        finishLineIcon = new ImageIcon(getClass().getResource("/image/finish_line.png"));
        setPreferredSize(new Dimension(966, 613));
    }

    public void updateLabelPosition(int index, int newPositionX) {
        if (index >= 0 && index < labels.size()) {
            JLabel label = labels.get(index);
            label.setLocation(newPositionX, label.getY());
        }
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