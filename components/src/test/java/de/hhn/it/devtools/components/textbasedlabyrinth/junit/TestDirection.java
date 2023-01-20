package de.hhn.it.devtools.components.textbasedlabyrinth.junit;

import de.hhn.it.devtools.components.textBasedLabyrinth.textbasedlabyrinth.*;
import de.hhn.it.devtools.apis.textbasedlabyrinth.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDirection {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(TestMap.class);

    @Test
    @DisplayName("Test if correct opposite direction is created")
    public void checkOppositeDirection() {
        Direction direction = Direction.NORTH;
        assertEquals(Direction.SOUTH, direction.getOpposite());
    }
}
