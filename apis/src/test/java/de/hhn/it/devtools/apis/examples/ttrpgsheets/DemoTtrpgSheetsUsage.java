package de.hhn.it.devtools.apis.examples.ttrpgsheets;

import de.hhn.it.devtools.apis.ttrpgsheets.CharacterSheet;
import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the Demo Usage of a Ttrpg Character Sheet.
 * It is not runnable because there is no implementation yet.
 */
public class DemoTtrpgSheetsUsage {
    private static final Logger logger = LoggerFactory.getLogger(DemoTtrpgSheetsUsage.class);

    public static void main(String[] args) {
        CharacterSheet characterSheet = null;

        // Starting to change the description of the character
        characterSheet.changeDescription(DescriptionType.PLAYERNAME, "Herbert");
    }
}
