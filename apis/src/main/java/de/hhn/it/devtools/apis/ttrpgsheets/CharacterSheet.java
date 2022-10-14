package de.hhn.it.devtools.apis.ttrpgsheets;

public interface CharacterSheet {

    /**
     * Adds a listener to get updates on the state of the character sheet.
     *
     * @param listener object implementing the listener interface
     */
    void addCallback(CharacterSheetListener listener);
}
