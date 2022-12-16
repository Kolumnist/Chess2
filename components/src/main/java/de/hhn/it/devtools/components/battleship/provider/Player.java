package de.hhn.it.devtools.components.battleship.provider;

import de.hhn.it.devtools.apis.battleship.Owner;

public class Player extends Owner {
    AttackField pAttackField;
    ShipField pShipField;
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(Player.class);


    public void setAttackField(AttackField attackField){
        logger.debug("setAttackField: - {}", attackField);
        this.pAttackField = attackField;

    }


    public void setShipfield(ShipField shipField){
        logger.debug("setShipField: - {}", shipField);
        this.pShipField = shipField;
    }


    public AttackField getPAttackField(){
        return this.pAttackField;
    }


    public ShipField getPShipField(){
        return this.pShipField;
    }


}
