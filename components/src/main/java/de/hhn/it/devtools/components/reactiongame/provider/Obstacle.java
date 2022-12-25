package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.reactiongame.ObstacleListener;

public interface Obstacle extends GameObject {
    void addCallback(ObstacleListener listener);

    void removeCallback(ObstacleListener listener);
}
