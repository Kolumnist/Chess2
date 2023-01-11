package de.hhn.it.devtools.components.ttrpgsheets.junit;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.hhn.it.devtools.apis.ttrpgsheets.DiceDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DiceType;
import de.hhn.it.devtools.components.ttrpgsheets.Dice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class DiceTest {

  static Dice dice;
  static int[] sizes = {2, 4, 6, 8, 10, 12, 20, 100, 3};

  @BeforeEach
  void setUp() {
    DiceDescriptor descriptor = new DiceDescriptor(DiceType.D6, 3);
    dice = new Dice(descriptor);
  }

  @Test
  void nextRollTest() {
    for (int size : sizes) {
      dice.setSize(size);
      for (double i = 0; i < calculateAverageNumberOfRolls(dice.getSize()); i++) {
        dice.nextRoll();
        assertTrue(dice.getValue() > 0 && dice.getValue() <= dice.getSize());
      }
    }
  }

  @Test
  void changeSizeTest() {
    for (DiceType diceTyp : DiceType.values()) {
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
        default -> { }
      }
    }
  }

  @Test
  void getTypeTest() {
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
        default -> assertNull(dice.getType());
      }
    }
  }

  @Test
  void getSizeTest() {
    dice.setSize(4);
    assertEquals(4, dice.getSize());
  }

  @Test
  void setSizeTest() {
    dice.setSize(6);
    assertEquals(6, dice.getSize());
  }

  @Test
  void getValueTest() {
    dice.setValue(5);
    assertEquals(5, dice.getValue());
  }

  @Test
  void setValueTest() {
    dice.setValue(3);
    assertEquals(3, dice.getValue());
  }

  @Test
  void testToStringTest() {
    dice.setValue(3);
    for (DiceType diceTyp : DiceType.values()) {
      dice.changeSize(diceTyp);
      switch (dice.getType()) {
        case D2 -> assertEquals("Dice: [Size: 2, Dice Value: 3]", dice.toString());
        case D4 -> assertEquals("Dice: [Size: 4, Dice Value: 3]", dice.toString());
        case D6 -> assertEquals("Dice: [Size: 6, Dice Value: 3]", dice.toString());
        case D8 -> assertEquals("Dice: [Size: 8, Dice Value: 3]", dice.toString());
        case D10 -> assertEquals("Dice: [Size: 10, Dice Value: 3]", dice.toString());
        case D12 -> assertEquals("Dice: [Size: 12, Dice Value: 3]", dice.toString());
        case D20 -> assertEquals("Dice: [Size: 20, Dice Value: 3]", dice.toString());
        case D100 -> assertEquals("Dice: [Size: 100, Dice Value: 3]", dice.toString());
        default -> { }
      }
    }
  }

  public static double calculateAverageNumberOfRolls(int size) {
    double result = 0;
    for (double i = size; i > 0; i--) {
      result += (double) size / i;
    }
    return Math.ceil(result);
  }

}

