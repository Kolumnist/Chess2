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
   * @param statType The type of the Stat
   * @param baseValue The base Value of the Stat
   * @param offset The offset of the Stat
   * @param abilityPointsUsed The ability points used in the Stat
   */
  public StatDescriptor(StatType statType, int baseValue, int offset, int abilityPointsUsed) {
    setStatType(statType);
    setBaseValue(baseValue);
    setOffset(offset);
    setAbilityPointsUsed(abilityPointsUsed);
  }

  public StatType getStatType() {
    return statType;
  }

  public void setStatType(StatType statType) {
    this.statType = statType;
  }

  public int getBaseValue() {
    return baseValue;
  }

  public void setBaseValue(int baseValue) {
    this.baseValue = baseValue;
  }

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public int getAbilityPointsUsed() {
    return abilityPointsUsed;
  }

  public void setAbilityPointsUsed(int abilityPointsUsed) {
    this.abilityPointsUsed = abilityPointsUsed;
  }

  @Override
  public String toString() {
    return "Stat type=" + getStatType()
            + ", Value=" + getValue();
  }
}
