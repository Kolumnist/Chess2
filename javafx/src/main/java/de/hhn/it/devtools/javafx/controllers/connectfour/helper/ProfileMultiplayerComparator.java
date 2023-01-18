package de.hhn.it.devtools.javafx.controllers.connectfour.helper;

import de.hhn.it.devtools.apis.connectfour.Profile;
import java.util.Comparator;

/**
 * Class for comparing profile multiplayer wins.
 */
public class ProfileMultiplayerComparator implements Comparator<Profile> {
  @Override
  public int compare(Profile o1, Profile o2) {
    int x = Integer.compare(o2.getMultiplayerWin(), o1.getMultiplayerWin());
    if (x != 0) {
      return x;
    } else {
      return String.CASE_INSENSITIVE_ORDER.compare(o1.getName(), o2.getName());
    }
  }
}
