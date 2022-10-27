package de.hhn.it.devtools.apis.duckhunt;

/**
 * Describes the games Settings
 * Describes games sound (ON/OFF), volume.
 */
public class GameSettingsDescriptor {

  private boolean sound;
  private double volume;

  /**
   * Standard Constructor leaving soundON and volume at first Null.
   */
  public GameSettingsDescriptor(){
  }

  public double getVolume() {
    return volume;
  }

  public void setVolume(double volume) {
    this.volume = volume;
  }

  public boolean isSoundOn() {
    return sound;
  }

  public void setSoundOn(boolean sound) {
    this.sound = sound;
  }

  @Override
  public String toString() {
    return "GameSettingsDescriptor{"
         + "Sound=" + sound
         + ", volume=" + volume
         + '}';
  }

}
