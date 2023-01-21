package de.hhn.it.devtools.javafx.controllers.ttrpgsheets;

import de.hhn.it.devtools.apis.ttrpgsheets.CharacterSheet;
import de.hhn.it.devtools.apis.ttrpgsheets.CharacterSheetListener;

/**
 * Abstract controller class for a view.
 */
public abstract class CharacterViewController implements CharacterSheetListener {

  protected final CharacterSheet characterSheet;

  public CharacterViewController(CharacterSheet characterSheet) {
    this.characterSheet = characterSheet;
  }
}
