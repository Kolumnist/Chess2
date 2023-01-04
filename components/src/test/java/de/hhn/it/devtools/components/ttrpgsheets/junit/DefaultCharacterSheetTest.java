package de.hhn.it.devtools.components.ttrpgsheets.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import de.hhn.it.devtools.components.ttrpgsheets.Description;
import de.hhn.it.devtools.components.ttrpgsheets.Stat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DefaultCharacterSheetTest {
  private static final org.slf4j.Logger logger
          = org.slf4j.LoggerFactory.getLogger(DefaultCharacterSheetTest.class);

  SimpleCharacterSheetListener listener = null;
  DefaultCharacterSheet characterSheet = null;

  @BeforeEach
  void setupObjects() {
    logger.info("setupObjects() is called");
    listener = new SimpleCharacterSheetListener();
    characterSheet = new DefaultCharacterSheet(listener, setupCharacterDescriptor());
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
    SimpleCharacterSheetListener newListener = new SimpleCharacterSheetListener();
    characterSheet.addCallback(newListener);
    characterSheet.incrementStat(StatType.AGILITY, OriginType.LEVEL_POINT);
    assertEquals(1, newListener.stats.size());
  }

  @Test
  void wrapCharacterTest() {
    logger.info("wrapCharacterTest() is called");
    CharacterDescriptor compCharDesc = setupCharacterDescriptor();
    CharacterDescriptor testCharDesc = characterSheet.wrapCharacter();

    for (int i = 0; i < compCharDesc.getDescriptions().length; i++) {
      assertEquals(compCharDesc.getDescriptions()[i].getDescriptionType(),
              testCharDesc.getDescriptions()[i].getDescriptionType());
      assertEquals(compCharDesc.getDescriptions()[i].getText(),
              testCharDesc.getDescriptions()[i].getText());
    }
    for (int i = 0; i < compCharDesc.getStats().length; i++) {
      StatDescriptor compareStatDesc = compCharDesc.getStats()[i];
      StatDescriptor statDescriptor = testCharDesc.getStats()[i];
      assertEquals(compareStatDesc.getMiscellaneous(), statDescriptor.getMiscellaneous());
      assertEquals(compareStatDesc.getStatType(), statDescriptor.getStatType());
      assertEquals(compareStatDesc.getOffset(), statDescriptor.getOffset());
      assertEquals(compareStatDesc.getBaseValue(), statDescriptor.getBaseValue());
      assertEquals(compareStatDesc.getAbilityPointsUsed(), statDescriptor.getAbilityPointsUsed());
      assertEquals(compareStatDesc.isLevelStat(), statDescriptor.isLevelStat());
    }
    assertEquals(compCharDesc.getDice().getDiceType(), testCharDesc.getDice().getDiceType());
    assertEquals(compCharDesc.getDice().getResult(), testCharDesc.getDice().getResult());
  }

  @Test
  void incrementStatTest() {
    logger.info("incrementStatTest() is called");
    for (StatType statType : StatType.values()) {
      StatDescriptor stat = characterSheet.getStatDescriptor(statType);
      for (OriginType origin : OriginType.values()) {
        if (origin == OriginType.LEVEL_POINT && !stat.isLevelStat()) {
          assertThrows(IllegalArgumentException.class,
                  () -> characterSheet.incrementStat(statType, origin));
          continue;
        }
        characterSheet.incrementStat(statType, origin);
        stat = characterSheet.getStatDescriptor(statType);
        if (origin == OriginType.LEVEL_POINT) {
          if (statType == StatType.STRENGTH) {
            assertEquals(3, stat.getAbilityPointsUsed());
          } else {
            assertEquals(1, stat.getAbilityPointsUsed());
          }
          for (Stat csStat : characterSheet.getStats()) {
            if (csStat.getType() == statType) {
              csStat.setAbilityPointsUsed(csStat.getAbilityPointsUsed() - 1);
            }
          }
        } else {
          switch (statType) {
            case HEALTH -> assertEquals(-1, stat.getMiscellaneous());
            case STRENGTH -> assertEquals(7, stat.getMiscellaneous());
            default -> assertEquals(1, stat.getMiscellaneous());
          }
          for (Stat csStat : characterSheet.getStats()) {
            if (csStat.getType() == statType) {
              csStat.setMiscellaneous(csStat.getMiscellaneous() - 1);
            }
          }
        }
      }
    }
    for (StatType statType : StatType.values()) {
      Stat stat = characterSheet.getStatOfType(statType);
      for (OriginType origin : OriginType.values()) {
        if (origin == OriginType.LEVEL_POINT && !stat.isLevelStat()) {
          assertThrows(IllegalArgumentException.class,
                  () -> characterSheet.incrementStat(statType, origin, 5));
          continue;
        }
        characterSheet.incrementStat(statType, origin, 5);
        if (origin == OriginType.LEVEL_POINT) {
          if (statType == StatType.STRENGTH) {
            assertEquals(7, stat.getAbilityPointsUsed());
          } else {
            assertEquals(5, stat.getAbilityPointsUsed());
          }
          for (Stat csStat : characterSheet.getStats()) {
            if (csStat.getType() == statType) {
              csStat.setAbilityPointsUsed(csStat.getAbilityPointsUsed() - 5);
            }
          }
        } else {
          switch (statType) {
            case HEALTH -> assertEquals(3, stat.getMiscellaneous());
            case STRENGTH -> assertEquals(11, stat.getMiscellaneous());
            default -> assertEquals(5, stat.getMiscellaneous());
          }
          for (Stat csStat : characterSheet.getStats()) {
            if (csStat.getType() == statType) {
              csStat.setMiscellaneous(csStat.getMiscellaneous() - 5);
            }
          }
        }
      }
    }
    Stat edgeStat = new Stat(characterSheet.getStatDescriptor(StatType.STRENGTH));
    for (Stat csStat : characterSheet.getStats()) {
      if (csStat.getType() == StatType.STRENGTH) {
        edgeStat = csStat;
      }
    }
    edgeStat.setAbilityPointsUsed(Integer.MAX_VALUE);
    edgeStat.setMiscellaneous(Integer.MAX_VALUE);
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT);
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.ABILITY);
    assertEquals(Integer.MAX_VALUE, edgeStat.getAbilityPointsUsed());
    assertEquals(Integer.MAX_VALUE, edgeStat.getMiscellaneous());

    edgeStat.setAbilityPointsUsed(5);
    edgeStat.setMiscellaneous(5);
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT, Integer.MAX_VALUE);
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.ABILITY, Integer.MAX_VALUE);
    assertEquals(Integer.MAX_VALUE, edgeStat.getAbilityPointsUsed());
    assertEquals(Integer.MAX_VALUE, edgeStat.getMiscellaneous());

    edgeStat.setAbilityPointsUsed(-5);
    edgeStat.setMiscellaneous(-5);
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT, Integer.MIN_VALUE);
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.ABILITY, Integer.MIN_VALUE);
    assertEquals(Integer.MIN_VALUE, edgeStat.getAbilityPointsUsed());
    assertEquals(Integer.MIN_VALUE, edgeStat.getMiscellaneous());

    edgeStat.setAbilityPointsUsed(-5);
    edgeStat.setMiscellaneous(-5);
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT, -5);
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.ABILITY, -7);
    assertEquals(-10, edgeStat.getAbilityPointsUsed());
    assertEquals(-12, edgeStat.getMiscellaneous());
  }

  @Test
  void decrementStatTest() {
    logger.info("decrementStatTest() is called");
    for (StatType statType : StatType.values()) {
      StatDescriptor stat = characterSheet.getStatDescriptor(statType);
      for (OriginType origin : OriginType.values()) {
        if (origin == OriginType.LEVEL_POINT && !stat.isLevelStat()) {
          assertThrows(IllegalArgumentException.class,
                  () -> characterSheet.decrementStat(statType, origin));
          continue;
        }
        characterSheet.decrementStat(statType, origin);
        stat = characterSheet.getStatDescriptor(statType);
        if (origin == OriginType.LEVEL_POINT) {
          if (statType == StatType.STRENGTH) {
            assertEquals(1, stat.getAbilityPointsUsed());
          } else {
            assertEquals(-1, stat.getAbilityPointsUsed());
          }
          for (Stat csStat : characterSheet.getStats()) {
            if (csStat.getType() == statType) {
              csStat.setAbilityPointsUsed(csStat.getAbilityPointsUsed() + 1);
            }
          }
        } else {
          switch (statType) {
            case HEALTH -> assertEquals(-3, stat.getMiscellaneous());
            case STRENGTH -> assertEquals(5, stat.getMiscellaneous());
            default -> assertEquals(-1, stat.getMiscellaneous());
          }
          for (Stat csStat : characterSheet.getStats()) {
            if (csStat.getType() == statType) {
              csStat.setMiscellaneous(csStat.getMiscellaneous() + 1);
            }
          }
        }
      }
    }
    for (StatType statType : StatType.values()) {
      Stat stat = characterSheet.getStatOfType(statType);
      for (OriginType origin : OriginType.values()) {
        if (origin == OriginType.LEVEL_POINT && !stat.isLevelStat()) {
          assertThrows(IllegalArgumentException.class,
                  () -> characterSheet.decrementStat(statType, origin, 5));
          continue;
        }
        characterSheet.decrementStat(statType, origin, 5);
        if (origin == OriginType.LEVEL_POINT) {
          if (statType == StatType.STRENGTH) {
            assertEquals(-3, stat.getAbilityPointsUsed());
          } else {
            assertEquals(-5, stat.getAbilityPointsUsed());
          }
          for (Stat csStat : characterSheet.getStats()) {
            if (csStat.getType() == statType) {
              csStat.setAbilityPointsUsed(csStat.getAbilityPointsUsed() + 5);
            }
          }
        } else {
          switch (statType) {
            case HEALTH -> assertEquals(-7, stat.getMiscellaneous());
            case STRENGTH -> assertEquals(1, stat.getMiscellaneous());
            default -> assertEquals(-5, stat.getMiscellaneous());
          }
          for (Stat csStat : characterSheet.getStats()) {
            if (csStat.getType() == statType) {
              csStat.setMiscellaneous(csStat.getMiscellaneous() + 5);
            }
          }
        }
      }
    }
    Stat edgeStat = new Stat(characterSheet.getStatDescriptor(StatType.STRENGTH));
    for (Stat csStat : characterSheet.getStats()) {
      if (csStat.getType() == StatType.STRENGTH) {
        edgeStat = csStat;
      }
    }
    edgeStat.setAbilityPointsUsed(Integer.MIN_VALUE);
    edgeStat.setMiscellaneous(Integer.MIN_VALUE);
    characterSheet.decrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT);
    characterSheet.decrementStat(StatType.STRENGTH, OriginType.ABILITY);
    assertEquals(Integer.MIN_VALUE, edgeStat.getAbilityPointsUsed());
    assertEquals(Integer.MIN_VALUE, edgeStat.getMiscellaneous());

    edgeStat.setAbilityPointsUsed(-5);
    edgeStat.setMiscellaneous(-5);
    characterSheet.decrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT, Integer.MAX_VALUE);
    characterSheet.decrementStat(StatType.STRENGTH, OriginType.ABILITY, Integer.MAX_VALUE);
    assertEquals(Integer.MIN_VALUE, edgeStat.getAbilityPointsUsed());
    assertEquals(Integer.MIN_VALUE, edgeStat.getMiscellaneous());

    edgeStat.setAbilityPointsUsed(5);
    edgeStat.setMiscellaneous(5);
    characterSheet.decrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT, Integer.MIN_VALUE);
    characterSheet.decrementStat(StatType.STRENGTH, OriginType.ABILITY, Integer.MIN_VALUE);
    assertEquals(Integer.MAX_VALUE, edgeStat.getAbilityPointsUsed());
    assertEquals(Integer.MAX_VALUE, edgeStat.getMiscellaneous());

    edgeStat.setAbilityPointsUsed(5);
    edgeStat.setMiscellaneous(5);
    characterSheet.decrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT, -5);
    characterSheet.decrementStat(StatType.STRENGTH, OriginType.ABILITY, -7);
    assertEquals(10, edgeStat.getAbilityPointsUsed());
    assertEquals(12, edgeStat.getMiscellaneous());
  }

  @Test
  void getStatDisplayValueTest() {
    logger.info("getStatDisplayValueTest() is called");
    for (Stat stat : characterSheet.getStats()) {
      switch (stat.getType()) {
        case MAX_HEALTH -> assertEquals(30, characterSheet.getStatDisplayValue(stat.getType()));
        case HEALTH -> assertEquals(28, characterSheet.getStatDisplayValue(stat.getType()));
        case LEVEL -> assertEquals(1, characterSheet.getStatDisplayValue(stat.getType()));
        case STRENGTH -> assertEquals(8, characterSheet.getStatDisplayValue(stat.getType()));
        default -> assertEquals(0, characterSheet.getStatDisplayValue(stat.getType()));
      }
    }
    characterSheet.setStats(new Stat[0]);
    for (StatType statType : StatType.values()) {
      assertThrows(IllegalArgumentException.class,
              () -> characterSheet.getStatDisplayValue(statType));
    }
  }

  @Test
  void getStatDescriptorTest() {
    logger.info("getStatDescriptorTest() is called");
    StatDescriptor[] descArray = setupStats();
    for (StatType statType : StatType.values()) {
      StatDescriptor statDescriptor = characterSheet.getStatDescriptor(statType);
      StatDescriptor compareStatDesc = descArray[statType.ordinal()];

      assertEquals(compareStatDesc.getMiscellaneous(), statDescriptor.getMiscellaneous());
      assertEquals(compareStatDesc.getStatType(), statDescriptor.getStatType());
      assertEquals(compareStatDesc.getOffset(), statDescriptor.getOffset());
      assertEquals(compareStatDesc.getBaseValue(), statDescriptor.getBaseValue());
      assertEquals(compareStatDesc.getAbilityPointsUsed(), statDescriptor.getAbilityPointsUsed());
      assertEquals(compareStatDesc.isLevelStat(), statDescriptor.isLevelStat());
    }
    for (StatType statType : StatType.values()) {
      characterSheet.setStats(new Stat[0]);
      assertNull(characterSheet.getStatDescriptor(statType));
    }
  }

  @Test
  void getStatOfTypeTest() {
    logger.info("getStatOfTypeTest() is called");
    for (StatType statType : StatType.values()) {
      assertEquals(characterSheet.getStats()[statType.ordinal()],
              characterSheet.getStatOfType(statType));
    }
    for (StatType statType : StatType.values()) {
      characterSheet.setStats(null);
      assertNull(characterSheet.getStatOfType(statType));
    }
    for (StatType statType : StatType.values()) {
      characterSheet.setStats(new Stat[0]);
      assertNull(characterSheet.getStatOfType(statType));
    }
  }

  @Test
  void changeDescriptionTest() {
    characterSheet.changeDescription(DescriptionType.CHARACTER_NAME, "Boris");
    assertEquals(characterSheet.getDescriptionDescriptor(DescriptionType.CHARACTER_NAME).getText(),
            "Boris");

    characterSheet.changeDescription(DescriptionType.HAIR_COLOR, "Red");
    assertEquals(characterSheet.getDescriptionDescriptor(DescriptionType.HAIR_COLOR).getText(),
            "Red");

    assertThrows(IllegalArgumentException.class,
            () -> characterSheet.changeDescription(null, "white"));
    assertThrows(IllegalArgumentException.class,
            () -> characterSheet.changeDescription(DescriptionType.HEIGHT, null));
    assertThrows(IllegalArgumentException.class,
            () -> characterSheet.changeDescription(null, null));

    assertEquals(2, listener.descriptions.size());
  }

  @Test
  void getDescriptionDescriptorTest() {
    logger.info("getDescriptionDescriptorTest() is called");
    DescriptionDescriptor[] descArray = setupDescriptions();
    for (DescriptionType descType : DescriptionType.values()) {
      DescriptionDescriptor descDescriptor = characterSheet.getDescriptionDescriptor(descType);
      DescriptionDescriptor compareDescDesc = descArray[descType.ordinal()];

      assertEquals(compareDescDesc.getText(), descDescriptor.getText());
      assertEquals(compareDescDesc.getDescriptionType(), descDescriptor.getDescriptionType());
    }
    for (DescriptionType descType : DescriptionType.values()) {
      characterSheet.setDescriptions(new Description[0]);
      assertNull(characterSheet.getDescriptionDescriptor(descType));
    }
  }

  @Test
  void rollDiceTest() {
    logger.info("rollDiceTest() is called");
    for (DiceType diceType : DiceType.values()) {
      characterSheet.getDiceDescriptor().setDiceType(diceType);
      for (double i = 0; i < calculateAverageNumberOfRolls(
              sizeFromDice(characterSheet.getDiceDescriptor().getDiceType())); i++) {
        characterSheet.rollDice();
        assertTrue(characterSheet.getDiceDescriptor().getResult() > 0
                           && characterSheet.getDiceDescriptor().getResult() <= sizeFromDice(
                characterSheet.getDiceDescriptor().getDiceType()));
      }
    }
  }

  @Test
  void changeDiceTypeTest() {
    logger.info("changeDiceTypeTest() is called");
    for (DiceType diceType : DiceType.values()) {
      characterSheet.changeDiceType(diceType);
      assertEquals(diceType, characterSheet.getDice().getType());
    }
  }

  @Test
  void getDiceDescriptorTest() {
    logger.info("getDiceDescriptorTest() is called");
  }

  @Test
  void toStringTest() {
    logger.info("toStringTest() is called");
    logger.info(characterSheet.toString());
    assertTrue(characterSheet.toString()
                       .contains("Description: [Type: EYE_COLOR, Description: Blue"));
  }

  public static double calculateAverageNumberOfRolls(int size) {
    double result = 0;
    for (double i = size; i > 0; i--) {
      result += (double) size / i;
    }
    return Math.ceil(result);
  }

  public static int sizeFromDice(DiceType diceType) {
    switch (diceType) {
      case D2 -> {
        return 2;
      }
      case D4 -> {
        return 4;
      }
      case D6 -> {
        return 6;
      }
      case D8 -> {
        return 8;
      }
      case D10 -> {
        return 10;
      }
      case D12 -> {
        return 12;
      }
      case D20 -> {
        return 20;
      }
      case D100 -> {
        return 100;
      }
      default -> {
      }
    }
    return -1;
  }

  /**
   * Inner class as a simple implementation of {@link CharacterSheetListener} for testing.
   */
  static class SimpleCharacterSheetListener implements CharacterSheetListener {

    public List<StatDescriptor> stats;
    public List<DiceDescriptor> dice;
    public List<DescriptionDescriptor> descriptions;

    public SimpleCharacterSheetListener() {
      stats = new ArrayList<>();
      dice = new ArrayList<>();
      descriptions = new ArrayList<>();
    }

    @Override
    public void statChanged(StatDescriptor stat) {
      this.stats.add(stat);
    }

    @Override
    public void diceChanged(DiceDescriptor dice) {
      this.dice.add(dice);
    }

    @Override
    public void descriptionChanged(DescriptionDescriptor description) {
      this.descriptions.add(description);
    }
  }
}