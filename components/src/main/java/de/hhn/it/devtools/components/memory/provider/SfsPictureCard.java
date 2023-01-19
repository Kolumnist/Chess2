package de.hhn.it.devtools.components.memory.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.PictureCardDescriptor;
import de.hhn.it.devtools.apis.memory.PictureCardListener;
import de.hhn.it.devtools.apis.memory.State;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Simple implementation of a PictureCard interface.
 */

public class SfsPictureCard implements PictureCard {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SfsPictureCard.class);
  private static AtomicInteger idCounter = new AtomicInteger(0);
  private List<PictureCardListener> listeners;
  private PictureCardDescriptor descriptor;

  /**
   * Constructor.
   *
   * @param descriptor the pictureCardDescriptor of the Card
   */
  public SfsPictureCard(PictureCardDescriptor descriptor) {
    logger.debug("Constructor - {}", descriptor);
    listeners = new ArrayList<>();
    descriptor.setId(idCounter.getAndIncrement());
    this.descriptor = descriptor;
  }

  /**
   * Resets the ID counter.
   */
  public static void resetIdCounter() {
    idCounter.set(0);
  }

  @Override
  public void turnCard() throws IllegalStateException {
    if (descriptor.getState().equals(State.MATCHED)) {
      throw new IllegalStateException("PictureCard is already matched.");
    }

    if (descriptor.getState().equals(State.VISIBLE)) {
      descriptor.setState(State.HIDDEN);
      notifyListeners(State.HIDDEN);
    } else {
      descriptor.setState(State.VISIBLE);
      notifyListeners(State.VISIBLE);
    }
  }

  @Override
  public void addCallback(PictureCardListener listener) throws IllegalParameterException {
    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    }

    if (listeners.contains(listener)) {
      throw new IllegalParameterException("Listener already registered.");
    }

    listeners.add(listener);
  }

  @Override
  public void removeCallback(PictureCardListener listener) throws IllegalParameterException {
    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    }

    if (!listeners.contains(listener)) {
      throw new IllegalParameterException("Listener is not registered:" + listener);
    }

    listeners.remove(listener);
  }

  @Override
  public PictureCardDescriptor getPictureCard() {
    return descriptor;
  }

  @Override
  public void matchCard() {
    descriptor.setState(State.MATCHED);
    notifyListeners(State.MATCHED);
  }

  /**
   * Notifies the listener.
   *
   * @param state state that should be notified to the listener
   */
  private void notifyListeners(State state) {
    listeners.forEach((listener) -> listener.currentState(state));
  }
}
