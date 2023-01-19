package de.hhn.it.devtools.apis.connectfour.helper;

import java.text.DecimalFormat;
import java.util.UUID;

/**
 * This class models the user profile.
 */
public class Profile {
  // The ID of the profile
  private final UUID id;
  private String name;
  private int singleplayerWin = 0;
  private int multiplayerWin = 0;
  private int singleplayerLoose = 0;
  private int multiplayerLoose = 0;
  private int singleplayerDraw = 0;
  private int multiplayerDraw = 0;


  /**
   * Create a new user profile.
   *
   * @param name the name of the profile
   */
  public Profile(String name) throws IllegalArgumentException {
    this.name = name.strip();
    this.id = UUID.randomUUID();
  }

  /**
   * Get the ID of the user profile.
   *
   * @return ID of the profile
   */
  public UUID getId() {
    return id;
  }

  /**
   * Get the name of the user profile.
   *
   * @return name of the profile
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the user profile.
   */
  public void setName(String name) throws IllegalArgumentException {
    this.name = name.strip();
  }

  /**
   * Get the singleplayer wins of the user profile.
   *
   * @return singleplayerWin of the profile
   */
  public int getSingleplayerWin() {
    return singleplayerWin;
  }

  /**
   * Add one singleplayer win to user profile.
   */
  public void addSingleplayerWin() throws IllegalArgumentException {
    this.singleplayerWin = this.singleplayerWin + 1;
  }

  /**
   * Get the singleplayer looses of the user profile.
   *
   * @return singleplayerLoose of the profile
   */
  public int getSingleplayerLoose() {
    return singleplayerLoose;
  }

  /**
   * Add one singleplayer loose to user profile.
   */
  public void addSingleplayerLoose() throws IllegalArgumentException {
    this.singleplayerLoose = this.singleplayerLoose + 1;
  }

  /**
   * Get the singleplayer draws of the user profile.
   *
   * @return singleplayerDraw of the profile
   */
  public int getSingleplayerDraw() {
    return singleplayerDraw;
  }

  /**
   * Add one singleplayer draw to user profile.
   */
  public void addSingleplayerDraw() throws IllegalArgumentException {
    this.singleplayerDraw = this.singleplayerDraw + 1;
  }

  /**
   * Get the multiplayer wins of the user profile.
   *
   * @return multiplayerWin of the profile
   */
  public int getMultiplayerWin() {
    return multiplayerWin;
  }

  /**
   * Add one multiplayer win to user profile.
   */
  public void addMultiplayerWin() throws IllegalArgumentException {
    this.multiplayerWin = this.multiplayerWin + 1;
  }

  /**
   * Get the singleplayer looses of the user profile.
   *
   * @return singleplayerLoose of the profile
   */
  public int getMultiplayerLoose() {
    return multiplayerLoose;
  }

  /**
   * Add one singleplayer loose to user profile.
   */
  public void addMultiplayerLoose() throws IllegalArgumentException {
    this.multiplayerLoose = this.multiplayerLoose + 1;
  }

  /**
   * Get the singleplayer draws of the user profile.
   *
   * @return singleplayerDraw of the profile
   */
  public int getMultiplayerDraw() {
    return multiplayerDraw;
  }

  /**
   * Add one singleplayer draw to user profile.
   */
  public void addMultiplayerDraw() throws IllegalArgumentException {
    this.multiplayerDraw = this.multiplayerDraw + 1;
  }

  /**
   * get singleplayer win percentage of the user profile.
   */
  public String getSingleplayerWinPercentage() {
    int percentage = 0;
    if ((singleplayerWin + singleplayerDraw + singleplayerLoose) != 0) {
      percentage =
          (singleplayerWin / (singleplayerWin + singleplayerDraw + singleplayerLoose)) * 100;
    }
    DecimalFormat df = new DecimalFormat("#.##");
    return df.format(percentage);
  }

  /**
   * get multiplayer win percentage of the user profile.
   */
  public String getMultiplayerWinPercentage() {
    int percentage = 0;
    if ((multiplayerWin + multiplayerDraw + multiplayerLoose) != 0) {
      percentage = (multiplayerWin / (multiplayerWin + multiplayerDraw + multiplayerLoose)) * 100;
    }
    DecimalFormat df = new DecimalFormat("#.##");
    return df.format(percentage);
  }

  @Override
  public String toString() {
    return name;
  }
}

