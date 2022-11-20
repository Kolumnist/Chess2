package de.hhn.it.devtools.components.battleship.provider;

import de.hhn.it.devtools.apis.battleship.Field;
import de.hhn.it.devtools.apis.battleship.Owner;

public class ShipField extends Field {
    //Index is false if no ship is on panel
    private boolean[][] carriesShip;

    public ShipField(int size, Owner owner){
        super(size,owner);
        carriesShip = new boolean[size][size];
    }

    public boolean[][] getCarriesShip() {
        return carriesShip;
    }
}
