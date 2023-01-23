package de.hhn.it.devtools.javafx.battleship;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundHandler {

  Clip clip;
  FloatControl fc;
  float currentGlobalVolume;
  float startVolume = 0.5f;
  float range;
  AudioInputStream introInputStream;
  AudioInputStream inputStream;
  String pathIntro = "javafx/src/main/resources/battleship/battleshipStartup.wav";
  String pathLoop = "javafx/src/main/resources/battleship/battleshipLoop.wav";


  SoundHandler() {

    try {
      introInputStream = AudioSystem.getAudioInputStream(new File(pathIntro));
      inputStream = AudioSystem.getAudioInputStream(new File(pathLoop));
      clip = AudioSystem.getClip();
      clip.open(introInputStream);

      fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      range = fc.getMaximum() - fc.getMinimum();
      changeVolume(startVolume);

      clip.start();
      clip.addLineListener(event -> {
        if (event.getType() == Type.STOP) {
          try {
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            changeVolume(currentGlobalVolume);
            runMusic();

          } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
          }
        }
      });

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
    fc.setValue((range * volume) + fc.getMinimum());
    currentGlobalVolume = volume;
  }
}
