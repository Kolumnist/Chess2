package de.hhn.it.devtools.components.reactiongame.provider;

import de.hhn.it.devtools.apis.reactiongame.AimTargetListener;

public interface AimTarget extends GameObject {

    void addCallback(AimTargetListener listener);

    void removeCallback(AimTargetListener listener);

}
