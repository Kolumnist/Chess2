package de.hhn.it.devtools.components.memory.provider;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.memory.TimerDescriptor;
import de.hhn.it.devtools.apis.memory.TimerListener;

/**
 * Interface of the timer.
 */
public interface Timer {

  void addCallback(TimerListener listener) throws IllegalParameterException;

  void removeCallback(TimerListener listener) throws IllegalParameterException;

  TimerDescriptor getTimer();
}
