package de.hhn.it.devtools.components.ttrpgsheets;

import de.hhn.it.devtools.apis.ttrpgsheets.CharacterDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.CharacterSheet;
import de.hhn.it.devtools.apis.ttrpgsheets.CharacterSheetListener;
import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionType;
import de.hhn.it.devtools.apis.ttrpgsheets.DiceDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DiceType;
import de.hhn.it.devtools.apis.ttrpgsheets.OriginType;
import de.hhn.it.devtools.apis.ttrpgsheets.StatDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.StatType;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The default implementation of a Character Sheet.
 */
public class DefaultCharacterSheet implements CharacterSheet {

  private static final Logger logger = LoggerFactory.getLogger(DefaultCharacterSheet.class);
  private CharacterSheetListener listener;
  private Description[] descriptions;
  private Stat[] stats;
  private Dice dice;

  /**
   * Constructor of the default Character Sheet.
   *
   * @param characterDescriptor The Descriptor of the Character Sheet
   */
  public DefaultCharacterSheet(CharacterDescriptor characterDescriptor) {
    logger.info("Constructor : characterDescriptor = {}", characterDescriptor);
    unwrapCharacter(characterDescriptor);
  }

  /**
   * Converts the Description Descriptors into Description Objects.
   *
   * @param descriptionDescriptors The Descriptors that will be converted
   * @return The converted Descriptions
   */
  private Description[] convertDescDescriptorsToDescriptions(
          DescriptionDescriptor[] descriptionDescriptors) {
    logger.info("convertDescDescriptorsToDescriptions : descriptionDescriptors = {}",
            Arrays.toString(descriptionDescriptors));
    Description[] descriptions = new Description[descriptionDescriptors.length];
    for (int i = 0; i < descriptions.length; i++) {
      descriptions[i] = new Description(descriptionDescriptors[i]);
    }
    return descriptions;
  }

  /**
   * Converts the Stat Descriptors into Stat Objects.
   *
   * @param statDescriptors The Descriptors that will be converted
   * @return The converted Stats
   */
  private Stat[] convertStatDescriptorsToStats(StatDescriptor[] statDescriptors) {
    logger.info("convertStatDescriptorsToStats : statDescriptors = {}",
            Arrays.toString(statDescriptors));
    Stat[] stats = new Stat[statDescriptors.length];
    for (int i = 0; i < stats.length; i++) {
      stats[i] = new Stat(statDescriptors[i]);
    }
    return stats;
  }

  /**
   * Handler if incrementStat is called with a negative amount.
   * Method calls decrementStat with the absolute value of amount
   *
   * @param statType the Type of Stat to increment
   * @param origin   the origin of the change
   * @param amount   the amount the Stat changes
   */
  private void negativeIncrementHandler(StatType statType, OriginType origin, int amount) {
    if (amount == Integer.MIN_VALUE) {
      amount = Integer.MAX_VALUE;
    }
    decrementStat(statType, origin, Math.abs(amount));
  }

  /**
   * Checks whether the sum of two numbers overflows and returns the respective result.
   *
   * @param addend1 the first number which is added
   * @param addend2 the second number which is added
   * @return the sum if no underflow occurs else Integer.MAX_VALUE
   */
  private int overflowCheck(int addend1, int addend2) {
    return addend1 + addend2 > addend1 ? addend1 + addend2 : Integer.MAX_VALUE;
  }

  /**
   * Handler if decrementStat is called with a negative amount.
   * Method calls incrementStat with the absolute value of amount
   *
   * @param statType the Type of Stat to increment
   * @param origin   the origin of the change
   * @param amount   the amount the Stat changes
   */
  private void negativeDecrementHandler(StatType statType, OriginType origin, int amount) {
    if (amount == Integer.MIN_VALUE) {
      amount = Integer.MAX_VALUE;
    }
    incrementStat(statType, origin, Math.abs(amount));
  }

  /**
   * Checks whether the difference of two numbers underflows and returns the respective result.
   *
   * @param minuend the number from which is subtracted from
   * @param subtrahend the number which subtracts
   * @return the difference if no underflow occurs else Integer.MIN_VALUE
   */
  private int underflowCheck(int minuend, int subtrahend) {
    return minuend - subtrahend < minuend ? minuend - subtrahend : Integer.MIN_VALUE;
  }

  /**
   * Returns the Stat of the given type.
   *
   * @param statType The specific StatType
   * @return The Stat of given StatType
   */
  private Stat getStatOfType(StatType statType) {
    logger.info("getStatOfType : statType = {}", statType);
      for (Stat stat : stats) {
        if (stat.getType() == statType) {
          return stat;
        }
      }
    return null;
  }

  @Override
  public void addCallback(CharacterSheetListener listener) throws IllegalArgumentException {
    logger.info("addCallback : listener = {}", listener);
    if (listener == null) {
      throw new IllegalArgumentException("Listener is null");
    }
    this.listener = listener;
  }

  @Override
  public void unwrapCharacter(CharacterDescriptor characterDescriptor) {
    logger.info("unwrapCharacter : characterDescriptor = {}", characterDescriptor);
    descriptions = convertDescDescriptorsToDescriptions(characterDescriptor.getDescriptions());
    stats = convertStatDescriptorsToStats(characterDescriptor.getStats());
    dice = new Dice(characterDescriptor.getDice());
  }

