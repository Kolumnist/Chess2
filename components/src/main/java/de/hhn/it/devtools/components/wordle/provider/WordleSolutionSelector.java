package de.hhn.it.devtools.components.wordle.provider;

import java.util.List;

public class WordleSolutionSelector {

  private static final List<String> possibleWordleSolutions = List.of("agree", "apply","brain", "beach","cheap",
      "cyber","daily", "draft", "email", "extra","facet", "fresh","yacht", "yeast", "wagon", "whack", "vague", "value",
      "udder", "uncle", "table", "taken", "stair", "spray", "react", "ramen", "quack", "qualm", "paddy", "panel",
      "oaken", "obese", "nanny", "nasty", "magma", "major", "leach", "legal", "kebab", "kitty", "jaunt", "jelly",
      "ideal", "idiot", "habit", "handy", "gamer", "goner","zebra", "zesty");

  public static String accessListAtIndex(int index) {
    return possibleWordleSolutions.get(index);
  }

  public static int getSolutionListLength() {
    return possibleWordleSolutions.size();
  }
}

