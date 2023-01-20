package de.hhn.it.devtools.components.ttrpgsheets.junit;

import static org.junit.jupiter.api.Assertions.assertAll;
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
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DefaultCharacterSheetTest {
  SimpleCharacterSheetListener listener = null;
  DefaultCharacterSheet characterSheet = null;
  DefaultCharacterSheet nullSheet = new DefaultCharacterSheet(new CharacterDescriptor(
          new DescriptionDescriptor[0], new StatDescriptor[0],
          new DiceDescriptor(DiceType.D2, 0)));

  @BeforeEach
  void setupObjects() {
    listener = new SimpleCharacterSheetListener();
    characterSheet = new DefaultCharacterSheet(setupCharacterDescriptor());
    characterSheet.addCallback(listener);
  }

  CharacterDescriptor setupCharacterDescriptor() {
    return new CharacterDescriptor(setupDescriptions(), setupStats(),
            new DiceDescriptor(DiceType.D6, 1));
  }

  StatDescriptor[] setupStats() {
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
    assertEquals(7, newListener.stats.size());
    assertEquals(12, newListener.descriptions.size());
    assertEquals(1, newListener.dice.size());
    assertThrows(IllegalArgumentException.class, () -> characterSheet.addCallback(null));
  }

  @Test
  void wrapCharacterTest() {
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
  void incrementStatSingleTest() {
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
        } else {
          switch (statType) {
            case HEALTH -> assertEquals(-1, stat.getMiscellaneous());
            case STRENGTH -> assertEquals(7, stat.getMiscellaneous());
            default -> assertEquals(1, stat.getMiscellaneous());
          }
        }
        characterSheet.incrementStat(statType, origin, -1);
      }
    }
  }

  @Test
  void incrementStatAmountTest() {
    for (StatType statType : StatType.values()) {
      StatDescriptor stat = characterSheet.getStatDescriptor(statType);
      for (OriginType origin : OriginType.values()) {
        if (origin == OriginType.LEVEL_POINT && !stat.isLevelStat()) {
          assertThrows(IllegalArgumentException.class,
                  () -> characterSheet.incrementStat(statType, origin, 5));
          continue;
        }

        characterSheet.incrementStat(statType, origin, 5);
        stat = characterSheet.getStatDescriptor(statType);
        if (origin == OriginType.LEVEL_POINT) {
          if (statType == StatType.STRENGTH) {
            assertEquals(7, stat.getAbilityPointsUsed());
          } else {
            assertEquals(5, stat.getAbilityPointsUsed());
          }
        } else {
          switch (statType) {
            case HEALTH -> assertEquals(3, stat.getMiscellaneous());
            case STRENGTH -> assertEquals(11, stat.getMiscellaneous());
            default -> assertEquals(5, stat.getMiscellaneous());
          }
        }
        characterSheet.incrementStat(statType, origin, -5);
      }
    }
  }

  @Test
  void incrementStatOverflowTest() {
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT, 5);
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT, Integer.MAX_VALUE);
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.ABILITY, 5);
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.ABILITY, Integer.MAX_VALUE);
    assertAll(
            () -> assertEquals(Integer.MAX_VALUE,
                    characterSheet.getStatDescriptor(StatType.STRENGTH).getAbilityPointsUsed()),
            () -> assertEquals(Integer.MAX_VALUE,
                    characterSheet.getStatDescriptor(StatType.STRENGTH).getMiscellaneous())
    );
  }

  @Test
  void incrementStatUnderflowTest() {
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT, -10);
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.ABILITY, -10);
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT, Integer.MIN_VALUE);
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.ABILITY, Integer.MIN_VALUE);
    assertAll(
            () -> assertEquals(Integer.MIN_VALUE,
                    characterSheet.getStatDescriptor(StatType.STRENGTH).getAbilityPointsUsed()),
            () -> assertEquals(Integer.MIN_VALUE,
                    characterSheet.getStatDescriptor(StatType.STRENGTH).getMiscellaneous())
    );
  }

  @Test
  void incrementStatNegativeValuesTest() {
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT, -5);
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.ABILITY, -10);
    assertAll(
            () -> assertEquals(-3,
                    characterSheet.getStatDescriptor(StatType.STRENGTH).getAbilityPointsUsed()),
            () -> assertEquals(-4,
                    characterSheet.getStatDescriptor(StatType.STRENGTH).getMiscellaneous())
    );
  }

  @Test
  void incrementStatNullStatTest() {
    assertThrows(AssertionError.class, () ->
            nullSheet.incrementStat(StatType.STRENGTH, OriginType.OTHER));
  }

  @Test
  void decrementStatSingleTest() {
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
        } else {
          switch (statType) {
            case HEALTH -> assertEquals(-3, stat.getMiscellaneous());
            case STRENGTH -> assertEquals(5, stat.getMiscellaneous());
            default -> assertEquals(-1, stat.getMiscellaneous());
          }
        }
        characterSheet.decrementStat(statType, origin, -1);
      }
    }
  }

  @Test
  void decrementStatAmountTest() {
    for (StatType statType : StatType.values()) {
      StatDescriptor stat = characterSheet.getStatDescriptor(statType);
      for (OriginType origin : OriginType.values()) {
        if (origin == OriginType.LEVEL_POINT && !stat.isLevelStat()) {
          assertThrows(IllegalArgumentException.class,
                  () -> characterSheet.decrementStat(statType, origin, 5));
          continue;
        }

        characterSheet.decrementStat(statType, origin, 5);
        stat = characterSheet.getStatDescriptor(statType);
        if (origin == OriginType.LEVEL_POINT) {
          if (statType == StatType.STRENGTH) {
            assertEquals(-3, stat.getAbilityPointsUsed());
          } else {
            assertEquals(-5, stat.getAbilityPointsUsed());
          }
        } else {
          switch (statType) {
            case HEALTH -> assertEquals(-7, stat.getMiscellaneous());
            case STRENGTH -> assertEquals(1, stat.getMiscellaneous());
            default -> assertEquals(-5, stat.getMiscellaneous());
          }
        }
        characterSheet.decrementStat(statType, origin, -5);
      }
    }
  }

  @Test
  void decrementStatOverflowTest() {
    characterSheet.decrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT, -10);
    characterSheet.decrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT, Integer.MIN_VALUE);
    characterSheet.decrementStat(StatType.STRENGTH, OriginType.ABILITY, -10);
    characterSheet.decrementStat(StatType.STRENGTH, OriginType.ABILITY, Integer.MIN_VALUE);
    assertAll(
            () -> assertEquals(Integer.MAX_VALUE,
                    characterSheet.getStatDescriptor(StatType.STRENGTH).getAbilityPointsUsed()),
            () -> assertEquals(Integer.MAX_VALUE,
                    characterSheet.getStatDescriptor(StatType.STRENGTH).getMiscellaneous())
    );
  }

  @Test
  void decrementStatUnderflowTest() {
    characterSheet.decrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT, 10);
    characterSheet.decrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT, Integer.MAX_VALUE);
    characterSheet.decrementStat(StatType.STRENGTH, OriginType.ABILITY, 10);
    characterSheet.decrementStat(StatType.STRENGTH, OriginType.ABILITY, Integer.MAX_VALUE);
    assertAll(
            () -> assertEquals(Integer.MIN_VALUE,
                    characterSheet.getStatDescriptor(StatType.STRENGTH).getAbilityPointsUsed()),
            () -> assertEquals(Integer.MIN_VALUE,
                    characterSheet.getStatDescriptor(StatType.STRENGTH).getMiscellaneous())
    );
  }

  @Test
  void decrementStatNegativeValuesTest() {
    characterSheet.decrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT, -5);
    characterSheet.decrementStat(StatType.STRENGTH, OriginType.ABILITY, -10);
    assertAll(
            () -> assertEquals(7,
                    characterSheet.getStatDescriptor(StatType.STRENGTH).getAbilityPointsUsed()),
            () -> assertEquals(16,
                    characterSheet.getStatDescriptor(StatType.STRENGTH).getMiscellaneous())
    );
  }

  @Test
  void decrementStatNullStatTest() {
    assertThrows(AssertionError.class, () ->
            nullSheet.decrementStat(StatType.STRENGTH, OriginType.OTHER));
  }

  @Test
  void getStatDisplayValueTest() {
    for (StatType statType : StatType.values()) {
      StatDescriptor stat = characterSheet.getStatDescriptor(statType);
      switch (stat.getStatType()) {
        case MAX_HEALTH -> assertEquals(30, characterSheet.getStatDisplayValue(stat.getStatType()));
        case HEALTH -> assertEquals(28, characterSheet.getStatDisplayValue(stat.getStatType()));
        case LEVEL -> assertEquals(1, characterSheet.getStatDisplayValue(stat.getStatType()));
        case STRENGTH -> assertEquals(8, characterSheet.getStatDisplayValue(stat.getStatType()));
        default -> assertEquals(0, characterSheet.getStatDisplayValue(stat.getStatType()));
      }
    }
    for (StatType statType : StatType.values()) {
      assertThrows(IllegalArgumentException.class,
              () -> nullSheet.getStatDisplayValue(statType));
    }
  }

  @Test
  void getStatDescriptorTest() {
    StatDescriptor[] descArray = setupStats();
    for (StatType statType : StatType.values()) {
      StatDescriptor statDescriptor = characterSheet.getStatDescriptor(statType);
      StatDescriptor compareStatDesc = descArray[statType.ordinal()];
      assertAll(
              () -> assertEquals(compareStatDesc.getMiscellaneous(),
                      statDescriptor.getMiscellaneous()),
              () -> assertEquals(compareStatDesc.getStatType(), statDescriptor.getStatType()),
              () -> assertEquals(compareStatDesc.getOffset(), statDescriptor.getOffset()),
              () -> assertEquals(compareStatDesc.getBaseValue(), statDescriptor.getBaseValue()),
              () -> assertEquals(compareStatDesc.getAbilityPointsUsed(),
                      statDescriptor.getAbilityPointsUsed()),
              () -> assertEquals(compareStatDesc.isLevelStat(), statDescriptor.isLevelStat()),
              () -> assertNull(nullSheet.getStatDescriptor(statType))
      );
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
  }

  @Test
  void getDescriptionDescriptorTest() {
    DescriptionDescriptor[] descArray = setupDescriptions();
    for (DescriptionType descType : DescriptionType.values()) {
      DescriptionDescriptor descDescriptor = characterSheet.getDescriptionDescriptor(descType);
      DescriptionDescriptor compareDescDesc = descArray[descType.ordinal()];
      assertAll(
              () -> assertEquals(compareDescDesc.getText(), descDescriptor.getText()),
              () -> assertEquals(compareDescDesc.getDescriptionType(),
                      descDescriptor.getDescriptionType()),
              () -> assertNull(nullSheet.getDescriptionDescriptor(descType))
      );
    }
  }

  @Test
  void rollDiceTest() {
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
    for (DiceType diceType : DiceType.values()) {
      characterSheet.changeDiceType(diceType);
      assertEquals(diceType, characterSheet.getDiceDescriptor().getDiceType());
    }
  }

  @Test
  void getDiceDescriptorTest() {
    for (DiceType diceType : DiceType.values()) {
      characterSheet.changeDiceType(diceType);
      switch (characterSheet.getDiceDescriptor().getDiceType()) {
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
  void statCallbackTest() {
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT);
    assertEquals(characterSheet.getStatDescriptor(StatType.STRENGTH).toString(),
            listener.stats.get(listener.stats.size() - 1).toString());
  }

  @Test
  void descriptionCallbackTest() {
    characterSheet.changeDescription(DescriptionType.NICKNAME, "Nick");
    assertEquals(characterSheet.getDescriptionDescriptor(DescriptionType.NICKNAME).toString(),
            listener.descriptions.get(listener.descriptions.size() - 1).toString());
  }

  @Test
  void diceCallbackTest() {
    characterSheet.changeDiceType(DiceType.D10);
    assertEquals(characterSheet.getDiceDescriptor().toString(),
            listener.dice.get(listener.dice.size() - 1).toString());
  }

  @Test
  void toStringTest() {
    assertTrue(characterSheet.toString()
            .contains("Description: [Type: EYE_COLOR, Description: Blue]"));
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