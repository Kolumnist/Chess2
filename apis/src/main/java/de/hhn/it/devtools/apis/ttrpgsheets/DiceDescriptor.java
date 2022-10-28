package de.hhn.it.devtools.apis.ttrpgsheets;

/**
 * The Descriptor for the Dice class.
 */
public class DiceDescriptor {
  private DiceType diceType;
  private int result;

  /**
   * Constructor stating {@link DiceType} and result of the Dice.
   *
   * @param diceType the type of the Dice
   * @param result the result of the last dice throw
   */
  public DiceDescriptor(DiceType diceType, int result) {
    this.diceType = diceType;
    this.result = result;
  }

  /**
   * Returns the {@link DiceType} of the Dice.
   *
   * @return {@link DiceType} of the Dice
   */
  public DiceType getDiceType() {
    return diceType;
  }

  /**
   * Sets the {@link DiceType} of the Dice.
   *
   * @param diceType type of Dice
   */
  public void setDiceType(DiceType diceType) {
    this.diceType = diceType;
  }

  /**
   * Returns the result of the Dice.
   *
   * @return result of the last dice throw
   */
  public int getResult() {
    return result;
  }

  /**
   * Sets the result of the dice throw.
   *
   * @param result result of the dice throw
   */
  public void setResult(int result) {
    this.result = result;
  }

  @Override
  public String toString() {
    return "Dice type = " + getDiceType()
            + ", Result = " + getResult();
  }
}
