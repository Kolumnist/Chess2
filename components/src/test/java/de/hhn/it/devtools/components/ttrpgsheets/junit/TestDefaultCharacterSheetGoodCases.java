package de.hhn.it.devtools.components.ttrpgsheets.junit;

import de.hhn.it.devtools.components.ttrpgsheets.DefaultCharacterSheet;
import org.junit.jupiter.api.Test;

class TestDefaultCharacterSheetGoodCases {
  private static final org.slf4j.Logger logger
          = org.slf4j.LoggerFactory.getLogger(TestStatGoodCases.class);

  DefaultCharacterSheet characterSheet = null; //TODO - Add Constructor

  @Test
  void addCallbackTest() {
    logger.info("addCallbackTest() is called");
  }

  @Test
  void unwrapCharacterTest() {
    logger.info("unwrapCharacterTest() is called");
  }

  @Test
  void wrapCharacterTest() {
    logger.info("wrapCharacterTest() is called");
  }

  @Test
  void incrementStatTest() {
    logger.info("incrementStatTest() is called");
  }

  @Test
  void decrementStatTest() {
    logger.info("decrementStatTest() is called");
  }

  @Test
  void testDecrementStatTest() {
    logger.info("testDecrementStatTest() is called");
  }

  @Test
  void getStatDisplayValueTest() {
    logger.info("getStatDisplayValueTest() is called");
  }

  @Test
  void getStatDescriptorTest() {
    logger.info("getStatDescriptorTest() is called");
  }

  @Test
  void changeDescriptionTest() {
    logger.info("changeDescriptionTest() is called");
  }

  @Test
  void getDescriptionDescriptorTest() {
    logger.info("getDescriptionDescriptorTest() is called");
  }

  @Test
  void rollDiceTest() {
    logger.info("rollDiceTest() is called");
  }

  @Test
  void changeDiceTypeTest() {
    logger.info("changeDiceTypeTest() is called");
  }

  @Test
  void getDiceDescriptorTest() {
    logger.info("getDiceDescriptorTest() is called");
  }

  @Test
  void getListenerTest() {
    logger.info("getListenerTest() is called");
  }

  @Test
  void setListenerTest() {
    logger.info("setListenerTest() is called");
  }

  @Test
  void getDescriptionsTest() {
    logger.info("getDescriptionsTest() is called");
  }

  @Test
  void setDescriptionsTest() {
    logger.info("setDescriptionsTest() is called");
  }

  @Test
  void getStatsTest() {
    logger.info("getStatsTest() is called");
  }

  @Test
  void setStatsTest() {
    logger.info("setStatsTest() is called");
  }

  @Test
  void getDiceTest() {
    logger.info("getDiceTest() is called");
  }

  @Test
  void setDiceTest() {
    logger.info("setDiceTest() is called");
  }

  @Test
  void toStringTest() {
    logger.info("toStringTest() is called");
  }
}