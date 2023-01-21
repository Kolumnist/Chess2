package de.hhn.it.devtools.javafx.duckhunt;

import de.hhn.it.devtools.apis.duckhunt.*;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.components.duckhunt.DuckHunt;
import de.hhn.it.devtools.components.duckhunt.ScreenDimension;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Creates additional window with the duck hunt game running.
 */
public class DuckHuntGameController implements Initializable, DuckHuntListener {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DuckHuntScreenController.class);

  public static final String SCREEN = "game.screen";

  @FXML
  private AnchorPane anchorPane;

  @FXML
  private Button continueButton;

  @FXML
  private Button exitButton;

  @FXML
  private ImageView backgroundImage;

  @FXML
  private ImageView bushImage;

  @FXML
  private ImageView grassImage;

  @FXML
  private ImageView groundImage;

  @FXML
  private ImageView treeImage;

  private DuckHunt game;
  private Stage stage;
  private Scene scene;
  private HashMap<Integer, ImageView> ducks;
  private GameSettingsDescriptor gameSettings;
  private int currentRound = 0;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // stage creation
    DuckHuntAttributeStore duckHuntAttributeStore = DuckHuntAttributeStore.getReference();
    stage = (Stage) duckHuntAttributeStore.getAttribute("gameStage");
    scene = new Scene(anchorPane, 960, 720);
    scene.setCursor(Cursor.CROSSHAIR);
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();

    scene.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ESCAPE) {
        pauseGame();
      }
    });
    stage.setOnCloseRequest(event -> {
      System.out.println("stage close");
      stage.close();
      game.stopGame();
    });

    // game creation
    gameSettings = (GameSettingsDescriptor) duckHuntAttributeStore.getAttribute("gameSettings");

    ducks = new HashMap<>();
    for (int i = 0; i < gameSettings.getduckAmount(); i++) {
      ducks.put(i, DuckHuntImageManager.NORTHDUCK.image);
    }

    game = new DuckHunt(
        gameSettings,
        new ScreenDimension(960, 430)
    );
    try {
      game.addCallback(this);
    } catch (IllegalParameterException e) {
      throw new RuntimeException(e);
    }
    game.startGame();
    newRound();
  }

  void pauseGame() {
    if (game.getGameInfo().getState() == GameState.PAUSED) {
      continueGame(null);
    } else if (game.getGameInfo().getState() == GameState.RUNNING) {
      continueButton.getParent().setDisable(false);
      continueButton.getParent().setVisible(true);
      game.pauseGame();
    }
  }

  @FXML
  void continueGame(ActionEvent event) {
    continueButton.getParent().setDisable(true);
    continueButton.getParent().setVisible(false);
    game.continueGame();
  }

  @FXML
  void exitGame(ActionEvent event) {
    stage.fireEvent(new WindowEvent(stage,WindowEvent.WINDOW_CLOSE_REQUEST));

  }

  @FXML
  void backgroundClicked(MouseEvent event) {

  }

  @FXML
  void bushClicked(MouseEvent event) {

  }

  @FXML
  void grassClicked(MouseEvent event) {

  }

  @FXML
  void treeClicked(MouseEvent event) {

  }

  void newRound() {
    ducks.forEach((integer, imageView) -> {
      anchorPane.getChildren().add(imageView);
    });
  }

  @Override
  public void newState(GameInfo gameInfo) throws IllegalGameInfoException {
    if (gameInfo == null) {
      return;
    }
    if (gameInfo.getRound() > currentRound) {
      newRound();
      currentRound = gameInfo.getRound();
    }
  }

  @Override
  public void newDuckPosition(DucksInfo duckPosition) throws IllegalDuckPositionException {
    Arrays.stream(duckPosition.duckData()).forEach(duckData -> {
      ImageView duck = ducks.get(duckData.getId());
      duck.setX(duckData.getX());
      duck.setY(duckData.getY());
    });
  }

  @Override
  public void duckHit(int id) throws IllegalDuckIdException {

  }
}
