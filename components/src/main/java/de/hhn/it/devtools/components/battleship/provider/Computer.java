package de.hhn.it.devtools.components.battleship.provider;

import de.hhn.it.devtools.apis.battleship.Owner;

public class Computer extends Owner {

    AttackField cAttackField;
    ShipField cShipField;


    public void setAttackField(AttackField attackField){
        this.cAttackField = attackField;
    }


    public void setShipfield(ShipField shipField){
        this.cShipField = shipField;
    }


    public AttackField getCAttackField() {
        return cAttackField;
    }


    public ShipField getCShipField(){
        return cShipField;
    }
}
