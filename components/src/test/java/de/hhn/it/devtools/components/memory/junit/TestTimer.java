package de.hhn.it.devtools.components.memory.junit;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.*;
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
    Thread.sleep(5000);
    assertTrue(listener.times.size() > 0);
  }

  @Test
  @DisplayName("Test registration of two listeners which both get notified.")
  void checkRegistrationTwoListeners() throws IllegalParameterException, InterruptedException {
    SimpleTimerListener listener0 = new SimpleTimerListener();
    SimpleTimerListener listener1 = new SimpleTimerListener();
    timer.addCallback(listener0);
    timer.addCallback(listener1);
    timer.startTime();
    Thread.sleep(5000);
    assertAll(
        () -> assertTrue(listener0.times.size() > 0),
        () -> assertTrue(listener1.times.size() > 0)
    );
  }

  @Test
  @DisplayName("Register one listener, get time, remove listener, provoke new time")
  void registerAndRemoveOneListener() throws IllegalParameterException, InterruptedException {
    SimpleTimerListener listener = new SimpleTimerListener();
    timer.addCallback(listener);
    timer.startTime();
    Thread.sleep(5000);
    int lenght0 = listener.times.size();
    timer.removeCallback(listener);
    Thread.sleep(5000);
    int lenght1 = listener.times.size();
    assertAll(
        () -> assertTrue(lenght0 > 0),
        () -> assertTrue(lenght1 > 0),
        () -> assertEquals(lenght0, lenght1)
    );
  }

  @Test
  @DisplayName("The same listener cannot be registered twice.")
  void tryRegisterSameListenerTwice() throws IllegalParameterException {
    SimpleTimerListener listener = new SimpleTimerListener();
    timer.addCallback(listener);
    IllegalParameterException exception = assertThrows(IllegalParameterException.class,
        () -> timer.addCallback(listener));
  }

  @Test
  @DisplayName("Listener which is not registered cannot be removed.")
  void tryRemoveNotRegisteredListener() throws  IllegalParameterException {
    SimpleTimerListener listener = new SimpleTimerListener();
    IllegalParameterException exception = assertThrows(IllegalParameterException.class,
        () -> timer.removeCallback(listener));
  }

  @Test
  @DisplayName("Null references cannot be registered as listener.")
  void tryRegisterNullReferencesAsListener() {
    IllegalParameterException exception = assertThrows(IllegalParameterException.class,
        () -> timer.addCallback(null));
  }

  @Test
  @DisplayName("Null references cannot be removed as listener.")
  void tryRemoveNullReferenceAsListener() {
    IllegalParameterException exception = assertThrows(IllegalParameterException.class,
        () -> timer.removeCallback(null));
  }

  /**
   * Inner class as a TimerListener.
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
