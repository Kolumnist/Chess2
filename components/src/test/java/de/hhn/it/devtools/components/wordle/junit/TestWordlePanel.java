package de.hhn.it.devtools.components.wordle.junit;

import de.hhn.it.devtools.components.wordle.provider.WordlePanel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWordlePanel {

    @Test
    void shouldReturnPanelLetter_d() {
        WordlePanel wordlePanelD = new WordlePanel('d', 0);
        assertEquals('d', wordlePanelD.getLetter());
    }
}
