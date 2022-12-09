package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.reactiongame.AimTargetDescriptor;

public class AimTarget {

  /**
   * Adds an aim target to the ui.
   *
   * @param aimTarget aim target
   */
  void addAimTarget(AimTargetDescriptor aimTarget) {

  }

  /**
   * Removes the aim target with the given id.
   *
   * @param aimTargetId identifier
   */
  void removeAimTarget(int aimTargetId) {

  }

  /**
   * Player hits a target.
   *
   * @param keyPressed  key pressed by player
   * @param aimTargetId hit target
   */
  void aimTargetHit(char keyPressed, int aimTargetId) {

  }

  /**
   * Player pressed a key.
   *
   * @param key key
   * @throws IllegalStateException if state equals the current state
   */
  void keyPressed(char key) throws IllegalStateException {

  }
}
