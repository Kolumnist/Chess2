package de.hhn.it.devtools.components.wordle.provider;

import java.security.SecureRandom;
import java.util.List;

public class WordleSolutionSelector {

  private static SecureRandom csprng = new SecureRandom();

  public static final List<String> possibleWordleSolutions = List.of("agree", "apply","brain", "beach","cheap",
      "cyber","daily", "draft", "email", "extra","facet", "fresh","yacht", "yeast", "wagon", "whack", "vague", "value",
      "udder", "uncle", "table", "taken", "stair", "spray", "react", "ramen", "quack", "qualm", "paddy", "panel",
      "oaken", "obese", "nanny", "nasty", "magma", "major", "leach", "legal", "kebab", "kitty", "jaunt", "jelly",
      "ideal", "idiot", "habit", "handy", "gamer", "goner","zebra", "zesty");

  public static int getSolutionListLength() {
    return possibleWordleSolutions.size();
  }


  public static String selectWordle() {
    int randomInt = csprng.nextInt(WordleSolutionSelector.getSolutionListLength());
    return possibleWordleSolutions.get(randomInt);
  }
}