  @Override
  public CharacterDescriptor wrapCharacter() {
    logger.info("wrapCharacter : no params");
    DescriptionDescriptor[] descDescriptors = new DescriptionDescriptor[descriptions.length];
    for (int i = 0; i < DescriptionType.values().length; i++) {
      descDescriptors[i] = getDescriptionDescriptor(DescriptionType.values()[i]);
    }
    StatDescriptor[] statDescriptors = new StatDescriptor[stats.length];
    for (int i = 0; i < StatType.values().length; i++) {
      statDescriptors[i] = getStatDescriptor(StatType.values()[i]);
    }
    return new CharacterDescriptor(descDescriptors, statDescriptors, getDiceDescriptor());
  }

  @Override
  public void incrementStat(StatType statType, OriginType origin) throws IllegalArgumentException {
    logger.info("incrementStat : statType = {}, origin = {}", statType, origin);
    incrementStat(statType, origin, 1);
  }

  @Override
  public void incrementStat(StatType statType, OriginType origin, int amount)
          throws IllegalArgumentException {
    logger.info("incrementStat : statType = {}, origin = {}, amount = {}",
            statType, origin, amount);
    Stat stat = getStatOfType(statType);

    if (origin == OriginType.LEVEL_POINT && !stat.isLevelStat()) {
      throw new IllegalArgumentException("Cannot change level of Stat of this Type");
    }

    if (amount < 0) {
      negativeIncrementHandler(statType, origin, amount);
    } else if (origin == OriginType.LEVEL_POINT) {
      stat.setAbilityPointsUsed(overflowCheck(stat.getAbilityPointsUsed(), amount));
    } else {
      stat.setMiscellaneous(overflowCheck(stat.getMiscellaneous(), amount));
    }
    listener.statChanged(stat.toStatDescriptor()); // Callback
  }

  @Override
  public void decrementStat(StatType statType, OriginType origin) throws IllegalArgumentException {
    logger.info("decrementStat : statType = {}, origin = {}", statType, origin);
    decrementStat(statType, origin, 1);
  }

  @Override
  public void decrementStat(StatType statType, OriginType origin, int amount)
          throws IllegalArgumentException {
    logger.info("decrementStat : statType = {}, origin = {}, amount = {}",
            statType, origin, amount);
    Stat stat = getStatOfType(statType);

    if (origin == OriginType.LEVEL_POINT && !stat.isLevelStat()) {
      throw new IllegalArgumentException("Cannot change level of Stat of this Type");
    }

    if (amount < 0) {
      negativeDecrementHandler(statType, origin, amount);
    } else if (origin == OriginType.LEVEL_POINT) {
      stat.setAbilityPointsUsed(underflowCheck(stat.getAbilityPointsUsed(), amount));
    } else {
      stat.setMiscellaneous(underflowCheck(stat.getMiscellaneous(), amount));
    }
    listener.statChanged(stat.toStatDescriptor()); // Callback
  }

  @Override
  public int getStatDisplayValue(StatType statType) throws IllegalArgumentException {
    logger.info("getStatDisplayValue : statType = {}", statType);
    Stat stat = getStatOfType(statType);
    if (stat == null) {
      throw new IllegalArgumentException("No Stat of this type is found");
    }
    return stat.getTotalValue();
  }

  @Override
  public StatDescriptor getStatDescriptor(StatType statType) {
    logger.info("getStatDescriptor : statType = {}", statType);
    Stat stat = getStatOfType(statType);
    if (stat == null) {
      return null;
    }
    return stat.toStatDescriptor();
  }

  @Override
  public void changeDescription(DescriptionType descriptionType, String text)
          throws IllegalArgumentException {
    logger.info("changeDescription : descriptionType = {}, text = {}", descriptionType, text);
    if (descriptionType == null || text == null) {
      throw new IllegalArgumentException("One or both arguments are null");
    }
    for (Description description : descriptions) {
      if (description.getType() == descriptionType) {
        description.setDescription(text);
        listener.descriptionChanged(description.toDescriptionDescriptor()); // Callback
      }
    }
  }

  @Override
  public DescriptionDescriptor getDescriptionDescriptor(DescriptionType descriptionType) {
    logger.info("getDescriptionDescriptor : descriptionType = {}", descriptionType);
    for (Description description : descriptions) {
      if (description.getType() == descriptionType) {
        return description.toDescriptionDescriptor();
      }
    }
    return null;
  }

  @Override
  public int rollDice() throws NullPointerException {
    logger.info("rollDice : no params");
    int result = dice.nextRoll();
    listener.diceChanged(dice.toDiceDescriptor()); // Callback
    return result;
  }

  @Override
  public void changeDiceType(DiceType diceType) throws IllegalArgumentException {
    logger.info("changeDiceType : dice = {}", diceType);
    dice.changeSize(diceType);
    listener.diceChanged(dice.toDiceDescriptor()); // Callback
  }

  @Override
  public DiceDescriptor getDiceDescriptor() {
    logger.info("getDiceDescriptor : no params");
    return dice.toDiceDescriptor();
  }

  @Override
  public String toString() {
    return "DefaultCharacterSheet: [CharacterSheetListener: " + listener
            + ", Descriptions: " + Arrays.toString(descriptions)
            + ", Stats: " + Arrays.toString(stats)
            + ", Dice: " + dice + "]";
  }
}
