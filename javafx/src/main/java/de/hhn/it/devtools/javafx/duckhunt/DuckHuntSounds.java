package de.hhn.it.devtools.javafx.duckhunt;

/**
 * Enum representing different directories of sound-files.
 */
public enum DuckHuntSounds {
  BUTTONCLICK("/sounds/duckhunt/ButtonClick.wav"),
  GUNSHOT("/sounds/duckhunt/GunShot.wav"),
  DRYSHOT("/sounds/duckhunt/DryShot.wav"),
  AMBIANCE("/sounds/duckhunt/Ambiance.wav");

  private String directoryPath;
  DuckHuntSounds(String directoryPath) {
    this.directoryPath = directoryPath;
  }

  public String getDirectoryPath() {
    return directoryPath;
  }
}
