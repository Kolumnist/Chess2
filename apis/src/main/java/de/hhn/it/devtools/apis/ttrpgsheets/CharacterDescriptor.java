package de.hhn.it.devtools.apis.ttrpgsheets;

import java.io.Serializable;
import java.util.Arrays;

/**
 * The Descriptor for the Character class.
 */
public class CharacterDescriptor implements Serializable {
  public static final CharacterDescriptor EMPTY = new CharacterDescriptor(
          new DescriptionDescriptor[]{
                  new DescriptionDescriptor(DescriptionType.PLAYER_NAME, ""),
                  new DescriptionDescriptor(DescriptionType.CHARACTER_NAME, ""),
                  new DescriptionDescriptor(DescriptionType.NICKNAME, ""),
                  new DescriptionDescriptor(DescriptionType.AGE, ""),
                  new DescriptionDescriptor(DescriptionType.RACE, ""),
                  new DescriptionDescriptor(DescriptionType.HEIGHT, ""),
                  new DescriptionDescriptor(DescriptionType.WEIGHT, ""),
                  new DescriptionDescriptor(DescriptionType.SKIN_COLOR, ""),
                  new DescriptionDescriptor(DescriptionType.HAIR_COLOR, ""),
                  new DescriptionDescriptor(DescriptionType.EYE_COLOR, ""),
                  new DescriptionDescriptor(DescriptionType.CHARACTER_CLASS, ""),
                  new DescriptionDescriptor(DescriptionType.OTHER, "")
          },
          new StatDescriptor[]{
                  new StatDescriptor(StatType.LEVEL, 1, 0, 0, 0, false),
                  new StatDescriptor(StatType.HEALTH, 30, 0, 0, 0, false),
                  new StatDescriptor(StatType.MAX_HEALTH, 30, 5, 0, 0, true),
                  new StatDescriptor(StatType.DEFENCE, 0, 1, 0, 0, true),
                  new StatDescriptor(StatType.STRENGTH, 0, 1, 0, 0, true),
                  new StatDescriptor(StatType.AGILITY, 0, 1, 0, 0, true),
                  new StatDescriptor(StatType.DEXTERITY, 0, 1, 0, 0, true)
          },
          new DiceDescriptor(DiceType.D6, 1));

  private DescriptionDescriptor[] descriptions;
  private StatDescriptor[] stats;
  private DiceDescriptor dice;

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
