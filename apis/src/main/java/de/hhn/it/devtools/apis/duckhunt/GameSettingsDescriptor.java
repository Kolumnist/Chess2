package de.hhn.it.devtools.apis.duckhunt;

/**
 * Describes a games sound (ON/OFF), volume.
 */
public class GameSettingsDescriptor {

  private boolean soundOn;
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
    return soundOn;
  }

  public void setSoundOn(boolean soundOn) {
    this.soundOn = soundOn;
  }

  @Override
  public String toString() {
    return "GameSettingsDescriptor{"
         + "SoundOn=" + soundOn
         + ", volume=" + volume
         + '}';
  }

}
