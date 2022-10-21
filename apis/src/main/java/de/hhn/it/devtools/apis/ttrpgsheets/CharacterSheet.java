package de.hhn.it.devtools.apis.ttrpgsheets;

/**
 * This interface controls the Character Sheet module.
 * It allows to change Stats or alter information about the Character.
 */
public interface CharacterSheet {

  /**
   * Adds a listener to get updates on the state of the character sheet.
   *
   * @param listener object implementing the listener interface
   */
  void addCallback(CharacterSheetListener listener);

  /**
   * increments the given {@link StatType} based upon the {@link OriginType}.
   *
   * @param stat   the Type of Stat to increment
   * @param origin the origin of the change
   */
  void incrementStat(StatType stat, OriginType origin);

  /**
   * Increments the given {@link StatType} based upon the {@link OriginType} by a given amount.
   *
   * @param stat   the Type of Stat to increment
   * @param origin the origin of the change
   * @param amount the amount the Stat changes
   */
  void incrementStat(StatType stat, OriginType origin, int amount);

  /**
   * Decrements the given {@link StatType} based upon the {@link OriginType}.
   *
   * @param stat   the Type of Stat to decrement
   * @param origin the origin of the change
   */
  void decrementStat(StatType stat, OriginType origin);

  /**
   * Decrements the given {@link StatType} based upon the {@link OriginType} by a given amount.
   *
   * @param stat   the Type of Stat to decrement
   * @param origin the origin of the change
   * @param amount the amount the Stat changes
   */
  void decrementStat(StatType stat, OriginType origin, int amount);

  /**
   * Returns descriptor of the stat.
   *
   * @return descriptor of the stat
   */
  StatDescriptor getStat(StatType stat);

  /**
   * Changes the description of the given {@link DescriptionType} to the String.
   *
   * @param description the type of description to change
   * @param text        the text the description is changed to
   */
  void changeDescription(DescriptionType description, String text);

  /**
   * Returns descriptor of description.
   *
   * @return descriptor of description
   */
  DescriptionDescriptor getDescription(DescriptionType description);

  /**
   * Rolls the dice to generate a random number according to the current dice.
   */
  void rollDice();

  /**
   * Changes the dice to the give {@link DiceType}.
   *
   * @param dice the type of dice to be used further
   */
  void changeDiceType(DiceType dice);

  /**
   * Returns the descriptor of the Dice.
   *
   * @return descriptor of the Dice
   */
  DiceDescriptor getDice();

}
