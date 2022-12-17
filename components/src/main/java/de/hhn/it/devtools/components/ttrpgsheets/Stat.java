package de.hhn.it.devtools.components.ttrpgsheets;

import de.hhn.it.devtools.apis.ttrpgsheets.StatDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.StatType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Representation of a Stat that is used by a character.
 */
public class Stat {
  private static final Logger logger = LoggerFactory.getLogger(Stat.class);
  private final StatType type;
  private final int baseValue; // Usually set to 0
  private final int offset; // Usually set to 1
  private int abilityPointsUsed; // Negative values are possible
  private int miscellaneous; // For other stat-affecting things such as items
  private boolean levelStat; // True when stat can be leveled else false

  /**
   * Constructor of Stat.
   *
   * @param statDescriptor The descriptor of a Stat
   */
  public Stat(StatDescriptor statDescriptor) {
    logger.info("Constructor is called. Parameter: statDescriptor = " + statDescriptor);
    this.type = statDescriptor.getStatType();
    this.baseValue = statDescriptor.getBaseValue();
    this.offset = statDescriptor.getOffset();
    setAbilityPointsUsed(statDescriptor.getAbilityPointsUsed());
    setMiscellaneous(statDescriptor.getMiscellaneous());
    setLevelStat(statDescriptor.isLevelStat());
  }

  /**
   * Returns the total value of a Stat by adding the product of the ability points
   * used times the offset to the base value of the stat.
   *
   * @return The total value of the Stat
   */
  public int getTotalValue() {
    logger.info("getTotalValue() is called");
    return getBaseValue() + getAbilityPointsUsed() * getOffset() + getMiscellaneous();
  }

  /**
   * Increases the ability point by one.
   */
  public void addAbilityPoint() {
    logger.info("addAbilityPoint() is called");
    setAbilityPointsUsed(getAbilityPointsUsed() + 1);
  }

  /**
   * Decreases the ability point by one.
   */
  public void removeAbilityPoint() {
    logger.info("removeAbilityPoint() is called");
    setAbilityPointsUsed(getAbilityPointsUsed() - 1);
  }

  public StatType getType() {
    return type;
  }

  public int getBaseValue() {
    return baseValue;
  }

  public int getAbilityPointsUsed() {
    return abilityPointsUsed;
  }

  public void setAbilityPointsUsed(int abilityPointsUsed) {
    this.abilityPointsUsed = abilityPointsUsed;
  }

  public int getOffset() {
    return offset;
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
    return "Stat Type: " + getType()
            + "\nBase Value: " + getBaseValue()
            + "\nOffset: " + getOffset()
            + "\nAbility Points Used: " + getAbilityPointsUsed()
            + "\nMiscellaneous: " + getMiscellaneous()
            + "\nLevel Stat: " + isLevelStat();
  }
}
