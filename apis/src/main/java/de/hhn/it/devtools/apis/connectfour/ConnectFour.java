package de.hhn.it.devtools.apis.connectfour;

public interface ConnectFour {
  void createProfile(String name);

  void setProfileName(int profileId, String name);

  void deleteProfile(int profileId);

  void help();

  void setMode(Mode mode);

  void setDifficulty();

  void startGame();

  void placeDiscAt(int column);

  void quitGame();



}
