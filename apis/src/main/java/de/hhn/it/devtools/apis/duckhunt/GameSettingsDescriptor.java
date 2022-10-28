package de.hhn.it.devtools.apis.duckhunt;

/**
 * Describes the games Settings
 * Describes games sound (ON/OFF), volume.
 */
public class GameSettingsDescriptor {

  private boolean sound;
  private float volume;

  /**
   * Standard Constructor leaving soundON and volume at first Null.
   */
  public GameSettingsDescriptor(){
  }

  public double getVolume() {
    return volume;
  }

  /**
   * Volume setter with parameter range check.
   *
   * @param volume the volume to be set (range: 0.0 - 100.0)
   * @throws IllegalStateException is thrown when parameter volume out of range
   */
  public void setVolume(float volume) throws IllegalStateException {
    if (volume > 100.0 || volume < 0.0) {
      throw new IllegalStateException("Volume not in acceptable range");
    }
    this.volume = volume;
  }

  public boolean isSound() {
    return sound;
  }

  public void setSound(boolean sound) {
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
