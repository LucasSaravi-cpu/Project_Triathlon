package view;

import java.awt.*;
import javax.swing.*;

public class TitleLabel extends JLabel {
    private static final long serialVersionUID = 1L;

    public TitleLabel(String text) {
        super(text);
        setFont(Scoreboard.loadCustomFont("/fonts/Dinofiles.ttf").deriveFont(60f));
        setHorizontalAlignment(SwingConstants.CENTER);
        setForeground(Color.WHITE); // Texto en blanco
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Habilitar antialiasing para una mejor calidad de texto
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Obtener posición del texto
        int x = getWidth() / 2 - g2d.getFontMetrics().stringWidth(getText()) / 2; // Centrar texto horizontalmente
        int y = getHeight() / 2 + getFontMetrics(getFont()).getAscent() / 4; // Centrar verticalmente

        // Dibujar la sombra difuminada
        g2d.setColor(Color.BLACK);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); // Sombra con mayor transparencia
        for (int i = 1; i <= 8; i++) { // Mayor rango para una sombra más difuminada
            g2d.drawString(getText(), x + i, y + i);
        }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); // Restablecer opacidad completa

        // Dibujar el contorno blanco
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(4f)); // Contorno más fino pero aún visible
        g2d.drawString(getText(), x, y);

        // Dibujar el texto en blanco
        g2d.setColor(getForeground());
        g2d.drawString(getText(), x, y);

        g2d.dispose();
    }
}
