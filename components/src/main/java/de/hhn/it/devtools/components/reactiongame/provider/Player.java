package de.hhn.it.devtools.components.reactiongame.provider;

public class Player {

  private int currentLife;
  private String name;


  public Player(String name) {
    this.name = name;
    currentLife = 0;
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
