package de.hhn.it.devtools.components.connectfour.provider;

import de.hhn.it.devtools.apis.connectfour.ConnectFourListener;
import de.hhn.it.devtools.apis.connectfour.Difficulty;
import de.hhn.it.devtools.apis.connectfour.GameState;
import de.hhn.it.devtools.apis.connectfour.IllegalNameException;
import de.hhn.it.devtools.apis.connectfour.IllegalOperationException;
import de.hhn.it.devtools.apis.connectfour.MatchState;
import de.hhn.it.devtools.apis.connectfour.Mode;
import de.hhn.it.devtools.apis.connectfour.Profile;
import de.hhn.it.devtools.apis.connectfour.ProfileNotFoundException;
import de.hhn.it.devtools.apis.connectfour.ProfileNotSelectedException;
import java.util.ArrayList;
import java.util.List;

public class ConnectFour implements de.hhn.it.devtools.apis.connectfour.ConnectFour {
  private ArrayList<Profile> profiles;
  private GameState gameState;
  private MatchState matchState;
  private Mode mode;

  @Override
  public void addCallback(ConnectFourListener listener) throws IllegalArgumentException {

  }

  @Override
  public void removeCallback(ConnectFourListener listener) throws IllegalArgumentException {

  }

  @Override
  public Profile createProfile(String name) throws IllegalNameException {
    return null;
  }

  @Override
  public void setProfileName(long profileId, String name)
      throws ProfileNotFoundException, IllegalNameException {

  }

  @Override
  public void deleteProfile(long profileId) throws ProfileNotFoundException {

  }

  @Override
  public List<Profile> getProfiles() {
    return null;
  }

  @Override
  public void setMode(Mode mode) {

  }

  @Override
  public void setDifficulty(Difficulty difficulty) {

  }

  @Override
  public void chooseProfileA(Profile a) {

  }

  @Override
  public void chooseProfileB(Profile b) {

  }

  @Override
  public void startGame() throws ProfileNotSelectedException {

  }

  @Override
  public void placeDiscAt(int column) throws IllegalOperationException {

  }

  @Override
  public void quitGame() {

  }
}
