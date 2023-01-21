package de.hhn.it.devtools.javafx.duckhunt;

import de.hhn.it.devtools.apis.duckhunt.DuckHuntListener;
import de.hhn.it.devtools.apis.duckhunt.DucksInfo;
import de.hhn.it.devtools.apis.duckhunt.GameInfo;
import de.hhn.it.devtools.apis.duckhunt.IllegalDuckIdException;
import de.hhn.it.devtools.apis.duckhunt.IllegalDuckPositionException;
import de.hhn.it.devtools.apis.duckhunt.IllegalGameInfoException;
import de.hhn.it.devtools.components.duckhunt.DuckHunt;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DuckHuntGameController implements DuckHuntListener {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DuckHuntScreenController.class);

  public static final String SCREEN = "game.screen";

  private DuckHunt game;
  private Parent root;
  private Stage stage;

  public DuckHuntGameController() {
    try {
      root = FXMLLoader.load(getClass().getClassLoader().getResource("/fxml/duckhunt/DuckHuntGame.fxml"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    stage = new Stage();
    stage.setTitle("DuckHunt");
    stage.setScene(new Scene(root, 1280, 540));
    stage.show();
  }

  @Override
  public void newState(GameInfo gameInfo) throws IllegalGameInfoException {

  }

  @Override
  public void newDuckPosition(DucksInfo duckPosition) throws IllegalDuckPositionException {

  }

  @Override
  public void duckHit(int id) throws IllegalDuckIdException {

  }
}
