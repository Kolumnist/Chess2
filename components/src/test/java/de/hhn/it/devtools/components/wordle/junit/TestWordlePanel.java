package de.hhn.it.devtools.components.wordle.junit;

import de.hhn.it.devtools.components.wordle.provider.WordlePanel;
import org.junit.jupiter.api.Test;

import static de.hhn.it.devtools.apis.wordle.State.INITIAL;
import static de.hhn.it.devtools.apis.wordle.State.PARTIALLY_CORRECT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWordlePanel {

    @Test
    void shouldDeleteLetterFromPanel() {
        WordlePanel wordlePanelA = new WordlePanel('A');
        wordlePanelA.deleteLetter();
        assertEquals(' ', wordlePanelA.getLetter());
    }

    @Test
    void shouldSetLetterInPanel() {
        WordlePanel wordlePanelR = new WordlePanel('r');
        wordlePanelR.setLetter('t');
        assertEquals('t', wordlePanelR.getLetter());
    }

    @Test
    void shouldSetIdofWordlePanel() {
        WordlePanel wordlePanelS = new WordlePanel('s');
        wordlePanelS.setId(5);
        assertEquals(5,wordlePanelS.getId());
    }

    @Test
    void shouldReturnStateOfPanel() {
        WordlePanel wordlePanelI = new WordlePanel('i');
        assertEquals(INITIAL, wordlePanelI.getState());
    }

    @Test
    void shouldSetStateOfPanel() {
        WordlePanel wordlePanelP = new WordlePanel('p');
        wordlePanelP.setState(PARTIALLY_CORRECT);
        assertEquals(PARTIALLY_CORRECT, wordlePanelP.getState());
    }




}
