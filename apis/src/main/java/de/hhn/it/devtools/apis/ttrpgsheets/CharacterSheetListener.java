package de.hhn.it.devtools.apis.ttrpgsheets;

/**
 * Callback to notify observers about a state change of the character sheet.
 */
public interface CharacterSheetListener {

  /**
   * Informs the listener that the stats of the character sheet changed.
   *
   * @param stat actual stat of the character sheet
   */
  void statChanged(StatDescriptor stat);

  /**
   * Informs the listener that the dice of the character sheet changed.
   *
   * @param dice actual value of the dice
   */
  void diceChanged(DiceDescriptor dice);

  /**
   * Informs the listener that the description of the character sheet changed.
   *
   * @param description actual value of the description
   */
  void descriptionChanged(DescriptionDescriptor description);
}
