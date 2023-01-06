package de.hhn.it.devtools.components.ttrpgsheets;

import de.hhn.it.devtools.apis.ttrpgsheets.StatDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.StatType;
import java.math.BigInteger;
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
  private final boolean levelStat; // True when stat can be leveled else false

  /**
   * Constructor of Stat.
   *
   * @param statDescriptor The descriptor of a Stat
   */
  public Stat(StatDescriptor statDescriptor) throws IllegalArgumentException {
    logger.info("Constructor : statDescriptor = {}", statDescriptor);
    if (statDescriptor == null) {
      throw new IllegalArgumentException("Parameter is null");
    }
    this.type = statDescriptor.getStatType();
    this.baseValue = statDescriptor.getBaseValue();
    this.offset = statDescriptor.getOffset();
    this.abilityPointsUsed = statDescriptor.getAbilityPointsUsed();
    this.miscellaneous = statDescriptor.getMiscellaneous();
    this.levelStat = statDescriptor.isLevelStat();
  }

  /**
   * Returns the total value of a Stat by adding the product of the ability points
   * used times the offset to the base value of the stat.
   * Calculation is as follows:
   * Base Value + Ability Points Used * Offset + Miscellaneous
   *
   * @return The total value of the Stat
   */
  public int getTotalValue() {
    logger.info("getTotalValue : no params");
    BigInteger totalValue = BigInteger.valueOf(baseValue)
            .add(BigInteger.valueOf(this.abilityPointsUsed)
                    .multiply(BigInteger.valueOf(offset)))
            .add(BigInteger.valueOf(this.miscellaneous));
    if (totalValue.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
      return Integer.MAX_VALUE;
    }
    if (totalValue.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0) {
      return Integer.MIN_VALUE;
    }
    return totalValue.intValue();
  }

  /**
   * Increases the ability point by one.
   */
  public void addAbilityPoint() {
    logger.info("addAbilityPoint : no params");
    if (levelStat && this.abilityPointsUsed < Integer.MAX_VALUE) {
      this.abilityPointsUsed++;
    }
  }

  /**
   * Decreases the ability point by one.
   */
  public void removeAbilityPoint() {
    logger.info("removeAbilityPoint : no params");
    if (levelStat && this.abilityPointsUsed > Integer.MIN_VALUE) {
      this.abilityPointsUsed--;
    }
  }

  /**
   * Converts this stat to a {@link StatDescriptor}.
   *
   * @return the {@link StatDescriptor} of this stat
   */
  public StatDescriptor toStatDescriptor() {
    logger.info("toStatDescriptor : no params");
    return new StatDescriptor(this.type, this.baseValue, this.offset,
            this.abilityPointsUsed, this.miscellaneous, this.levelStat);
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

  @Override
  public String toString() {
    return "Stat: [Type: " + this.type
            + ", Base Value: " + this.baseValue
            + ", Offset: " + this.offset
            + ", Ability Points Used: " + this.abilityPointsUsed
            + ", Miscellaneous: " + this.miscellaneous
            + ", Level Stat: " + this.levelStat + "]";
  }
}
