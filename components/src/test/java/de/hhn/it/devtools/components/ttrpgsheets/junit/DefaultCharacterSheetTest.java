package de.hhn.it.devtools.components.ttrpgsheets.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.hhn.it.devtools.apis.ttrpgsheets.CharacterDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.CharacterSheetListener;
import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionType;
import de.hhn.it.devtools.apis.ttrpgsheets.DiceDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DiceType;
import de.hhn.it.devtools.apis.ttrpgsheets.OriginType;
import de.hhn.it.devtools.apis.ttrpgsheets.StatDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.StatType;
import de.hhn.it.devtools.components.ttrpgsheets.DefaultCharacterSheet;
import de.hhn.it.devtools.components.ttrpgsheets.Stat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DefaultCharacterSheetTest {
  private static final org.slf4j.Logger logger
          = org.slf4j.LoggerFactory.getLogger(DefaultCharacterSheetTest.class);

  DefaultCharacterSheet characterSheet = null;

  @BeforeEach
  void setupObjects() {
    logger.info("setupObjects() is called");
    characterSheet = new DefaultCharacterSheet(new CharacterSheetListener() {
      @Override
      public void statChanged(StatDescriptor stat) {

      }

      @Override
      public void diceChanged(DiceDescriptor dice) {

      }

      @Override
      public void descriptionChanged(DescriptionDescriptor description) {

      }
    }, setupCharacterDescriptor());
  }

  CharacterDescriptor setupCharacterDescriptor() {
    logger.info("setupCharacterDescriptor() is called");
    return new CharacterDescriptor(setupDescriptions(), setupStats(),
            new DiceDescriptor(DiceType.D6, 1));
  }

  StatDescriptor[] setupStats() {
    logger.info("setupStats() is called");
    StatDescriptor[] statDescriptors = new StatDescriptor[StatType.values().length];
    for (int i = 0; i < StatType.values().length; i++) {
      switch (StatType.values()[i]) {
        case MAX_HEALTH -> statDescriptors[i]
                = new StatDescriptor(StatType.values()[i], 30, 5, 0, 0, true);
        case HEALTH -> statDescriptors[i]
                = new StatDescriptor(StatType.values()[i], 30, 0, 0, -2, false);
        case LEVEL -> statDescriptors[i]
                = new StatDescriptor(StatType.values()[i], 1, 0, 0, 0, false);
        case STRENGTH -> statDescriptors[i]
                = new StatDescriptor(StatType.values()[i], 0, 1, 2, 6, true);
        default -> statDescriptors[i] = new StatDescriptor(StatType.values()[i], 0, 1, 0, 0, true);
      }
    }
    return statDescriptors;
  }

  DescriptionDescriptor[] setupDescriptions() {
    logger.info("setupDescriptions() is called");
    DescriptionDescriptor[] descriptionDescriptors = new DescriptionDescriptor[
            DescriptionType.values().length];
    for (int i = 0; i < DescriptionType.values().length; i++) {
      switch (DescriptionType.values()[i]) {
        case CHARACTER_CLASS -> descriptionDescriptors[i] = new DescriptionDescriptor(
                DescriptionType.values()[i], "Warrior");
        case CHARACTER_NAME -> descriptionDescriptors[i] = new DescriptionDescriptor(
                DescriptionType.values()[i], "Sylas Thatcher");
        case PLAYER_NAME -> descriptionDescriptors[i] = new DescriptionDescriptor(
                DescriptionType.values()[i], "Peter");
        case SKIN_COLOR -> descriptionDescriptors[i] = new DescriptionDescriptor(
                DescriptionType.values()[i], "White");
        case HAIR_COLOR -> descriptionDescriptors[i] = new DescriptionDescriptor(
                DescriptionType.values()[i], "Pink");
        case EYE_COLOR -> descriptionDescriptors[i] = new DescriptionDescriptor(
                DescriptionType.values()[i], "Blue");
        case NICKNAME -> descriptionDescriptors[i] = new DescriptionDescriptor(
                DescriptionType.values()[i], "Sy");
        case WEIGHT -> descriptionDescriptors[i] = new DescriptionDescriptor(
                DescriptionType.values()[i], "72 kg");
        case HEIGHT -> descriptionDescriptors[i] = new DescriptionDescriptor(
                DescriptionType.values()[i], "160 cm");
        case RACE -> descriptionDescriptors[i] = new DescriptionDescriptor(
                DescriptionType.values()[i], "Human");
        case AGE -> descriptionDescriptors[i] = new DescriptionDescriptor(
                DescriptionType.values()[i], "52");
        case OTHER -> descriptionDescriptors[i] = new DescriptionDescriptor(
                DescriptionType.values()[i], "He is a funny guy.");
        default -> {

        }
      }
    }
    return descriptionDescriptors;
  }

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
      Stat stat = characterSheet.getStatOfType(statType);
      for (OriginType origin : OriginType.values()) {
        if (origin == OriginType.LEVEL_POINT && !stat.isLevelStat()) {
          assertThrows(IllegalArgumentException.class,
                  () -> characterSheet.incrementStat(statType, origin));
          continue;
        }
        characterSheet.incrementStat(statType, origin);
        if (origin == OriginType.LEVEL_POINT) {
          if (statType == StatType.STRENGTH) {
            assertEquals(3, stat.getAbilityPointsUsed());
          } else {
            assertEquals(1, stat.getAbilityPointsUsed());
          }
        } else {
          System.out.println(stat.toString());
          switch (statType) {
            case HEALTH -> assertEquals(-1, stat.getMiscellaneous());
            case STRENGTH -> assertEquals(7, stat.getMiscellaneous());
            default -> assertEquals(1, stat.getMiscellaneous());
          }
        }
        characterSheet.decrementStat(statType, origin);
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