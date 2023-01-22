package de.hhn.it.devtools.apis.reactiongame;

/**
 * Enum holding the three game states
 */
public enum GameState {
  /**
   * In the running game state, the game moves forward and spawns AimTargets and Obstacle
   */
  RUNNING,
  /**
   * In the paused game state, the game is not running and cannot spawn Shapes on the screen or
   * progress time
   */
  PAUSED,
  /**
   * In the finished game state, the run is finished and the program processes highscore
   */
  FINISHED
}
