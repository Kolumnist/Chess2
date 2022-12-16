package de.hhn.it.devtools.apis.battleship;

import java.util.ArrayList;

public class Owner {
    protected ArrayList<Ship> ownedShips = new ArrayList<>();


    public void setOwnedShips(Ship addShip){
        ownedShips.add(addShip);
    }

}
