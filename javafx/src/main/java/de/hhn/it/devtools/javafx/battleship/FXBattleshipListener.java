package de.hhn.it.devtools.javafx.battleship;

import de.hhn.it.devtools.apis.battleship.*;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;

public class FXBattleshipListener implements BattleshipListener {

    SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(FXBattleshipListener.class);
    CmpBattleshipService service;
    Game game;

    public FXBattleshipListener(Game game){
        this.service = (CmpBattleshipService) singletonAttributeStore.getAttribute("Battleship.service");
        this.game = game;
    }

    @Override
    public void outputWinner(Player potentialWinner) {
        if (service.checkWon(potentialWinner)) {
            OutputWinner outputWinner = new OutputWinner(potentialWinner);
        }
    }

    @Override
    public void updateUnplacedShips() {
        int carrierCount = game.player.countShipType(ShipType.CARRIER);
        int battleshipCount = game.player.countShipType(ShipType.BATTLESHIP);
        int cruiserCount = game.player.countShipType(ShipType.CRUISER);
        int submarineCount = game.player.countShipType(ShipType.SUBMARINE);
        int destroyerCount = game.player.countShipType(ShipType.DESTROYER);

        game.shipsleft.carrier.setText("Carrier, Length: 5, Ships Left: " + carrierCount);
        game.shipsleft.battleship.setText("Battleship, Length: 4, Ships Left: " + battleshipCount);
        game.shipsleft.cruiser.setText("Cruiser, Length: 3, Ships Left: " + cruiserCount);
        game.shipsleft.submarine.setText("Submarine, Length: 3, Ships Left: " + submarineCount);
        game.shipsleft.destroyer.setText("Destroyer, Length: 2, Ships Left: " + destroyerCount);

    }

    @Override
    public void updateFiringShotsButton() {
        game.shipsleft.startFiring.setVisible(false);
    }

    @Override
    public void allShipsPlaced() {
            game.shipsleft.startFiring.setVisible(true);
    }

    @Override
    public void updateField() {

            for (int i = 0; i < game.sizeGrid ; i++) {
                for (int k = 0; k < game.sizeGrid ; k++) {


                    PanelState computerFieldStates =  service.getPlayer().getAttackField().getPanelMarker(i,k);
                    if(computerFieldStates ==PanelState.HIT){
                        game.buttonsUpper[k][i].getStyleClass().add("hit");
                    }
                    if (computerFieldStates.equals(PanelState.MISSED)){
                        game.buttonsUpper[k][i].getStyleClass().add("missed");
                    }

                    PanelState playerFieldStates =  service.getComputer().getAttackField().getPanelMarker(i,k);
                    if(playerFieldStates ==PanelState.HIT){
                        game.buttonsLower[k][i].getStyleClass().add("hit");
                    }

                    if (playerFieldStates.equals(PanelState.MISSED)){
                        game.buttonsLower[k][i].getStyleClass().add("missed");
                    }

                    Ship ship = service.getPlayer().getShipField().getShipsOnField(i,k);
                    if( ship == null) {
                        game.buttonsLower[k][i].getStyleClass().removeAll("carrier", "battleship", "cruiser", "submarine", "destroyer");
                        continue;
                    }

                    switch (ship.getShipType()){

                        case CARRIER:
                            game.buttonsLower[k][i].getStyleClass().add("carrier");
                            break;

                        case BATTLESHIP:
                            game.buttonsLower[k][i].getStyleClass().add("battleship");
                            break;

                        case CRUISER:
                            game.buttonsLower[k][i].getStyleClass().add("cruiser");
                            break;

                        case SUBMARINE:
                            game.buttonsLower[k][i].getStyleClass().add("submarine");
                            break;

                        case DESTROYER:
                            game.buttonsLower[k][i].getStyleClass().add("destroyer");
                            break;

                        default: break;
                    }

                }
            }
        }
    @Override
    public void resetShipSelected() {
        game.shipsleft.setShipSelected(null);
        game.shipsleft.resetStylesSelectShips();
    }

}
