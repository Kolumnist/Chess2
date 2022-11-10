package de.hhn.it.devtools.apis.duckhunt;

/**
 * Enum to represent the different states a Duck can be in.
 */
public enum DuckState {
  /** Standard state when a Duck is flying. */
  FLYING,
  /** The State of a Duck when it has been shot (in this moment) and is scarred. */
  SCARRED,
  /** The State of a Duck when it is Falling down after being shot. */
  FALLING,
  /** The State of a Duck when a timer runs out and the ducks fly away. */
  FLYAWAY,
  /** The State of a Duck when a Duck is dead and fell out of screen. */
  DEAD
}
