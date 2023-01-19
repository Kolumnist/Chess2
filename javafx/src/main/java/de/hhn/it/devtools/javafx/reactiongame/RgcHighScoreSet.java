package de.hhn.it.devtools.javafx.reactiongame;

import java.io.Serializable;

public record RgcHighScoreSet(String name, long score) implements Comparable, Serializable {


  @Override
  public int compareTo(Object o) {
    return Long.compare(score, ((RgcHighScoreSet) o).score()) * -1;
  }
}
