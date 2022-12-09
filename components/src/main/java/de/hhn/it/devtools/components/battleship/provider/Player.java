package de.hhn.it.devtools.components.battleship.provider;

import de.hhn.it.devtools.apis.battleship.Owner;

public class Player extends Owner {
    AttackField pAttackField;
    ShipField pShipField;


    public void setAttackField(AttackField attackField){
        this.pAttackField = attackField;

    }


    public void setShipfield(ShipField shipField){
        this.pShipField = shipField;
    }


    public AttackField getPAttackField(){
        return this.pAttackField;
    }


    public ShipField getPShipField(){
        return this.pShipField;
    }


}
