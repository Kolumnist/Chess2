package de.hhn.it.devtools.components.ttrpgsheets.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.hhn.it.devtools.apis.ttrpgsheets.StatDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.StatType;
import de.hhn.it.devtools.components.ttrpgsheets.Stat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class StatTest {
  private static final org.slf4j.Logger logger
          = org.slf4j.LoggerFactory.getLogger(StatTest.class);

  private static Stat[] stats;

  @BeforeEach
  void setupObjects() {
    logger.info("setupObjects() is called");
    StatDescriptor[] statDescriptors = new StatDescriptor[StatType.values().length];
    stats = new Stat[StatType.values().length];
    for (int i = 0; i < stats.length; i++) {
      switch (StatType.values()[i]) {
        case MAX_HEALTH -> statDescriptors[i]
                = new StatDescriptor(StatType.values()[i], 30, 5, 0, 0, true);
        case HEALTH -> statDescriptors[i]
                = new StatDescriptor(StatType.values()[i], 30, 0, 0, -2, false);
        case LEVEL -> statDescriptors[i]
                = new StatDescriptor(StatType.values()[i], 1, 0, 0, 0, false);
        case STRENGTH -> statDescriptors[i]
                = new StatDescriptor(StatType.values()[i], 0, 1, 2, 6, true);
        default -> statDescriptors[i] = new StatDescriptor(StatType.values()[i], 0, 1, 0, 0, true);
      }
      stats[i] = new Stat(statDescriptors[i]);
    }
  }

  @Test
  void constructorTest() {
    logger.info("constructorTest() is called");
    assertThrows(IllegalArgumentException.class, () -> new Stat(null));
  }

  @Test
  void getTotalValueTest() {
    logger.info("getTotalValueTest() is called");
    for (Stat stat : stats) {
      switch (stat.getType()) {
        case MAX_HEALTH -> assertEquals(30, stat.getTotalValue());
        case HEALTH -> assertEquals(28, stat.getTotalValue());
        case LEVEL -> assertEquals(1, stat.getTotalValue());
        case STRENGTH -> assertEquals(8, stat.getTotalValue());
        default -> assertEquals(0, stat.getTotalValue());
      }
    }

    Stat edgeStat = stats[3];
    edgeStat.setMiscellaneous(Integer.MAX_VALUE);
    assertEquals(Integer.MAX_VALUE, edgeStat.getTotalValue());
    edgeStat.setAbilityPointsUsed(Integer.MAX_VALUE);
    assertEquals(Integer.MAX_VALUE, edgeStat.getTotalValue());
    edgeStat.setMiscellaneous(0);
    assertEquals(Integer.MAX_VALUE, edgeStat.getTotalValue());

    edgeStat.setMiscellaneous(Integer.MIN_VALUE);
    assertEquals(-1, edgeStat.getTotalValue());

    edgeStat.setAbilityPointsUsed(0);
    assertEquals(Integer.MIN_VALUE, edgeStat.getTotalValue());
    edgeStat.setAbilityPointsUsed(Integer.MIN_VALUE);
    assertEquals(Integer.MIN_VALUE, edgeStat.getTotalValue());
    edgeStat.setMiscellaneous(0);
    assertEquals(Integer.MIN_VALUE, edgeStat.getTotalValue());

    edgeStat.setMiscellaneous(Integer.MAX_VALUE);
    assertEquals(-1, edgeStat.getTotalValue());
  }

  @Test
  void addAbilityPointTest() {
    logger.info("addAbilityPointTest() is called");
    for (Stat stat : stats) {
      stat.addAbilityPoint();
      switch (stat.getType()) {
        case MAX_HEALTH -> {
          assertEquals(1, stat.getAbilityPointsUsed());
          assertEquals(35, stat.getTotalValue());
        }
        case HEALTH -> {
          assertEquals(0, stat.getAbilityPointsUsed());
          assertEquals(28, stat.getTotalValue());
        }
        case LEVEL -> {
          assertEquals(0, stat.getAbilityPointsUsed());
          assertEquals(1, stat.getTotalValue());
        }
        case STRENGTH -> {
          assertEquals(3, stat.getAbilityPointsUsed());
          assertEquals(9, stat.getTotalValue());
        }
        default -> {
          assertEquals(1, stat.getAbilityPointsUsed());
          assertEquals(1, stat.getTotalValue());
        }
      }
    }

    Stat edgeStat = stats[3];
    edgeStat.setAbilityPointsUsed(Integer.MAX_VALUE);
    edgeStat.addAbilityPoint();
    assertEquals(Integer.MAX_VALUE, edgeStat.getAbilityPointsUsed());

    edgeStat.setAbilityPointsUsed(Integer.MIN_VALUE);
    edgeStat.addAbilityPoint();
    assertEquals(Integer.MIN_VALUE + 1, edgeStat.getAbilityPointsUsed());
  }

  @Test
  void removeAbilityPointTest() {
    logger.info("removeAbilityPointTest() is called");
    for (Stat stat : stats) {
      stat.removeAbilityPoint();
      switch (stat.getType()) {
        case MAX_HEALTH -> {
          assertEquals(-1, stat.getAbilityPointsUsed());
          assertEquals(25, stat.getTotalValue());
        }
        case HEALTH -> {
          assertEquals(0, stat.getAbilityPointsUsed());
          assertEquals(28, stat.getTotalValue());
        }
        case LEVEL -> {
          assertEquals(0, stat.getAbilityPointsUsed());
          assertEquals(1, stat.getTotalValue());
        }
        case STRENGTH -> {
          assertEquals(1, stat.getAbilityPointsUsed());
          assertEquals(7, stat.getTotalValue());
        }
        default -> {
          assertEquals(-1, stat.getAbilityPointsUsed());
          assertEquals(-1, stat.getTotalValue());
        }
      }
    }

    Stat edgeStat = stats[3];
    edgeStat.setAbilityPointsUsed(Integer.MAX_VALUE);
    edgeStat.removeAbilityPoint();
    assertEquals(Integer.MAX_VALUE - 1, edgeStat.getAbilityPointsUsed());

    edgeStat.setAbilityPointsUsed(Integer.MIN_VALUE);
    edgeStat.removeAbilityPoint();
    assertEquals(Integer.MIN_VALUE, edgeStat.getAbilityPointsUsed());
  }

  @Test
  void toStringTest() {
    logger.info("toStringTest() is called");
    for (Stat stat : stats) {
      switch (stat.getType()) {
        case LEVEL -> assertEquals("""
                Stat Type: LEVEL
                Base Value: 1
                Offset: 0
                Ability Points Used: 0
                Miscellaneous: 0
                Level Stat: false""", stat.toString());
        case HEALTH -> assertEquals("""
                Stat Type: HEALTH
                Base Value: 30
                Offset: 0
                Ability Points Used: 0
                Miscellaneous: -2
                Level Stat: false""", stat.toString());
        case AGILITY -> assertEquals("""
                Stat Type: AGILITY
                Base Value: 0
                Offset: 1
                Ability Points Used: 0
                Miscellaneous: 0
                Level Stat: true""", stat.toString());
        case DEFENCE -> assertEquals("""
                Stat Type: DEFENCE
                Base Value: 0
                Offset: 1
                Ability Points Used: 0
                Miscellaneous: 0
                Level Stat: true""", stat.toString());
        case STRENGTH -> assertEquals("""
                Stat Type: STRENGTH
                Base Value: 0
                Offset: 1
                Ability Points Used: 2
                Miscellaneous: 6
                Level Stat: true""", stat.toString());
        case DEXTERITY -> assertEquals("""
                Stat Type: DEXTERITY
                Base Value: 0
                Offset: 1
                Ability Points Used: 0
                Miscellaneous: 0
                Level Stat: true""", stat.toString());
        case MAX_HEALTH -> assertEquals("""
                Stat Type: MAX_HEALTH
                Base Value: 30
                Offset: 5
                Ability Points Used: 0
                Miscellaneous: 0
                Level Stat: true""", stat.toString());
        default -> {
        }
      }
    }
  }
}