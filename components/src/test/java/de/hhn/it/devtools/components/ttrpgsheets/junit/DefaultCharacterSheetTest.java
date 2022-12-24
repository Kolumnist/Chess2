package de.hhn.it.devtools.components.ttrpgsheets.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    assertEquals(new DescriptionDescriptor(DescriptionType.CHARACTER_CLASS, "Warrior"),
            characterSheet.getDescriptionDescriptor(DescriptionType.CHARACTER_CLASS));
  }

  @Test
  void rollDiceTest() {
    logger.info("rollDiceTest() is called");
    DiceType[] diceTypes = {DiceType.D2, DiceType.D4, DiceType.D6, DiceType.D8,
            DiceType.D10, DiceType.D12, DiceType.D20, DiceType.D100};
    for (DiceType diceType : diceTypes) {
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
    DiceType[] diceTypes = {DiceType.D2, DiceType.D4, DiceType.D6, DiceType.D8,
            DiceType.D10, DiceType.D12, DiceType.D20, DiceType.D100};
    for (DiceType diceTyp : diceTypes) {
      characterSheet.getDiceDescriptor().setDiceType(diceTyp);
      switch (characterSheet.getDice().getType()) {
        case D2 -> assertEquals(DiceType.D2, characterSheet.getDiceDescriptor().getDiceType());
        case D4 -> assertEquals(DiceType.D4, characterSheet.getDiceDescriptor().getDiceType());
        case D6 -> assertEquals(DiceType.D6, characterSheet.getDiceDescriptor().getDiceType());
        case D8 -> assertEquals(DiceType.D8, characterSheet.getDiceDescriptor().getDiceType());
        case D10 -> assertEquals(DiceType.D10, characterSheet.getDiceDescriptor().getDiceType());
        case D12 -> assertEquals(DiceType.D12, characterSheet.getDiceDescriptor().getDiceType());
        case D20 -> assertEquals(DiceType.D20, characterSheet.getDiceDescriptor().getDiceType());
        case D100 -> assertEquals(DiceType.D100, characterSheet.getDiceDescriptor().getDiceType());
        default -> {
        }
      }
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
  class SimpleCharacterSheetListener implements CharacterSheetListener {

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