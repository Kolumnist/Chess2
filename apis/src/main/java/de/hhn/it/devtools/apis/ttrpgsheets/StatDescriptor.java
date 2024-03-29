package de.hhn.it.devtools.apis.ttrpgsheets;

import java.io.Serializable;

/**
 * The Descriptor for the Stat class.
 */
public class StatDescriptor implements Serializable {
  private StatType statType;
  private int baseValue;
  private int offset;
  private int abilityPointsUsed;
  private int miscellaneous;
  private boolean levelStat;

  /**
   * Constructor stating {@link StatType} and value of the Stat.
   *
   * @param statType The type of the Stat
   * @param baseValue The base Value of the Stat
   * @param offset The offset of the Stat
   * @param abilityPointsUsed The ability points used in the Stat
   * @param miscellaneous The miscellaneous additions to the Stat
   * @param levelStat True, if the Stat can be leveled
   */
  public StatDescriptor(StatType statType, int baseValue, int offset, int abilityPointsUsed,
                        int miscellaneous, boolean levelStat) {
    this.statType = statType;
    this.baseValue = baseValue;
    this.offset = offset;
    this.abilityPointsUsed = abilityPointsUsed;
    this.miscellaneous = miscellaneous;
    this.levelStat = levelStat;
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

  public int getMiscellaneous() {
    return miscellaneous;
  }

  public void setMiscellaneous(int miscellaneous) {
    this.miscellaneous = miscellaneous;
  }

  public boolean isLevelStat() {
    return levelStat;
  }

  public void setLevelStat(boolean levelStat) {
    this.levelStat = levelStat;
  }

  @Override
  public String toString() {
    return "StatDescriptor: [Type: " + statType
            + ", Base Value: " + baseValue
            + ", Offset: " + offset
            + ", Ability Points Used: " + abilityPointsUsed
            + ", Miscellaneous: " + miscellaneous
            + ", Level Stat: " + levelStat + "]";
  }
}
