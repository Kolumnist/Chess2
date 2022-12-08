package de.hhn.it.devtools.apis.memory;

public class TimerDescriptor {

  public int time;
  public boolean run;

  public TimerDescriptor() {
    this.time = 0;
  }


  /**
   * Returns the time.
   *
   * @return current time
   */
  public int getTime() {
    return time;
  }

  /**
   * Sets the time.
   * @param time the timer should be set to
   */
  public void setTime(int time) {
    this.time = time;
  }

  /**
   * Resets the time.
   */
  public void resetTime() {
    this.time = 0;
  }

  /**
   * Starts the timer.
   */
  public void startTime() {
    run = true;
  }

  /**
   * Stops the timer.
   */
  public void stopTime() {
    run = false;
  }

}
