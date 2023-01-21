package de.hhn.it.devtools.javafx.controllers.ttrpgsheets;

import de.hhn.it.devtools.apis.ttrpgsheets.CharacterSheet;
import de.hhn.it.devtools.apis.ttrpgsheets.CharacterSheetListener;

public abstract class CharacterViewController implements CharacterSheetListener {

  protected CharacterSheet characterSheet;

  public CharacterViewController(CharacterSheet characterSheet) {
    this.characterSheet = characterSheet;
  }
}
