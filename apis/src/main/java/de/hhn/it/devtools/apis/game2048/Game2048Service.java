package de.hhn.it.devtools.apis.game2048;


import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * This Game2048Service is an interface for the Backend of this application.
 * The Methods residing in the JavaFx directory can call these methods.
 */
public interface Game2048Service {


  /**
   * Adds a listener to updates on the state of the game 2048.
   *
   * @param listener object implementing the listener interface.
   * @throws IllegalParameterException if the listener is a null reference.
   */
  void addCallback(Game2048Listener listener) throws IllegalParameterException;

  /**
   * Adds a listener to updates on the state of the game 2048.
   *
   * @param listener listener to be removed.
   * @throws IllegalParameterException if the listener is a null reference.
   */
  void removeCallback(Game2048Listener listener) throws IllegalParameterException;
}