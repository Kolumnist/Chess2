package de.hhn.it.devtools.apis.ttrpgsheets;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

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
     * increments the given {@link StatType} based upon the {@link Origin}
     *
     * @param type the Type of Stat to increment
     * @param origin the origin of the change
     */
    void incrementStat(StatType type, Origin origin);

    /**
     * Increments the given {@link StatType} based upon the {@link Origin} by a given amount
     *
     * @param type the Type of Stat to increment
     * @param origin the origin of the change
     * @param amount the amount the Stat changes
     */
    void incrementStat(StatType type, Origin origin, int amount);

    /**
     * Decrements the given {@link StatType} based upon the {@link Origin}
     *
     * @param type the Type of Stat to decrement
     * @param origin the origin of the change
     */
    void decrementStat(StatType type, Origin origin);

    /**
     * Decrements the given {@link StatType} based upon the {@link Origin} by a given amount
     *
     * @param type the Type of Stat to decrement
     * @param origin the origin of the change
     * @param amount the amount the Stat changes
     */
    void decrementStat(StatType type, Origin origin, int amount);

    /**
     * Changes the description of the given {@link DescriptionType} to the String
     *
     * @param type the type of description to change
     * @param text the text the description is changed to
     */
    void changeDescription(DescriptionType type, String text);

    /**
     * Rolls the dice to generate a random number according to the current dice
     */
    void rollDice();

    /**
     * Changes the dice to the give {@link DiceType}
     *
     * @param type the type of dice to be used further
     */
    void changeDiceType(DiceType type);

    /**
     * Changes the dice to one of the given size
     * It is recommended to use {@link DiceType} instead for better readability
     *
     * @param size the size of dice to be used further
     */
    void changeDiceType(int size);

}
