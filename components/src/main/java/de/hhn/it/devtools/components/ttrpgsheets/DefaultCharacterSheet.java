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
   * @param listener            The callback listener for the Character Sheet
   * @param characterDescriptor The Descriptor of the Character Sheet
   */
  public DefaultCharacterSheet(CharacterSheetListener listener,
                               CharacterDescriptor characterDescriptor) {
    logger.info("Constructor : listener = {}, characterDescriptor = {}", listener,
            characterDescriptor);
    addCallback(listener);
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

  @Override
  public void addCallback(CharacterSheetListener listener) throws IllegalArgumentException {
    logger.info("addCallback : listener = {}", listener);
    setListener(listener);
  }

  @Override
  public void unwrapCharacter(CharacterDescriptor characterDescriptor) {
    logger.info("unwrapCharacter : characterDescriptor = {}", characterDescriptor);
    setDescriptions(convertDescDescriptorsToDescriptions(characterDescriptor.getDescriptions()));
    setStats(convertStatDescriptorsToStats(characterDescriptor.getStats()));
    setDice(new Dice(characterDescriptor.getDice()));
  }

  @Override
  public CharacterDescriptor wrapCharacter() {
    logger.info("wrapCharacter : no params");
    DescriptionDescriptor[] descDescriptors = new DescriptionDescriptor[getDescriptions().length];
    for (int i = 0; i < DescriptionType.values().length; i++) {
      descDescriptors[i] = getDescriptionDescriptor(DescriptionType.values()[i]);
    }
    StatDescriptor[] statDescriptors = new StatDescriptor[getStats().length];
    for (int i = 0; i < StatType.values().length; i++) {
      statDescriptors[i] = getStatDescriptor(StatType.values()[i]);
    }
    return new CharacterDescriptor(descDescriptors, statDescriptors, getDiceDescriptor());
  }

  @Override
  public void incrementStat(StatType statType, OriginType origin) throws IllegalArgumentException {
    Stat stat = getStatOfType(statType);
    if (origin == OriginType.LEVEL_POINT && !stat.isLevelStat()) {
      throw new IllegalArgumentException("Cannot change level of Stat of this Type");
    }
    if (origin == OriginType.LEVEL_POINT) {
      if (stat.getAbilityPointsUsed() < Integer.MAX_VALUE) {
        stat.addAbilityPoint();
        getListener().statChanged(stat.toStatDescriptor()); // Callback
      }
    } else {
      if (stat.getMiscellaneous() < Integer.MAX_VALUE) {
        stat.setMiscellaneous(stat.getMiscellaneous() + 1);
        getListener().statChanged(stat.toStatDescriptor()); // Callback
      }
    }
  }

  @Override
  public void incrementStat(StatType statType, OriginType origin, int amount)
          throws IllegalArgumentException {
    Stat stat = getStatOfType(statType);
    if (origin == OriginType.LEVEL_POINT && !stat.isLevelStat()) {
      throw new IllegalArgumentException("Cannot change level of Stat of this Type");
    }
    if (amount < 0) {
      if (amount == Integer.MIN_VALUE) {
        decrementStat(statType, origin, Integer.MAX_VALUE);
        return;
      }
      decrementStat(statType, origin, Math.abs(amount));
      return;
    }
    if (origin == OriginType.LEVEL_POINT) {
      if (stat.getAbilityPointsUsed() + amount > stat.getAbilityPointsUsed()) {
        stat.setAbilityPointsUsed(stat.getAbilityPointsUsed() + amount);
        getListener().statChanged(stat.toStatDescriptor()); // Callback
      } else {
        stat.setAbilityPointsUsed(Integer.MAX_VALUE);
        getListener().statChanged(stat.toStatDescriptor()); // Callback
      }
    } else {
      if (stat.getMiscellaneous() + amount > stat.getMiscellaneous()) {
        stat.setMiscellaneous(stat.getMiscellaneous() + amount);
        getListener().statChanged(stat.toStatDescriptor()); // Callback
      } else {
        stat.setMiscellaneous(Integer.MAX_VALUE);
        getListener().statChanged(stat.toStatDescriptor()); // Callback
      }
    }
  }

  @Override
  public void decrementStat(StatType statType, OriginType origin) throws IllegalArgumentException {
    Stat stat = getStatOfType(statType);
    if (origin == OriginType.LEVEL_POINT && !stat.isLevelStat()) {
      throw new IllegalArgumentException("Cannot change level of Stat of this Type");
    }
    if (origin == OriginType.LEVEL_POINT) {
      if (stat.getAbilityPointsUsed() > Integer.MIN_VALUE) {
        stat.removeAbilityPoint();
        getListener().statChanged(stat.toStatDescriptor()); // Callback
      }
    } else {
      if (stat.getMiscellaneous() > Integer.MIN_VALUE) {
        stat.setMiscellaneous(stat.getMiscellaneous() - 1);
        getListener().statChanged(stat.toStatDescriptor()); // Callback
      }
    }
  }

  @Override
  public void decrementStat(StatType statType, OriginType origin, int amount)
          throws IllegalArgumentException {
    Stat stat = getStatOfType(statType);
    if (origin == OriginType.LEVEL_POINT && !stat.isLevelStat()) {
      throw new IllegalArgumentException("Cannot change level of Stat of this Type");
    }
    if (amount < 0) {
      if (amount == Integer.MIN_VALUE) {
        incrementStat(statType, origin, Integer.MAX_VALUE);
        return;
      }
      incrementStat(statType, origin, Math.abs(amount));
      return;
    }
    if (origin == OriginType.LEVEL_POINT) {
      if (stat.getAbilityPointsUsed() - amount < stat.getAbilityPointsUsed()) {
        stat.setAbilityPointsUsed(stat.getAbilityPointsUsed() - amount);
        getListener().statChanged(stat.toStatDescriptor()); // Callback
      } else {
        stat.setAbilityPointsUsed(Integer.MIN_VALUE);
        getListener().statChanged(stat.toStatDescriptor()); // Callback
      }
    } else {
      if (stat.getMiscellaneous() - amount < stat.getMiscellaneous()) {
        stat.setMiscellaneous(stat.getMiscellaneous() - amount);
        getListener().statChanged(stat.toStatDescriptor()); // Callback
      } else {
        stat.setMiscellaneous(Integer.MIN_VALUE);
        getListener().statChanged(stat.toStatDescriptor()); // Callback
      }
    }
  }

  @Override
  public int getStatDisplayValue(StatType statType) throws IllegalArgumentException {
    Stat stat = getStatOfType(statType);
    if (stat == null) {
      throw new IllegalArgumentException("No Stat of this type is found");
    }
    return stat.getTotalValue();
  }

  @Override
  public StatDescriptor getStatDescriptor(StatType statType) {
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
    for (Description description : getDescriptions()) {
      if (description.getType() == descriptionType) {
        description.setDescription(text);
        getListener().descriptionChanged(description.toDescriptionDescriptor()); // Callback
      }
    }
  }

  @Override
  public DescriptionDescriptor getDescriptionDescriptor(DescriptionType descriptionType) {
    logger.info("getDescriptionDescriptor : descriptionType = {}", descriptionType);
    for (Description description : getDescriptions()) {
      if (description.getType() == descriptionType) {
        return description.toDescriptionDescriptor();
      }
    }
    return null;
  }

  @Override
  public int rollDice() throws NullPointerException {
    logger.info("rollDice : no params");
    int result = getDice().nextRoll();
    getListener().diceChanged(getDice().toDiceDescriptor()); // Callback
    return result;
  }

  @Override
  public void changeDiceType(DiceType dice) throws IllegalArgumentException {
    logger.info("changeDiceType : dice = {}", dice);
    getDice().changeSize(dice);
    getListener().diceChanged(getDice().toDiceDescriptor()); // Callback
  }

  @Override
  public DiceDescriptor getDiceDescriptor() {
    logger.info("getDiceDescriptor : no params");
    return getDice().toDiceDescriptor();
  }

  /**
   * Returns the Stat of the given type.
   *
   * @param statType The specific StatType
   * @return The Stat of given StatType
   */
  public Stat getStatOfType(StatType statType) {
    if (getStats() != null) {
      for (Stat stat : getStats()) {
        if (stat.getType() == statType) {
          return stat;
        }
      }
    }
    return null;
  }

  public CharacterSheetListener getListener() {
    return listener;
  }

  public void setListener(CharacterSheetListener listener) {
    this.listener = listener;
  }

  public Description[] getDescriptions() {
    return descriptions;
  }

  public void setDescriptions(Description[] descriptions) {
    this.descriptions = descriptions;
  }

  public Stat[] getStats() {
    return stats;
  }

  public void setStats(Stat[] stats) {
    this.stats = stats;
  }

  public Dice getDice() {
    return dice;
  }

  public void setDice(Dice dice) {
    this.dice = dice;
  }

  @Override
  public String toString() {
    return "CharacterSheetListener: " + getListener()
                   + "\nDescriptions: " + Arrays.toString(getDescriptions())
                   + "\nStats: " + Arrays.toString(getStats())
                   + "\nDice: " + getDice();
  }
}
