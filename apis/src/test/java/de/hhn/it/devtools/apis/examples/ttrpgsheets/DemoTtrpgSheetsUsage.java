package de.hhn.it.devtools.apis.examples.ttrpgsheets;

import de.hhn.it.devtools.apis.ttrpgsheets.*;
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
    CharacterDescriptor characterDescriptor = null;

    logger.info(">>> Import CharacterDescriptor");
    characterSheet.importCharacter(characterDescriptor);
    logger.debug("" + characterSheet.getCharacter());

    // change the character weight, because he ate too much cake
    logger.info(">>> Setup character description");
    characterSheet.changeDescription(DescriptionType.WEIGHT, "92kg");
    logger.debug("" + characterSheet.getDescription(DescriptionType.WEIGHT));

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
    logger.debug("" + characterSheet.getDice());

    // The player wants to open the Chest he found, rolling with a D20
    logger.info(">>> Change to D20");
    characterSheet.changeDiceType(DiceType.D20);
    characterSheet.rollDice();
    logger.debug("" + characterSheet.getDice());

    // The player successfully opened the chest and found a sword granting him +2 Strength
    logger.info(">>> Equip sword");
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.ITEM, 2);
    logger.debug("" + characterSheet.getStat(StatType.STRENGTH));

    // He also found a scarf, granting him +2 Defence at the cost of -1 Dexterity
    logger.info(">>> Equip scarf");
    characterSheet.incrementStat(StatType.DEFENCE, OriginType.ARMOR, 2);
    characterSheet.decrementStat(StatType.DEXTERITY, OriginType.ARMOR);
    logger.debug("" + characterSheet.getStat(StatType.DEFENCE));
    logger.debug("" + characterSheet.getStat(StatType.DEXTERITY));

    // Watch out! The chest activated an ancient spike trap, poking the character for 5 Damage!
    // The Defense Stat just got increased (Callback would only decrease Health by 5 - 2 = 3)
    logger.info(">>> Get 5 raw damage");
    characterSheet.decrementStat(StatType.HEALTH, OriginType.DAMAGE, 5);
    logger.debug("" + characterSheet.getStat(StatType.HEALTH));

    // After killing some wild animals, the level got increased
    logger.info(">>> Get level up");
    characterSheet.incrementStat(StatType.LEVEL, OriginType.OTHER);
    logger.debug("" + characterSheet.getStat(StatType.LEVEL));

    // The Player decides to level up his Dexterity
    logger.info(">>> Level up dexterity");
    characterSheet.incrementStat(StatType.DEXTERITY, OriginType.LEVEL_POINT);
    logger.debug("" + characterSheet.getStat(StatType.DEXTERITY));

    // It starts to rain, making it hard to move or fight. -1 Agility while raining
    logger.info(">>> Rain affects agility");
    characterSheet.decrementStat(StatType.AGILITY, OriginType.EFFECT);
    logger.debug("" + characterSheet.getStat(StatType.AGILITY));

    // The character uses his ability to heal himself with +2 HP
    logger.info(">>> Character heals themself by 2 HP");
    characterSheet.incrementStat(StatType.HEALTH, OriginType.ABILITY, 2);
    logger.debug("" + characterSheet.getStat(StatType.HEALTH));

    // The character gets exported
    logger.info(">>> Export character");
    characterSheet.exportCharacter();
    logger.debug("" + characterSheet.getCharacter());

  }
}
