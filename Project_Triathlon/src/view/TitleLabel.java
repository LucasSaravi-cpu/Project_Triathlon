package view;

import java.awt.*;
import javax.swing.*;

public class TitleLabel extends JLabel {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
    private static final long serialVersionUID = 1L;
    //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
    public TitleLabel(String text, Font font) {
        super(text);
        setFont(font);
        setHorizontalAlignment(SwingConstants.CENTER);
        setForeground(Color.WHITE);
    }

  //------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        int x = getWidth() / 2 - g2d.getFontMetrics().stringWidth(getText()) / 2;
        int y = getHeight() / 2 + getFontMetrics(getFont()).getAscent() / 4;

        g2d.setColor(Color.BLACK);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        for (int i = 1; i <= 8; i++) {
            g2d.drawString(getText(), x + i, y + i);
        }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(4f));
        g2d.drawString(getText(), x, y);
        g2d.setColor(getForeground());
        g2d.drawString(getText(), x, y);

        g2d.dispose();
    }
}
