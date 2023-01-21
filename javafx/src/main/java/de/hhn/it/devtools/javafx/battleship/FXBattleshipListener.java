package de.hhn.it.devtools.javafx.battleship;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;

public class FXBattleshipListener implements BattleshipListener {

    SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(FXBattleshipListener.class);
    BattleshipService service;

    public FXBattleshipListener(){
        this.service = (CmpBattleshipService) singletonAttributeStore.getAttribute("Battleship.service");
    }

    /**
     * Informs the listener that the game has changed its state.
     *
     * @param state current state of the game
     */
    @Override
    public void newState(GameState state) {
        logger.info("listener - newState: {}", state);


    }

    /**
     * Informs the listener that a new field was created
     *
     * @param createdField
     */
    @Override
    public void newFieldCreated(Field createdField) {
        logger.info("listener - newFieldCreated: {}", createdField);

    }

    /**
     * Informs the listener that it's possible to place the ship at the current location
     *
     * @param ship
     * @param possible true if possible
     */
    @Override
    public void outputPlacingPossible(Ship ship, boolean possible) {
        logger.info("listener - outputPlacingPossible: ship = {}, possible = {}", ship, possible);

    }

    /**
     * Informs the listener that a ship was successfully placed
     *
     * @param ship
     */
    @Override
    public void outputShipPlaced(Ship ship) {
        logger.info("listener - outputShipPlaced: {}", ship);
/*
        Ship ship = shipsleft.getShipSelected();

        // if place ship is selected
        if (!shipsleft.placeMode && !shipsleft.isHorizontal) {

            if(!shipsleft.getShipSelected().getIsVertical()){
                try {
                    service.rotateShip(player,ship);
                } catch (IllegalPositionException | IllegalGameStateException | IllegalShipStateException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }

            try {
                service.placeShip(player, shipsleft.getShipSelected(), i1, k1);
                updateField();
                shipsleft.setShipSelected(null);
                shipsleft.resetStylesSelectShips();

                if(!player.hasUnplacedShipsLeft()){
                    shipsleft.startFiring.setVisible(true);
                }


            } catch (IllegalPositionException | IllegalGameStateException | IllegalShipStateException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

        if (!shipsleft.placeMode && shipsleft.isHorizontal){

            if(shipsleft.getShipSelected().getIsVertical()) {
                try {
                    service.rotateShip(player, ship);
                } catch (IllegalPositionException | IllegalGameStateException | IllegalShipStateException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
            try {
                service.placeShip(player, shipsleft.getShipSelected(), i1, k1);
                updateField();
                shipsleft.setShipSelected(null);
                shipsleft.resetStylesSelectShips();

                if(!player.hasUnplacedShipsLeft()){
                    shipsleft.startFiring.setVisible(true);
                }

            } catch (IllegalPositionException | IllegalGameStateException | IllegalShipStateException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }w
        }


        // if unplace ship is selected
        if (shipsleft.placeMode) {

            ship = player.getShipField().getShipsOnField(i1, k1);

            if (ship != null) {
                try {
                    service.unPlace(player, player.getShipField().getShipsOnField(i1, k1));
                    updateField();
                    shipsleft.startFiring.setVisible(false);
                } catch (IllegalGameStateException e) {
                    e.printStackTrace();
                }

            }
            else;

        }
    }

 */
    }

    /**
     * Informs the listener that a ship is not placed anymore and can be moved
     *
     * @param ship
     */
    @Override
    public void outputShipMovable(Ship ship) {
        logger.info("listener - outputShipMovable: {}", ship);

    }

    /**
     * Informs the listener that a bombing was (not)successful
     *
     * @param bombedPosition
     * @param successful     false if no ship on the bombedPosition and if the ship part was already bombed
     */
    @Override
    public void outputBombingSuccessful(Position bombedPosition, boolean successful) {
        logger.info("listener - outputBombingSuccessful: bombedPosition = {}, successful = {}", bombedPosition, successful);

    }

    /**
     * Informs the listener that the player or the computer has won
     *
     * @param playerWon true if player won, false if computer won
     */
    @Override
    public void outputWinner(boolean playerWon) {
        logger.info("listener - outputWinner: playerWon = {}", playerWon);

    }
}
