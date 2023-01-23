package de.hhn.it.devtools.components.battleship.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.hhn.it.devtools.apis.battleship.BattleshipService;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test adjustSoundVolume")
public class TestAdjustSoundVolume {

  CmpBattleshipService bsService = new CmpBattleshipService();
  BattleshipService bs = bsService;

  // Bad Case

  @Test
  @DisplayName("Test adjustSoundVolume with negative value")
  public void adjustSoundVolumeNegative() {
    // IllegalArgumentException should be thrown
    assertThrows(IllegalArgumentException.class,
        () -> bs.adjustSoundVolume(-5));
  }

  // Good Case

  @Test
  @DisplayName("Test adjustSoundVolume successfully")
  public void adjustSoundVolumeSuccessful() {
    bs.adjustSoundVolume(50);
    assertEquals(50, bsService.getGameVolume());
  }

}
