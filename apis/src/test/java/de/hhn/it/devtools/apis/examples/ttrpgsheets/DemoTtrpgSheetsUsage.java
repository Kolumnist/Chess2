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

    public static void main(String[] args) {
        CharacterSheet characterSheet = null;

        // Starting to change the description of the character
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
        characterSheet.changeDescription(DescriptionType.OTHER, "After 5 years held back in chains, Sylas wants" +
                " nothing more but revenge.");

        // By default, you start at level 1
        // this equals to 5 Level Points (default) + 1 Level Point (Level 1) (= 6) that can be spent
        characterSheet.incrementStat(StatType.MAX_HEALTH, Origin.LEVEL_POINT);
        characterSheet.incrementStat(StatType.AGILITY, Origin.LEVEL_POINT); // User wanted more Agility, so he
        characterSheet.incrementStat(StatType.AGILITY, Origin.LEVEL_POINT); // presses the Button twice
        characterSheet.incrementStat(StatType.DEXTERITY, Origin.LEVEL_POINT);
        characterSheet.incrementStat(StatType.STRENGTH, Origin.LEVEL_POINT); // Same goes for Strength
        characterSheet.incrementStat(StatType.STRENGTH, Origin.LEVEL_POINT);

        // Now the game begins
        // The player wants to roll a D6 to decide where to go
        characterSheet.changeDiceType(DiceType.D6);
        characterSheet.rollDice();
        // The player wants to open the Chest he found, rolling with a D20
        characterSheet.changeDiceType(DiceType.D20);
        characterSheet.rollDice();
        // How lucky! The player successfully opened the chest and found a sword granting him +2 Strength
        characterSheet.incrementStat(StatType.STRENGTH, Origin.ITEM, 2);
        // He also found a scarf, granting him +2 Defence at the cost of -1 Dexterity
        characterSheet.incrementStat(StatType.DEFENCE, Origin.ARMOR, 2);
        characterSheet.decrementStat(StatType.DEXTERITY, Origin.ARMOR);
        // Watch out! The chest activated an ancient spike trap, poking the character for 5 Damage!
        // Luckily the Defense Stat just got increased (Callback would only decrease Health by 5 - 2 = 3)
        characterSheet.decrementStat(StatType.HEALTH, Origin.DAMAGE, 5);
        // After killing some wild animals, the level got increased
        characterSheet.incrementStat(StatType.LEVEL, Origin.OTHER);
        // The Player decides to level up his Dexterity
        characterSheet.incrementStat(StatType.DEXTERITY, Origin.LEVEL_POINT);
        // It starts to rain, making it hard to move or fight. -1 Agility while raining
        characterSheet.decrementStat(StatType.AGILITY, Origin.EFFECT);
        // The character uses his ability to heal himself with +2 HP
        characterSheet.incrementStat(StatType.HEALTH, Origin.ABILITY, 2);
    }
}
