package de.hhn.it.devtools.components.ttrpgsheets;

import de.hhn.it.devtools.apis.ttrpgsheets.DiceDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DiceType;
import java.util.Random;


/**
 * The class Dice for the default Character Sheet.
 */
public class Dice {
  private int size;
  private int value;
  private final Random generator = new Random();

  /**
   * Constructor stating diceDescriptor of {@link DiceDescriptor}
   * and value of the Dice throw the method getResult().
   *
   * @param diceDescriptor type of the Dice
   *
   */
  public Dice(DiceDescriptor diceDescriptor) {
    changeSize(diceDescriptor.getDiceType());
    setValue(diceDescriptor.getResult());
  }

  /**
   * generate a new random value for the dice.
   *
   * @return new value for dice.
   */
  public int nextRoll() {
    setValue(generator.nextInt(size) + 1);
    return getValue();
  }

  /**
   * changes the size of the dice .
   *
   * @param diceTyp type of the dice.
   */
  public void changeSize(DiceType diceTyp) {
    switch (diceTyp) {
      case D2 -> setSize(2);
      case D4 -> setSize(4);
      case D6 -> setSize(6);
      case D8 -> setSize(8);
      case D10 -> setSize(10);
      case D12 -> setSize(12);
      case D20 -> setSize(20);
      case D100 -> setSize(100);
      default -> setSize(0);
    }
  }

  /**
   * Converts a given Size of Dice to a DiceType. If no conversion ist possible returns null.
   *
   * @param size The size of the Dice
   * @return The converted size to the DiceType. null if there is no conversion possible.
   */
  public DiceType convertToDiceType(int size) {
    switch (size) {
      case 2 -> {
        return DiceType.D2;
      }
      case 4 -> {
        return DiceType.D4;
      }
      case 6 -> {
        return DiceType.D6;
      }
      case 8 -> {
        return DiceType.D8;
      }
      case 10 -> {
        return DiceType.D10;
      }
      case 12 -> {
        return DiceType.D12;
      }
      case 20 -> {
        return DiceType.D20;
      }
      case 100 -> {
        return DiceType.D100;
      }
      default -> {
        return null;
      }
    }
  }


  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Dice Size: " + getSize()
            + "\nDice Value: " + getValue();
  }
}
