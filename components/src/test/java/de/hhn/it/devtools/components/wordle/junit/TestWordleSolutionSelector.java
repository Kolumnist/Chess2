package de.hhn.it.devtools.components.wordle.junit;

import de.hhn.it.devtools.components.wordle.provider.WordleSolutionSelector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestWordleSolutionSelector {

    @Test
    void shouldReturnPossibleSolutionFromList() {
        String solution = WordleSolutionSelector.selectWordle();
        assertTrue(WordleSolutionSelector.possibleWordleSolutions.contains(solution));
    }
}
