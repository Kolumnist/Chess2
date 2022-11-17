package de.hhn.it.devtools.components.ttrpgsheets;

import de.hhn.it.devtools.apis.ttrpgsheets.CharacterSheet;
import de.hhn.it.devtools.apis.ttrpgsheets.CharacterSheetListener;
import de.hhn.it.devtools.apis.ttrpgsheets.CharacterDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionType;
import de.hhn.it.devtools.apis.ttrpgsheets.StatDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.StatType;
import de.hhn.it.devtools.apis.ttrpgsheets.DiceType;
import de.hhn.it.devtools.apis.ttrpgsheets.OriginType;

import java.util.Arrays;

public class DefaultCharacterSheet implements CharacterSheet {
  // VARIABLES
  CharacterSheetListener listener;
  Description[] descriptions;
  Stat[] stats;
  Dice dice;

  // CONSTRUCTORS
  public DefaultCharacterSheet(CharacterSheetListener listener, CharacterDescriptor characterDescriptor) {
    addCallback(listener);
    unwrapCharacter(characterDescriptor);
  }

  // CONSTRUCTOR METHODS
  private Description[] convertDescDescriptorsToDescriptions(DescriptionDescriptor[] descriptionDescriptors) {
    Description[] descriptions = new Description[descriptionDescriptors.length];
    for (int i = 0; i < descriptions.length; i++) {
      descriptions[i] = new Description(descriptionDescriptors[i]);
    }
    return descriptions;
  }

  private Stat[] convertStatDescriptorsToStats(StatDescriptor[] statDescriptors) {
    Stat[] stats = new Stat[statDescriptors.length];
    for (int i = 0; i < stats.length; i++) {
      stats[i] = new Stat(statDescriptors[i]);
    }
    return stats;
  }

  // INTERFACE METHODS

  @Override
  public void addCallback(CharacterSheetListener listener) throws IllegalArgumentException {
    this.listener = listener;
  }

  @Override
  public void unwrapCharacter(CharacterDescriptor characterDescriptor) {
    this.descriptions = convertDescDescriptorsToDescriptions(characterDescriptor.getDescriptions());
    this.stats = convertStatDescriptorsToStats(characterDescriptor.getStats());
    this.dice = new Dice(characterDescriptor.getDice());
  }

  @Override
  public CharacterDescriptor wrapCharacter() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void incrementStat(StatType stat, OriginType origin) throws IllegalArgumentException {
    // TODO Auto-generated method stub

  }

  @Override
  public void incrementStat(StatType stat, OriginType origin, int amount) throws IllegalArgumentException {
    // TODO Auto-generated method stub

  }

  @Override
  public void decrementStat(StatType stat, OriginType origin) throws IllegalArgumentException {
    // TODO Auto-generated method stub

  }

  @Override
  public void decrementStat(StatType stat, OriginType origin, int amount) throws IllegalArgumentException {
    // TODO Auto-generated method stub

  }

  @Override
  public StatDescriptor getStat(StatType stat) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void changeDescription(DescriptionType description, String text) throws IllegalArgumentException {
    // TODO Auto-generated method stub

  }

  @Override
  public DescriptionDescriptor getDescription(DescriptionType description) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void rollDice() throws NullPointerException {
    // TODO Auto-generated method stub

  }

  @Override
  public void changeDiceType(DiceType dice) throws IllegalArgumentException {
    // TODO Auto-generated method stub

  }

  // GETTER and SETTER
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

  public Dice getDice() { //TODO - Pain weil Dopplung
    return this.dice;
  }

  public void setDice(Dice dice) {
    this.dice = dice;
  }

  // TO STRING
  @Override
  public String toString() {
    return "CharacterSheetListener: " + getListener()
            + "Descriptions: " + Arrays.toString(getDescriptions())
            + "Stats: " + Arrays.toString(getStats())
            + "Dice: " + getDice();
  }
}
