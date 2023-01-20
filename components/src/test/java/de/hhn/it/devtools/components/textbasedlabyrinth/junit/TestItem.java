package de.hhn.it.devtools.components.textbasedlabyrinth.junit;

import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.*;
import de.hhn.it.devtools.apis.textbasedlabyrinth.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestItem {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestMap.class);

    private Item item;

    @BeforeEach
    void setup() {
        item = new Item(0, "Rusty Key", "ExampleText");
    }

    @Test
    @DisplayName("Test if Item will be correctly created")
    public void checkItemCreation() {
        assertEquals(0, item.getItemId());
        assertEquals("Rusty Key", item.getName());
        assertEquals("ExampleText", item.getInfo());
        assertFalse(item.getIsTreasure());
    }
}
