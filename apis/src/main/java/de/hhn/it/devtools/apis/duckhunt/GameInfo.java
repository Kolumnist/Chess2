package de.hhn.it.devtools.apis.duckhunt;

/**
 * This Class saves information about the current games state,
 * such as the Players score, the ammo amount a player currently,
 * the round at which the Game is and its current State.
 */
public class GameInfo {
  private int playerScore;
  private int ammo;
  private int round;
  private de.hhn.it.devtools.apis.duckhunt.GameState state;

  /**
   * Constructor to create a GameInfo-Class.
   *
   * @param playerScore the highscore of a Player
   * @param ammo amount of ammo a Player currently has left
   * @param round the round of the Game
   */
  public GameInfo(int playerScore, int ammo, int round) {
    this.playerScore = playerScore;
    this.ammo = ammo;
    this.round = round;
    this.state = GameState.PREPARED;
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

  @Override
  public String toString() {
    return "GameInfo{" +
            "playerScore=" + playerScore +
            ", ammo=" + ammo +
            ", round=" + round +
            ", state=" + state +
            '}';
  }
}
