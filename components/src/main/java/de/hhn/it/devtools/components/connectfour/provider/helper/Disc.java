package de.hhn.it.devtools.components.connectfour.provider.helper;

import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.components.connectfour.provider.helper.Color;

/**
 * This class models a disc.
 */
public final record Disc(Profile owner, Color color) {
}
