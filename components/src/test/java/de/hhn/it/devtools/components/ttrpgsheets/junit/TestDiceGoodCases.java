package de.hhn.it.devtools.components.ttrpgsheets.junit;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.hhn.it.devtools.apis.ttrpgsheets.DiceDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DiceType;
import de.hhn.it.devtools.components.ttrpgsheets.Dice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class TestDiceGoodCases {
  private static final org.slf4j.Logger logger
          = org.slf4j.LoggerFactory.getLogger(TestDiceGoodCases.class);

  static Dice dice;
  static int[] sizes = {2, 4, 6, 8, 10, 12, 20, 100};
  static DiceType[] diceTypes = {DiceType.D2, DiceType.D4, DiceType.D6, DiceType.D8,
      DiceType.D10, DiceType.D12, DiceType.D20, DiceType.D100};

  @BeforeEach
  void setUp() {
    DiceDescriptor descriptor = new DiceDescriptor(DiceType.D6, 3);
    dice = new Dice(descriptor);
  }

  @Test
  void nextRollTest() {
    logger.info("nextRoll() is called");
    for (int size : sizes) {
      dice.setSize(size);
      for (double i = 0; i < calculateAvarageNumberOfRolls(dice.getSize()); i++) {
        dice.nextRoll();
        assertTrue(dice.getValue() > 0 && dice.getValue() <= dice.getSize());
      }
    }
  }

  @Test
  void changeSizeTest() {
    logger.info("changeSize() is called");
    for (DiceType diceTyp : diceTypes) {
      dice.changeSize(diceTyp);
      switch (dice.getType()) {
        case D2 -> assertEquals(2, dice.getSize());
        case D4 -> assertEquals(4, dice.getSize());
        case D6 -> assertEquals(6, dice.getSize());
        case D8 -> assertEquals(8, dice.getSize());
        case D10 -> assertEquals(10, dice.getSize());
        case D12 -> assertEquals(12, dice.getSize());
        case D20 -> assertEquals(20, dice.getSize());
        case D100 -> assertEquals(100, dice.getSize());
      }
    }
  }

  @Test
  void convertToDiceTypeTest() {
    logger.info("convertToDiceType() is called");
    for (int size : sizes) {
      dice.setSize(size);
      switch (dice.getSize()) {
        case 2 -> assertEquals(DiceType.D2, dice.getType());
        case 4 -> assertEquals(DiceType.D4, dice.getType());
        case 6 -> assertEquals(DiceType.D6, dice.getType());
        case 8 -> assertEquals(DiceType.D8, dice.getType());
        case 10 -> assertEquals(DiceType.D10, dice.getType());
        case 12 -> assertEquals(DiceType.D12, dice.getType());
        case 20 -> assertEquals(DiceType.D20, dice.getType());
        case 100 -> assertEquals(DiceType.D100, dice.getType());
      }
    }
  }

  @Test
  void getTypeTest() {
    logger.info("getType() is called");
    dice.setSize(2);
    assertEquals(DiceType.D2, dice.getType());
  }

  @Test
  void getSizeTest() {
    logger.info("getSize() is called");
    dice.setSize(4);
    assertEquals(4, dice.getSize());
  }

  @Test
  void setSizeTest() {
    logger.info("setSize() is called");
    dice.setSize(6);
    assertEquals(6, dice.getSize());
  }

  @Test
  void getValueTest() {
    logger.info("getValue() is called");
    dice.setValue(5);
    assertEquals(5, dice.getValue());
  }

  @Test
  void setValueTest() {
    logger.info("setValue() is called");
    dice.setValue(3);
    assertEquals(3, dice.getValue());
  }

  @Test
  void testToStringTest() {
    logger.info("testToString() is called");
    dice.setValue(3);
    for (DiceType diceTyp : diceTypes) {
      dice.changeSize(diceTyp);
      switch (dice.getType()) {
        case D2 -> assertEquals("Dice Size: 2"
                + "\nDice Value: 3", dice.toString());
        case D4 -> assertEquals("Dice Size: 4"
                + "\nDice Value: 3", dice.toString());
        case D6 -> assertEquals("Dice Size: 6"
                + "\nDice Value: 3", dice.toString());
        case D8 -> assertEquals("Dice Size: 8"
                + "\nDice Value: 3", dice.toString());
        case D10 -> assertEquals("Dice Size: 10"
                + "\nDice Value: 3", dice.toString());
        case D12 -> assertEquals("Dice Size: 12"
                + "\nDice Value: 3", dice.toString());
        case D20 -> assertEquals("Dice Size: 20"
                + "\nDice Value: 3", dice.toString());
        case D100 -> assertEquals("Dice Size: 100"
                + "\nDice Value: 3", dice.toString());
      }
    }
  }

  public static double calculateAvarageNumberOfRolls(int size) {
    double result = 0;
    for (double i = size; i > 0; i--) {
      result += (double) size / i;
    }
    return Math.ceil(result);
  }

}

