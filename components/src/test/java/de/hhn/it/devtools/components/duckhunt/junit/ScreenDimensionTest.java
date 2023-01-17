package de.hhn.it.devtools.components.duckhunt.junit;

import de.hhn.it.devtools.components.duckhunt.ScreenDimension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScreenDimensionTest {
    ScreenDimension screenDimension;
    @BeforeEach
    void setUp() {
        screenDimension = new ScreenDimension(500, 500);
    }

    @Test
    void getWidth() {
        assertEquals(screenDimension.getWidth(), 500);
    }

    @Test
    void setWidth() {
        screenDimension.setWidth(400);
        assertEquals(screenDimension.getWidth(), 400);

    }

    @Test
    void getHeight() {
        assertEquals(screenDimension.getHeight(), 500);
    }

    @Test
    void setHeight() {
        screenDimension.setHeight(400);
        assertEquals(screenDimension.getHeight(), 400);
    }
}