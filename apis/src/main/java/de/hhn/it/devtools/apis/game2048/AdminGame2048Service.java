package de.hhn.it.devtools.apis.game2048;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

public interface AdminGame2048Service {
  /**
   * Is important if multiple Objects of this class are used.
   * Removes a Game2048Service by an ID.
   *
   * @throws IllegalParameterException if there is no Existing GameService for given id
   */
  void removeGameServiceById(int id) throws IllegalParameterException;

  /**
   * Is important if multiple Objects of this class are used.
   * Adds a GameService by an ID.
   *
   * @throws IllegalParameterException if there is no Existing GameService for given id
   */
  void addGameServiceById(int id) throws IllegalParameterException;
}