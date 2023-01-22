package de.hhn.it.devtools.javafx.duckhunt;

import de.hhn.it.devtools.apis.duckhunt.DuckHuntService;
import de.hhn.it.devtools.components.duckhunt.DuckHunt;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.net.URL;

public class DuckHuntSoundManager {
  private Media media;
  private MediaPlayer mediaPlayer;
  private MediaPlayer mediaLoopPlayer;
  private File directory;
  private double volume;

  public DuckHuntSoundManager() {
    volume = 20d;
  }

  public void playSound(DuckHuntSounds sound) {
    URL resource = getClass().getResource(sound.getDirectoryPath());
    directory = new File(resource.getFile());
    media = new Media(directory.toURI().toString());
    mediaPlayer = new MediaPlayer(media);
    changeVolume(volume);
    mediaPlayer.play();
  }

  public void playLoopSound(DuckHuntSounds sound) {
    URL resource = getClass().getResource(sound.getDirectoryPath());
    directory = new File(resource.getFile());
    media = new Media(directory.toURI().toString());
    mediaLoopPlayer = new MediaPlayer(media);
    mediaLoopPlayer.setVolume(volume*0.005);
    mediaLoopPlayer.setAutoPlay(true);
    mediaLoopPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    mediaLoopPlayer.play();
  }

  public void changeVolume(double value) {
    volume = value;
    this.mediaPlayer.setVolume(value * 0.01);
  }

  public void stop() {
    mediaPlayer.stop();
    mediaLoopPlayer.stop();
  }
}
