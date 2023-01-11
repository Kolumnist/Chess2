package de.hhn.it.devtools.apis.memory;

public interface TimerListener {

  /**
   * Informs the listener that the time has been updated.
   *
   * @param time the current time of the Timer
   */
  void currentTime(int time);
}
