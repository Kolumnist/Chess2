package de.hhn.it.devtools.apis.ttrpgsheets;

/**
 * The Descriptor for the Stat class.
 */
public class StatDescriptor {
  private StatType statType;
  private int baseValue;
  private int offset;
  private int abilityPointsUsed;

  /**
   * Constructor stating {@link StatType} and value of the Stat.
   *
   * @param statType the type of the Stat
   * @param value the value of the Stat
   */
  public StatDescriptor(StatType statType, int value) {
    this.statType = statType;
    this.value = value;
  }

  /**
   * Returns the {@link StatType} of the Stat.
   *
   * @return {@link StatType} of the Stat
   */
  public StatType getStatType() {
    return statType;
  }

  /**
   * Sets the {@link StatType} of the Stat.
   *
   * @param statType type of Stat
   */
  public void setStatType(StatType statType) {
    this.statType = statType;
  }

  /**
   * Returns the value of the Stat.
   *
   * @return value of the Stat
   */
  public int getValue() {
    return value;
  }

  /**
   * Sets the value of the Stat.
   *
   * @param value value of the Stat
   */
  public void setValue(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Stat type=" + getStatType()
            + ", Value=" + getValue();
  }
}
