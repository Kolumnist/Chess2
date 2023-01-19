package de.hhn.it.devtools.apis.reactiongame;

import java.io.Serializable;

public record HighscoreTupel(String name, long score) implements Comparable, Serializable {

  @Override
  public int compareTo(Object o) {
    return Long.compare(score, ((HighscoreTupel) o).score()) * -1;
  }
}
