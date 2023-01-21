package de.hhn.it.devtools.javafx.battleship;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundHandler {

    String path;
    Clip clip;
    FloatControl fc;

    SoundHandler(String path) {
        this.path = path;
        AudioInputStream inputStream = null;
        try {
            inputStream = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            fc.setValue(-15.0f);
        } catch (UnsupportedAudioFileException | IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }


    }

    void runMusic() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    void stopMusic(){
        clip.stop();
    }

    void changeVolume(float volume){
        fc.setValue(volume);
    }
}
