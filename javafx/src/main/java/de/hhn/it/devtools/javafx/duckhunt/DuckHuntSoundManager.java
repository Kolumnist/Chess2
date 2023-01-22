package de.hhn.it.devtools.javafx.duckhunt;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.net.URL;

public class DuckHuntSoundManager {
  private Media media;
  private MediaPlayer mediaPlayer;
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

  public void changeVolume(double value) {
    volume = value;
    this.mediaPlayer.setVolume(value * 0.01);
  }
}
