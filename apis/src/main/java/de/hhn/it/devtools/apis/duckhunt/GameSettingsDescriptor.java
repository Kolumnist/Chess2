package de.hhn.it.devtools.apis.duckhunt;

/**
 * Describes the games Settings.
 */
public class GameSettingsDescriptor {

  private int duckAmount;
  private int ammoAmount;

  /**
   * Standard Constructor setting default duckAmount and ammoAmount.
   */
  public GameSettingsDescriptor() {
    duckAmount = 1;
    ammoAmount = 3;
  }

  /**
   * Constructor setting given duckAmount and ammoAmount.
   */
  public GameSettingsDescriptor(int duckAmount, int ammoAmount) {
    this.duckAmount = duckAmount;
    this.ammoAmount = ammoAmount;
  }

  public int getduckAmount() {
    return duckAmount;
  }

  /**
   * Duck Amount setter with parameter range check.
   *
   * @param duckAmount the duckAmount to be set (range: 1 - 4)
   * @throws IllegalStateException is thrown when parameter volume out of range
   */
  public void setDuckAmount(int duckAmount) throws IllegalStateException {
    if (duckAmount > 6 || duckAmount <= 0) {
      throw new IllegalStateException("Duck amount not in acceptable range");
    }
    this.duckAmount = duckAmount;
  }

  public int getAmmoAmount() {
    return ammoAmount;
  }

  /**
   * AmmoAmount setter with parameter range check.
   *
   * @param ammoAmount the ammoAmount to be set (range: 1 - (maxInteger-1))
   * @throws IllegalStateException is thrown when parameter volume out of range
   */
  public void setAmmoAmount(int ammoAmount) {
    if (ammoAmount > Integer.MAX_VALUE - 1 || ammoAmount <= 0) {
      throw new IllegalStateException("Ammo amount not in acceptable range");
    }
    this.ammoAmount = ammoAmount;
  }

  @Override
  public String toString() {
    return "GameSettingsDescriptor{"
         + "duckAmount=" + duckAmount
         + ", ammoAmount=" + ammoAmount
         + '}';
  }

}
