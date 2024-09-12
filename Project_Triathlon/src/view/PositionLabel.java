package view;

import javax.swing.*;
import java.awt.*;

public class PositionLabel extends JLabel {
    private static final long serialVersionUID = 1L;
    private String customText = "";
    public PositionLabel(){
       super();
    }
    public void setCustomText(String text) {
        this.customText = text;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        applyCustomTextStyle(g, customText);
    }

    private void applyCustomTextStyle(Graphics g, String text) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setFont(FontCharger.loadCustomFont("/fonts/CuteDino.ttf").deriveFont(10f));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        FontMetrics metrics = g2d.getFontMetrics();
        int x = getInsets().left;
        int y = getInsets().top + metrics.getAscent();

        g2d.setColor(Color.BLACK);
            g2d.drawString(text, x - 1, y - 1);
            g2d.drawString(text, x, y - 1);
            g2d.drawString(text, x, y + 1);
            g2d.drawString(text, x - 1, y + 1);
            g2d.drawString(text, x + 1, y - 1);
            g2d.drawString(text, x + 1, y + 1);
            g2d.drawString(text, x + 1, y);
            g2d.drawString(text, x - 1, y);


        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x, y);

        g2d.dispose();
    }
}