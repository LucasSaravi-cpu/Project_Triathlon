package view;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontCharger {
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
    public static Font loadCustomFont(String path) {
        try {
            InputStream is = Scoreboard.class.getResourceAsStream(path);
            if (is == null) {
                throw new IOException("Font not found: " + path);
            }

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            return customFont.deriveFont(Font.BOLD, 16f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}
