package de.hhn.it.devtools.javafx.controllers.ttrpgsheets;

import de.hhn.it.devtools.apis.ttrpgsheets.CharacterSheet;
import de.hhn.it.devtools.apis.ttrpgsheets.CharacterSheetListener;
import de.hhn.it.devtools.javafx.controllers.CharacterSheetServiceController;

public abstract class CharacterViewController implements CharacterSheetListener {

  protected CharacterSheet characterSheet = CharacterSheetServiceController.getCharacterSheet();
}
