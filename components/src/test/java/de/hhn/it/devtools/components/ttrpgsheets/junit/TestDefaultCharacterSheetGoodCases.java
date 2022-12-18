package de.hhn.it.devtools.components.ttrpgsheets.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.hhn.it.devtools.apis.ttrpgsheets.OriginType;
import de.hhn.it.devtools.apis.ttrpgsheets.StatDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.StatType;
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
    for (StatType statType : StatType.values()) {
      for (OriginType origin : OriginType.values()) {
        StatDescriptor stat = characterSheet.getStatDescriptor(statType);
        int prevAbilityPoints = stat.getAbilityPointsUsed();
        int prevMiscellaneous = stat.getMiscellaneous();

        if (origin == OriginType.LEVEL_POINT && !stat.isLevelStat()) {
          assertThrows(IllegalArgumentException.class,
                  () -> characterSheet.incrementStat(statType, origin));
        }
        characterSheet.incrementStat(statType, origin);
        if (origin == OriginType.LEVEL_POINT) {
          assertEquals(prevAbilityPoints + 1,
                  characterSheet.getStatDescriptor(statType).getAbilityPointsUsed());
        } else {
          assertEquals(prevMiscellaneous + 1,
                  characterSheet.getStatDescriptor(statType).getMiscellaneous());
        }
      }
    }
  }

  @Test
  void decrementStatTest() {
    logger.info("decrementStatTest() is called");
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
  void toStringTest() {
    logger.info("toStringTest() is called");
  }
}