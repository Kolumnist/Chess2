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
   * @throws IllegalArgumentException if the listener is a null reference
   */
  void addCallback(CharacterSheetListener listener) throws IllegalArgumentException;

  /**
   * Unwraps (imports) the {@link CharacterDescriptor} into a character.
   *
   * @param character the CharacterDescriptor
   */
  void unwrapCharacter(CharacterDescriptor character);

  /**
   * Wraps (exports) the character into a {@link CharacterDescriptor}.
   *
   * @return the CharacterDescriptor
   */
  CharacterDescriptor wrapCharacter();

  /**
   * increments the given {@link StatType} based upon the {@link OriginType}.
   *
   * @param stat   the Type of Stat to increment
   * @param origin the origin of the change
   * @throws IllegalArgumentException if the given Stat Type can't be incremented with
   *     the given Origin Type or if there are null references
   */
  void incrementStat(StatType stat, OriginType origin) throws IllegalArgumentException;

  /**
   * Increments the given {@link StatType} based upon the {@link OriginType} by a given amount.
   *
   * @param stat   the Type of Stat to increment
   * @param origin the origin of the change
   * @param amount the amount the Stat changes
   * @throws IllegalArgumentException if the given Stat Type can't be incremented with
   *     the given Origin Type or if there are null references
   */
  void incrementStat(StatType stat, OriginType origin, int amount) throws IllegalArgumentException;

  /**
   * Decrements the given {@link StatType} based upon the {@link OriginType}.
   *
   * @param stat   the Type of Stat to decrement
   * @param origin the origin of the change
   * @throws IllegalArgumentException if the given Stat Type can't be incremented with
   *     the given Origin Type or if there are null references
   */
  void decrementStat(StatType stat, OriginType origin) throws IllegalArgumentException;

  /**
   * Decrements the given {@link StatType} based upon the {@link OriginType} by a given amount.
   *
   * @param stat   the Type of Stat to decrement
   * @param origin the origin of the change
   * @param amount the amount the Stat changes
   * @throws IllegalArgumentException if the given Stat Type can't be incremented with
   *     the given Origin Type or if there are null references
   */
  void decrementStat(StatType stat, OriginType origin, int amount) throws IllegalArgumentException;

  /**
   * Returns the total value of the given Stat.
   *
   * @param statType the Type of Stat from which the display value is from
   * @return the value that is supposed to be displayed
   * @throws IllegalArgumentException if StatType is invalid
   */
  int getStatDisplayValue(StatType statType) throws IllegalArgumentException;

  /**
   * Returns descriptor of the stat.
   *
   * @return descriptor of the stat
   */
  StatDescriptor getStatDescriptor(StatType stat);

  /**
   * Changes the description of the given {@link DescriptionType} to the String.
   *
   * @param description the type of description to change
   * @param text        the text the description is changed to
   * @throws IllegalArgumentException if there are null references
   */
  void changeDescription(DescriptionType description, String text) throws IllegalArgumentException;

  /**
   * Returns descriptor of description.
   *
   * @return descriptor of description
   */
  DescriptionDescriptor getDescriptionDescriptor(DescriptionType description);

  /**
   * Rolls the dice to generate a random number according to the current dice.
   *
   * @return The result of the dice throw
   * @throws NullPointerException if the Dice Type hasn't yet been changed
   *      with {@link CharacterSheet#changeDiceType(DiceType)}
   */
  int rollDice() throws NullPointerException;

  /**
   * Changes the dice to the give {@link DiceType}.
   *
   * @param dice the type of dice to be used further
   * @throws IllegalArgumentException if the Dice Type is a null reference
   */
  void changeDiceType(DiceType dice) throws IllegalArgumentException;

  /**
   * Returns the descriptor of the Dice.
   *
   * @return descriptor of the Dice
   */
  DiceDescriptor getDiceDescriptor();

}
