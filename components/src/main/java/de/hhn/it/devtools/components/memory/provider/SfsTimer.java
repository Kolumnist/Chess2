package de.hhn.it.devtools.components.memory.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.TimerDescriptor;
import de.hhn.it.devtools.apis.memory.TimerListener;
import java.util.ArrayList;
import java.util.List;

public class SfsTimer implements Timer, Runnable {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SfsTimer.class);
  private List<TimerListener> listeners;
  private TimerDescriptor descriptor;

  public SfsTimer(TimerDescriptor descriptor) {
    logger.debug("Constructor - {}", descriptor);
    listeners = new ArrayList<>();
    this.descriptor = descriptor;
  }

  @Override
  public void addCallback(TimerListener listener) throws IllegalParameterException {
    logger.info("addCallback: listener = {}", listener);
    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    }

    if (listeners.contains(listener)) {
      throw new IllegalParameterException("Listener already registered.");
    }

    listeners.add(listener);
  }

  @Override
  public void removeCallback(TimerListener listener) throws IllegalParameterException {
    logger.info("removeCallback: listener = {}", listener);
    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    }

    if (!listeners.contains(listener)) {
      throw new IllegalParameterException("Listener is not registered:" + listener);
    }

    listeners.remove(listener);
  }


  @Override
  public TimerDescriptor getTimer() {
    return descriptor;
  }

  public void startTime() {
    descriptor.startTime();
    new Thread(this::run).start();
  }

  @Override
  public void run() {
    while (descriptor.run) {
      try {
        descriptor.time++;
        notifyListener(descriptor.time);
        Thread.sleep(1000);
      } catch (InterruptedException | IllegalParameterException e) {
        System.out.println(e);
      }
    }
  }

  /**
   * Notifies the listener
   *
   * @param time time that should be notified to the listener
   * @throws IllegalParameterException if the time does not exist
   */
  private void notifyListener(int time) throws IllegalParameterException {
    listeners.forEach((listener) -> listener.currentTime(time));
  }

}
