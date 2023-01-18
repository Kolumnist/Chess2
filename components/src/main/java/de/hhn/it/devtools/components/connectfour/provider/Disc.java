package de.hhn.it.devtools.components.connectfour.provider;

import de.hhn.it.devtools.apis.connectfour.Profile;
import de.hhn.it.devtools.components.connectfour.provider.Color;

/**
 * This class models a disc.
 */
public final record Disc(Profile owner, Color color) {
}
