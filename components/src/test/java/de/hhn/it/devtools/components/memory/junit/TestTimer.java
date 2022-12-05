package de.hhn.it.devtools.components.memory.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.*;
import de.hhn.it.devtools.components.memory.provider.SfsPictureCard;
import de.hhn.it.devtools.components.memory.provider.SfsTimer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestTimer {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TestTimer.class);

  private SfsTimer timer;

  @BeforeEach
  void setup() {
    timer = new SfsTimer(new TimerDescriptor());
  }

  @Test
  @DisplayName("Test registration of callback listener.")
  void CheckSuccessfulRegistrationOfCallback() throws IllegalParameterException,
      InterruptedException {
    TestTimer.SimpleTimerListener listener = new SimpleTimerListener();
    timer.addCallback(listener);
    timer.startTime();
    Thread.sleep(2000);
    assertTrue(listener.times.size() > 0);
  }

  /**
   * Inner class as a PictureCardListener.
   */
  class SimpleTimerListener implements TimerListener {

    public List<Integer> times;

    public SimpleTimerListener() {times = new ArrayList<>(); }


    @Override
    public void currentTime(int time) {
      times.add(time);
    }
  }
}
