package view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {

    private static Clip music;
    public static void music(String url) {

        try {
            // Load music
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(MusicPlayer.class.getResourceAsStream(url));
            music = AudioSystem.getClip();
            music.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void playMusic() {
        if (music != null && !music.isRunning()) {
            // Play music in a continuous loop
            music.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }



    public static Clip getMusic() {
        return music;
    }
    public static void close(){
        music.close();
    }

}
