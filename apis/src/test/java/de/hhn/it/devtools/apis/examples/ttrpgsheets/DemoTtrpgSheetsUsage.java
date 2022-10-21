package de.hhn.it.devtools.apis.examples.ttrpgsheets;

import de.hhn.it.devtools.apis.ttrpgsheets.CharacterSheet;
import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionType;
import de.hhn.it.devtools.apis.ttrpgsheets.DiceType;
import de.hhn.it.devtools.apis.ttrpgsheets.OriginType;
import de.hhn.it.devtools.apis.ttrpgsheets.StatType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the Demo Usage of a Ttrpg Character Sheet.
 * It is not runnable because there is no implementation yet.
 */
public class DemoTtrpgSheetsUsage {
  private static final Logger logger = LoggerFactory.getLogger(DemoTtrpgSheetsUsage.class);

  /**
   * The main Method for the Demo.
   *
   * @param args The Arguments are not used
   */
  public static void main(String[] args) {
    CharacterSheet characterSheet = null;

    // Starting to change the description of the character
    logger.info(">>> Setup character description");
    characterSheet.changeDescription(DescriptionType.PLAYERNAME, "Herbert");
    characterSheet.changeDescription(DescriptionType.CHARACTERNAME, "Sylas Thatcher");
    characterSheet.changeDescription(DescriptionType.NICKNAME, "Sy");
    characterSheet.changeDescription(DescriptionType.AGE, "34");
    characterSheet.changeDescription(DescriptionType.RACE, "Human");
    characterSheet.changeDescription(DescriptionType.HEIGHT, "1.85m");
    characterSheet.changeDescription(DescriptionType.WEIGHT, "87kg");
    characterSheet.changeDescription(DescriptionType.SKINCOLOR, "White");
    characterSheet.changeDescription(DescriptionType.HAIRCOLOR, "Black");
    characterSheet.changeDescription(DescriptionType.EYECOLOR, "Blue-Gray");
    characterSheet.changeDescription(DescriptionType.CHARACTERCLASS, "Assassin");
    characterSheet.changeDescription(DescriptionType.OTHER, "After 5 years held back in chains,"
            + " Sylas wants nothing more but revenge.");

    // By default, you start at level 1
    // this equals to 5 Level Points (default) + 1 Level Point (Level 1) (= 6) that can be spent
    logger.info(">>> Setup initial character stats");
    characterSheet.incrementStat(StatType.MAX_HEALTH, OriginType.LEVEL_POINT);
    characterSheet.incrementStat(StatType.AGILITY, OriginType.LEVEL_POINT);
    // User wanted more Agility, so he presses the Button twice
    characterSheet.incrementStat(StatType.AGILITY, OriginType.LEVEL_POINT);
    characterSheet.incrementStat(StatType.DEXTERITY, OriginType.LEVEL_POINT);
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT);
    // Same goes for Strength
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT);

    // Now the game begins
    // The player wants to roll a D6 to decide where to go
    logger.info(">>> Change to D6");
    characterSheet.changeDiceType(DiceType.D6);
    characterSheet.rollDice();
    //TODO logger debug Dice

    // The player wants to open the Chest he found, rolling with a D20
    logger.info(">>> Change to D20");
    characterSheet.changeDiceType(DiceType.D20);
    characterSheet.rollDice();
    //TODO logger debug Dice

    // The player successfully opened the chest and found a sword granting him +2 Strength
    logger.info(">>> Equip sword");
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.ITEM, 2);
    //TODO logger debug Stat

    // He also found a scarf, granting him +2 Defence at the cost of -1 Dexterity
    logger.info(">>> Equip scarf");
    characterSheet.incrementStat(StatType.DEFENCE, OriginType.ARMOR, 2);
    characterSheet.decrementStat(StatType.DEXTERITY, OriginType.ARMOR);
    //TODO logger debug Stat

    // Watch out! The chest activated an ancient spike trap, poking the character for 5 Damage!
    // The Defense Stat just got increased (Callback would only decrease Health by 5 - 2 = 3)
    logger.info(">>> Get 5 raw damage");
    characterSheet.decrementStat(StatType.HEALTH, OriginType.DAMAGE, 5);
    //TODO logger debug Stat

    // After killing some wild animals, the level got increased
    logger.info(">>> Get level up");
    characterSheet.incrementStat(StatType.LEVEL, OriginType.OTHER);
    //TODO logger debug Stat

    // The Player decides to level up his Dexterity
    logger.info(">>> Level up dexterity");
    characterSheet.incrementStat(StatType.DEXTERITY, OriginType.LEVEL_POINT);
    //TODO logger debug Stat

    // It starts to rain, making it hard to move or fight. -1 Agility while raining
    logger.info(">>> Rain affects agility");
    characterSheet.decrementStat(StatType.AGILITY, OriginType.EFFECT);
    //TODO logger debug stat

    // The character uses his ability to heal himself with +2 HP
    logger.info(">>> Character heals themself by 2 HP");
    characterSheet.incrementStat(StatType.HEALTH, OriginType.ABILITY, 2);
    //TODO logger debug stat
  }
}
