package de.hhn.it.devtools.components.ttrpgsheets.junit;

import static org.junit.jupiter.api.Assertions.assertThrows;

import de.hhn.it.devtools.components.ttrpgsheets.Stat;
import org.junit.jupiter.api.Test;

class TestStatBadCases {
  private static final org.slf4j.Logger logger
          = org.slf4j.LoggerFactory.getLogger(TestStatGoodCases.class);

  @Test
  void constructorTest() {
    logger.info("constructorTest() is called");
    assertThrows(IllegalArgumentException.class, () -> new Stat(null));
  }
}