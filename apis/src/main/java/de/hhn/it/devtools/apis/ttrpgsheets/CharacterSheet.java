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
     * @param type the Type of Stat to increment
     * @param origin the origin of the change
     */
    void incrementStat(StatType type, Origin origin);

    void incrementStat(StatType type, Origin origin, int amount);

    void decrementStat(StatType type, Origin origin);

    void decrementStat(StatType type, Origin origin, int amount);

    void changeDescription(DescriptionType type, String text);

    void rollDice();

    void changeDiceType(DiceType type);

    void changeDiceType(int size);

}
