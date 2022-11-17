package de.hhn.it.devtools.components.ttrpgsheets;

import de.hhn.it.devtools.apis.ttrpgsheets.DiceDescriptor;
import java.util.Random;


/**
 * The class Dice for the default Character Sheet.
 */
public class Dice {
  private int size;
  private int value;
  Random generator = new Random();

  /**
   * Constructor stating diceTyp of {@link DiceDescriptor} and value of the Dice throw the method getResult().
   *
   * @param diceTyp type of the Dice
   *
   */
  public Dice(DiceDescriptor diceTyp) {
    changeDiceTyp(diceTyp);
    value = diceTyp.getResult();
  }

  public int getSize() {
    return size;
  }

  public int getValue() {
    return value;
  }

  public int rollDice() {
    value = generator.nextInt(size) + 1;
    return value;
  }

  public void changeDiceTyp(DiceDescriptor diceTyp) {
    switch (diceTyp.getDiceType()) {
      case D2 -> size = 2;
      case D4 -> size = 4;
      case D6 -> size = 6;
      case D8 -> size = 8;
      case D10 -> size = 10;
      case D12 -> size = 12;
      case D20 -> size = 20;
      case D100 -> size = 100;
      default -> size = 0;
    }
  }
}
