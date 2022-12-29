package de.hhn.it.devtools.components.wordle.junit;

import de.hhn.it.devtools.components.wordle.provider.WordlePanel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWordlePanel {

    @Test
    void shouldReturnPanelLetter_d() {
        WordlePanel wordlePanelD = new WordlePanel('d');
        assertEquals('d', wordlePanelD.getLetter());
    }

    @Test
    void shouldReturnIdOfWordlePanel() {
        WordlePanel wordlePanelO = new WordlePanel('o');
        assertEquals(0, wordlePanelO.getId());
    }

    @Test
    void shouldDeleteLetterFromPanel() {
        WordlePanel wordflePanelA = new WordlePanel('A');
        wordflePanelA.deleteLetter();
        assertEquals(' ', wordflePanelA.getLetter());
    }



}
