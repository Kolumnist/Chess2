package de.hhn.it.devtools.javafx.duckhunt;

import de.hhn.it.devtools.apis.duckhunt.*;
import de.hhn.it.devtools.components.duckhunt.DuckHunt;
import de.hhn.it.devtools.components.duckhunt.ScreenDimension;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    DuckHuntAttributeStore duckHuntAttributeStore = DuckHuntAttributeStore.getReference();
    stage = (Stage) duckHuntAttributeStore.getAttribute("gameStage");
    scene = new Scene(anchorPane, 960, 720);
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

    game = new DuckHunt(
        (GameSettingsDescriptor) duckHuntAttributeStore.getAttribute("gameSettings"),
        new ScreenDimension(960, 530)
    );
    game.startGame();
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
