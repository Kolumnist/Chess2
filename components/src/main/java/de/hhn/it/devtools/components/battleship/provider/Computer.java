package de.hhn.it.devtools.components.battleship.provider;

import de.hhn.it.devtools.apis.battleship.Owner;

public class Computer extends Owner {

    AttackField cAttackField;
    ShipField cShipField;
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(Computer.class);


    public void setAttackField(AttackField attackField){
        logger.debug("setAttackField: - {}", attackField);
        this.cAttackField = attackField;
    }


    public void setShipfield(ShipField shipField){
        logger.debug("setShipField: - {}", shipField);
        this.cShipField = shipField;
    }


    public AttackField getCAttackField() {
        return cAttackField;
    }


    public ShipField getCShipField(){
        return cShipField;
    }
}
