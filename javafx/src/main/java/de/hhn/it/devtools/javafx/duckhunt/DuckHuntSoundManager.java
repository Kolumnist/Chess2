package de.hhn.it.devtools.javafx.duckhunt;

import java.io.File;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Provides the sound for the game.
 */
public class DuckHuntSoundManager {
  private Media media;
  private MediaPlayer mediaPlayer;
  private MediaPlayer mediaLoopPlayer;
  private File directory;
  private double volume;

  public DuckHuntSoundManager() {
    volume = 20d;
  }

  /**
   * Plays the given sound once.
   *
   * @param sound to be played
   */
  public void playSound(DuckHuntSounds sound) {
    URL resource = getClass().getResource(sound.getDirectoryPath());
    directory = new File(resource.getFile());
    media = new Media(directory.toURI().toString());
    mediaPlayer = new MediaPlayer(media);
    changeVolume(volume);
    mediaPlayer.play();
  }

  /**
   * Plays the given sound in endless loop.
   *
   * @param sound to be played
   */
  public void playLoopSound(DuckHuntSounds sound) {
    URL resource = getClass().getResource(sound.getDirectoryPath());
    directory = new File(resource.getFile());
    media = new Media(directory.toURI().toString());
    mediaLoopPlayer = new MediaPlayer(media);
    mediaLoopPlayer.setVolume(volume * 0.005);
    mediaLoopPlayer.setAutoPlay(true);
    mediaLoopPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    mediaLoopPlayer.play();
  }

  public void changeVolume(double value) {
    volume = value;
    this.mediaPlayer.setVolume(value * 0.01);
  }

  /**
   * Stops all sound media players.
   */
  public void stop() {
    mediaPlayer.pause();
    mediaLoopPlayer.pause();
    mediaPlayer.stop();
    mediaLoopPlayer.stop();
  }
}
