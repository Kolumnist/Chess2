package de.hhn.it.devtools.javafx.battleship;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundHandler {

  String path;
  Clip clip;
  FloatControl fc;

  SoundHandler(String path) {
    this.path = path;
    AudioInputStream inputStream;
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

  void stopMusic() {
    clip.stop();
  }

  void changeVolume(float volume) {
    fc.setValue(volume);
  }
}
