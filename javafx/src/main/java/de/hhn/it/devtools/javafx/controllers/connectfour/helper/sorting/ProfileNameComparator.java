package de.hhn.it.devtools.javafx.controllers.connectfour.helper.sorting;

import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import java.util.Comparator;

/**
 * Class for comparing profile names.
 */
public class ProfileNameComparator implements Comparator<Profile> {
  @Override
  public int compare(Profile o1, Profile o2) {
    return String.CASE_INSENSITIVE_ORDER.compare(o1.getName(), o2.getName());
  }
}
