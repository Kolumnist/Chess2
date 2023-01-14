package de.hhn.it.devtools.components.reactiongame.provider;

/**
 * Player class holds the information about the player.
 */
public class RgcPlayer {

  private int currentLife;
  private String name;


  public RgcPlayer(String name) {
    this.name = name;
    currentLife = 3;
  }


  public int getCurrentLife() {
    return currentLife;
  }

  public void setCurrentLife(int currentLife) {
    this.currentLife = currentLife;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
