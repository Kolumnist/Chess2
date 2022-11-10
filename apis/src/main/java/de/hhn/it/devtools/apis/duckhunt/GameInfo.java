package de.hhn.it.devtools.apis.duckhunt;

/**
 * This Record saves information about the current games state.
 */
public class GameInfo {
  private int playerScore;
  private int ammo;
  private int round;
  private de.hhn.it.devtools.apis.duckhunt.GameState state;

  public GameInfo(int playerScore, int ammo, int round, de.hhn.it.devtools.apis.duckhunt.GameState state){
    this.playerScore = playerScore;
    this.ammo = ammo;
    this.round = round;
    this.state = state;
  }

  public int getPlayerScore() {
    return playerScore;
  }

  public void setPlayerScore(int playerScore) {
    this.playerScore = playerScore;
  }

  public int getAmmo() {
    return ammo;
  }

  public void setAmmo(int ammo) {
    this.ammo = ammo;
  }

  public int getRound() {
    return round;
  }

  public void setRound(int round) {
    this.round = round;
  }

  public GameState getState() {
    return state;
  }

  public void setState(GameState state) {
    this.state = state;
  }
}
