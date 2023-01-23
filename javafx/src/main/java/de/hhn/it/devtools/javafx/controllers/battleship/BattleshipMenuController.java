package de.hhn.it.devtools.javafx.controllers.battleship;

import de.hhn.it.devtools.apis.battleship.GameState;
import de.hhn.it.devtools.apis.battleship.IllegalGameStateException;
import de.hhn.it.devtools.apis.battleship.IllegalPositionException;
import de.hhn.it.devtools.apis.battleship.IllegalShipStateException;
import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;
import de.hhn.it.devtools.javafx.battleship.Game;
import de.hhn.it.devtools.javafx.controllers.Controller;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import javafx.fxml.FXML;

public class BattleshipMenuController extends Controller {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(BattleshipMenuController.class);
  Game game;
  SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
  CmpBattleshipService service;

  @FXML
  void create5x5() {

    logger.info("Creating 5x5 Game Field");

    service = new CmpBattleshipService();
    singletonAttributeStore.setAttribute("Battleship.service", service);
    service.setCurrentGameState(GameState.PREGAME);
    game = new Game(5);
    try {
      service.createFields(5);
    } catch (IllegalGameStateException | IllegalShipStateException | IllegalPositionException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void create10x10() {
    logger.info("Creating 5x5 Game Field");

    service = new CmpBattleshipService();
    singletonAttributeStore.setAttribute("Battleship.service", service);
    service.setCurrentGameState(GameState.PREGAME);

    game = new Game(10);
    try {
      service.createFields(10);
    } catch (IllegalGameStateException | IllegalShipStateException | IllegalPositionException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void create15x15() {

    logger.info("Creating 5x5 Game Field");

    service = new CmpBattleshipService();
    singletonAttributeStore.setAttribute("Battleship.service", service);
    service.setCurrentGameState(GameState.PREGAME);

    game = new Game(15);
    try {
      service.createFields(15);
    } catch (IllegalGameStateException | IllegalShipStateException | IllegalPositionException e) {
      e.printStackTrace();
    }
  }

}
