package de.hhn.it.devtools.components.battleship.provider;

import de.hhn.it.devtools.apis.battleship.Field;
import de.hhn.it.devtools.apis.battleship.Owner;

public class AttackField extends Field {

    private boolean[][] bombMarker;

    public AttackField(int size, Owner owner) {
        super(size, owner);
        bombMarker = new boolean[size][size];
    }
}
