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

/**
 * The default implementation of a Character Sheet.
 */
public class DefaultCharacterSheet implements CharacterSheet {
  private CharacterSheetListener listener;
  private Description[] descriptions;
  private Stat[] stats;
  private Dice dice;

  /**
   * Constructor of the default Character Sheet.
   *
   * @param listener The callback listener for the Character Sheet
   * @param characterDescriptor The Descriptor of the Character Sheet
   */
  public DefaultCharacterSheet(CharacterSheetListener listener,
                               CharacterDescriptor characterDescriptor) {
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
    Stat[] stats = new Stat[statDescriptors.length];
    for (int i = 0; i < stats.length; i++) {
      stats[i] = new Stat(statDescriptors[i]);
    }
    return stats;
  }

  @Override
  public void addCallback(CharacterSheetListener listener) throws IllegalArgumentException {
    setListener(listener);
  }

  @Override
  public void unwrapCharacter(CharacterDescriptor characterDescriptor) {
    setDescriptions(convertDescDescriptorsToDescriptions(characterDescriptor.getDescriptions()));
    setStats(convertStatDescriptorsToStats(characterDescriptor.getStats()));
    setDice(new Dice(characterDescriptor.getDice()));
  }

  @Override
  public CharacterDescriptor wrapCharacter() {
    return null;
  }

  @Override
  public void incrementStat(StatType stat, OriginType origin) throws IllegalArgumentException {

  }

  @Override
  public void incrementStat(StatType stat, OriginType origin, int amount)
          throws IllegalArgumentException {

  }

  @Override
  public void decrementStat(StatType stat, OriginType origin) throws IllegalArgumentException {

  }

  @Override
  public void decrementStat(StatType stat, OriginType origin, int amount)
          throws IllegalArgumentException {

  }

  @Override
  public StatDescriptor getStatDescriptor(StatType stat) {
    return null;
  }

  @Override
  public void changeDescription(DescriptionType description, String text)
          throws IllegalArgumentException {

  }

  @Override
  public DescriptionDescriptor getDescriptionDescriptor(DescriptionType description) {
    return null;
  }

  @Override
  public void rollDice() throws NullPointerException {

  }

  @Override
  public void changeDiceType(DiceType dice) throws IllegalArgumentException {

  }

  @Override
  public DiceDescriptor getDiceDescriptor() {
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
            + "Descriptions: " + Arrays.toString(getDescriptions())
            + "Stats: " + Arrays.toString(getStats())
            + "Dice: " + getDice();
  }
}
