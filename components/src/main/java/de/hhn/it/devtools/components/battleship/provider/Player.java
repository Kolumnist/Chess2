package de.hhn.it.devtools.components.battleship.provider;

import de.hhn.it.devtools.apis.battleship.Field;
import de.hhn.it.devtools.apis.battleship.Owner;

public class Player {
    protected Field attackField;
    protected Field shipField;
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(Player.class);


    public void setAttackField(Field attackField){
        logger.debug("setAttackField: - {}", attackField);
        this.attackField = attackField;

    }


    public void setShipfield(Field shipField){
        logger.debug("setShipField: - {}", shipField);
        this.shipField = shipField;
    }


    public Field getAttackField(){
        return this.attackField;
    }


    public Field getShipField(){
        return this.shipField;
    }


}
