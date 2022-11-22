package de.hhn.it.devtools.apis.ttrpgsheets;

import java.util.Arrays;

/**
 * The Descriptor for the Character class.
 */
public class CharacterDescriptor {
  DescriptionDescriptor[] descriptions;
  StatDescriptor[] stats;
  DiceDescriptor dice;

  /**
   * Constructor stating {@link DescriptionDescriptor}, {@link StatDescriptor}
   * and {@link DiceDescriptor}.
   *
   * @param descriptions array of DescriptionDescriptor.
   * @param stats array of StatDescriptor.
   * @param dice the DiceDescriptor.
   */
  public CharacterDescriptor(DescriptionDescriptor[] descriptions,
                             StatDescriptor[] stats, DiceDescriptor dice) {
    this.descriptions = descriptions;
    this.stats = stats;
    this.dice = dice;
  }

  /**
   * Returns the array of {@link DescriptionDescriptor}.
   *
   * @return array of descriptions.
   */
  public DescriptionDescriptor[] getDescriptions() {
    return descriptions;
  }

  /**
   * Sets the array of {@link DescriptionDescriptor}.
   *
   * @param descriptions array of DescriptionDescriptor
   */
  public void setDescriptions(DescriptionDescriptor[] descriptions) {
    this.descriptions = descriptions;
  }

  /**
   * Returns the array of {@link StatDescriptor}.
   *
   * @return array of stats.
   */
  public StatDescriptor[] getStats() {
    return stats;
  }

  /**
   * Sets the array of {@link StatDescriptor}.
   *
   * @param stats array of StatDescriptor.
   */
  public void setStats(StatDescriptor[] stats) {
    this.stats = stats;
  }

  /**
   * Returns the {@link DiceDescriptor}.
   *
   * @return the DiceDescriptor
   */
  public DiceDescriptor getDice() {
    return dice;
  }

  /**
   * Sets the {@link DiceDescriptor}.
   *
   * @param dice the DiceDescriptor
   */
  public void setDice(DiceDescriptor dice) {
    this.dice = dice;
  }

  @Override
  public String toString() {
    return  "descriptions = " + Arrays.toString(descriptions)
            + ", stats = " + Arrays.toString(stats)
            + ", dice = " + dice;
  }
}
