package de.hhn.it.devtools.components.ttrpgsheets;

import de.hhn.it.devtools.apis.ttrpgsheets.StatDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.StatType;

/**
 * Representation of a Stat that is used by a character.
 *
 * @author Tristan Nußbaum, Lukas Fahrner, Gabriel Schmidlin Kitzig
 * @version 1
 */
public class Stat {
  private StatType type;
  private int baseValue; // Usually set to 0
  private int offset; // Usually set to 1
  private int abilityPointsUsed; // Negative values are possible

  /**
   * Constructor of Stat.
   *
   * @param statDescriptor The descriptor of a Stat
   */
  public Stat(StatDescriptor statDescriptor) {
    setType(statDescriptor.getStatType());
    setBaseValue(statDescriptor.getBaseValue());
    setOffset(statDescriptor.getOffset());
    setAbilityPointsUsed(statDescriptor.getAbilityPointsUsed());
  }

  /**
   * Returns the total value of a Stat by adding the product of the ability points
   * used times the offset to the base value of the stat.
   *
   * @return The total value of the Stat
   */
  public int getTotalValue() {
    return getBaseValue() + getAbilityPointsUsed() * getOffset();
  }

  /**
   * Increases the ability point by one.
   */
  public void addAbilityPoint() {
    setAbilityPointsUsed(getAbilityPointsUsed() + 1);
  }

  /**
   * Decreases the ability point by one.
   */
  public void removeAbilityPoint() {
    setAbilityPointsUsed(getAbilityPointsUsed() - 1);
  }

  public StatType getType() {
    return type;
  }

  public void setType(StatType type) {
    this.type = type;
  }

  public int getBaseValue() {
    return baseValue;
  }

  public void setBaseValue(int baseValue) {
    this.baseValue = baseValue;
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

  public void setOffset(int offset) {
    this.offset = offset;
  }

  @Override
  public String toString() {
    return "Stat Type: " + getType()
            + "\nBase Value: " + getBaseValue()
            + "\nOffset: " + getOffset()
            + "\nAbility Points Used: " + getAbilityPointsUsed();
  }
}
