package de.hhn.it.devtools.components.ttrpgsheets.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.hhn.it.devtools.apis.ttrpgsheets.StatDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.StatType;
import de.hhn.it.devtools.components.ttrpgsheets.Stat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestStatGoodCases {
  private static final org.slf4j.Logger logger
          = org.slf4j.LoggerFactory.getLogger(TestStatGoodCases.class);

  private static Stat[] stats;

  @BeforeAll
  static void setup() {
    logger.info("setup() is called");
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
  void getTotalValue() {
    logger.info("getTotalValue() is called");
    for (Stat stat : stats) {
      switch (stat.getType()) {
        case MAX_HEALTH -> assertEquals(30, stat.getTotalValue());
        case HEALTH -> assertEquals(28, stat.getTotalValue());
        case LEVEL -> assertEquals(1, stat.getTotalValue());
        case STRENGTH -> assertEquals(8, stat.getTotalValue());
        default -> assertEquals(0, stat.getTotalValue());
      }
    }
  }

  @Test
  void addAbilityPoint() {
    logger.info("addAbilityPoint() is called");
    for (Stat stat : stats) {
      stat.addAbilityPoint();
      switch (stat.getType()) {
        case MAX_HEALTH -> {
          assertEquals(1, stat.getAbilityPointsUsed());
          assertEquals(35, stat.getTotalValue());
        }
        case HEALTH -> {
          assertEquals(0, stat.getAbilityPointsUsed());
          assertEquals(0, stat.getTotalValue());
        }
        case LEVEL -> {
          assertEquals(0, stat.getAbilityPointsUsed());
          assertEquals(1, stat.getTotalValue());
        }
        case STRENGTH -> {
          assertEquals(1, stat.getAbilityPointsUsed());
          assertEquals(9, stat.getTotalValue());
        }
        default -> {
          assertEquals(1, stat.getAbilityPointsUsed());
          assertEquals(1, stat.getTotalValue());
        }
      }
    }
  }

  @Test
  void removeAbilityPoint() {
    logger.info("removeAbilityPoint() is called");
    for (Stat stat : stats) {
      stat.removeAbilityPoint();
      switch (stat.getType()) {
        case MAX_HEALTH -> {
          assertEquals(1, stat.getAbilityPointsUsed());
          assertEquals(30, stat.getTotalValue());
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
          assertEquals(0, stat.getAbilityPointsUsed());
          assertEquals(8, stat.getTotalValue());
        }
        default -> {
          assertEquals(0, stat.getAbilityPointsUsed());
          assertEquals(0, stat.getTotalValue());
        }
      }
    }
  }

  @Test
  void testToString() {
    logger.info("testToString() is called");
    for (Stat stat : stats) {
      stat.addAbilityPoint();
      switch (stat.getType()) {
        case LEVEL -> assertEquals("Stat Type: LEVEL\nBase Value: 1\nOffset: 1\n"
                + "Ability Points Used: 0\nMiscellaneous: 0\nLevel Stat: false", stat.toString());
        case HEALTH -> assertEquals("Stat Type: HEALTH\nBase Value: 30\nOffset: 0\n"
                + "Ability Points Used: 0\nMiscellaneous: -2\nLevel Stat: false", stat.toString());
        case AGILITY -> assertEquals("Stat Type: AGILITY\nBase Value: 0\nOffset: 1\n"
                + "Ability Points Used: 0\nMiscellaneous: 0\nLevel Stat: true", stat.toString());
        case DEFENCE -> assertEquals("Stat Type: DEFENCE\nBase Value: 0\nOffset: 1\n"
                + "Ability Points Used: 0\nMiscellaneous: 0\nLevel Stat: true", stat.toString());
        case STRENGTH -> assertEquals("Stat Type: STRENGTH\nBase Value: 0\nOffset: 1\n"
                + "Ability Points Used: 2\nMiscellaneous: 6\nLevel Stat: true", stat.toString());
        case DEXTERITY -> assertEquals("Stat Type: DEXTERITY\nBase Value: 0\nOffset: 1\n"
                + "Ability Points Used: 0\nMiscellaneous: 0\nLevel Stat: true", stat.toString());
        case MAX_HEALTH -> assertEquals("Stat Type: MAX_HEALTH\nBase Value: 30\nOffset: 5\n"
                + "Ability Points Used: 0\nMiscellaneous: 0\nLevel Stat: true", stat.toString());
      }
    }
  }
}