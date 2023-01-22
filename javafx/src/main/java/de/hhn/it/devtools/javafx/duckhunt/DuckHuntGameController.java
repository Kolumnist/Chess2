package de.hhn.it.devtools.javafx.duckhunt;

import de.hhn.it.devtools.apis.duckhunt.*;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.components.duckhunt.DuckHunt;
import de.hhn.it.devtools.components.duckhunt.ScreenDimension;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Creates additional window with the duck hunt game running.
 */
public class DuckHuntGameController implements Initializable, DuckHuntListener {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(DuckHuntScreenController.class);

  public static final String SCREEN = "game.screen";

  @FXML private AnchorPane anchorPane;
  @FXML private Button continueButton;
  @FXML private Button exitButton;
  @FXML private ImageView backgroundImage;
  @FXML private ImageView bushImage;
  @FXML private ImageView groundImage;
  @FXML private ImageView treeImage;
  @FXML private Label ammoLabel;
  @FXML private Label hitLabel;
  @FXML private Label nextRoundLabel;
  @FXML private Label scoreLabel;
  @FXML private VBox pauseMenu;

  private DuckHunt game;
  private Stage stage;
  private Scene scene;
  private HashMap<Integer, ImageView> ducks;
  private GameSettingsDescriptor gameSettings;
  private DuckHuntSoundManager soundManager;
  private GameInfo gameInfo;
  private int currentRound = 0;
  private long score = 0;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // stage creation
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
      soundManager.stop();
    });

    soundManager = (DuckHuntSoundManager) duckHuntAttributeStore.getAttribute("soundManager");

    // game creation
    gameSettings = (GameSettingsDescriptor) duckHuntAttributeStore.getAttribute("gameSettings");

    ducks = new HashMap<>();
    for (int i = 0; i < gameSettings.getduckAmount(); i++) {
      ImageView duck = DuckHuntImageManager.NORTHDUCK.getImageView();
      duck.setOnMouseClicked(event -> {
        shoot(event);
      });
      ducks.put(i, duck);
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
    soundManager.playLoopSound(DuckHuntSounds.AMBIANCE);
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
    shoot(event);
  }

  @FXML
  void bushClicked(MouseEvent event) {
    shoot(event);
  }

  @FXML
  void treeClicked(MouseEvent event) {
    shootObstacle();
  }

  @FXML
  void groundClicked(MouseEvent event) {
    shoot(event);
  }

  void newRound() {
    game.pauseGame();
    nextRoundLabel.setDisable(false);
    nextRoundLabel.setVisible(true);
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    nextRoundLabel.setDisable(true);
    nextRoundLabel.setVisible(false);
    anchorPane.getChildren().clear();
    anchorPane.getChildren().add(backgroundImage);
    ducks.forEach((integer, imageView) -> {
      anchorPane.getChildren().add(imageView);
    });
    anchorPane.getChildren().add(bushImage);
    anchorPane.getChildren().add(treeImage);
    anchorPane.getChildren().add(groundImage);
    anchorPane.getChildren().add(ammoLabel);
    anchorPane.getChildren().add(hitLabel);
    anchorPane.getChildren().add(scoreLabel);
    anchorPane.getChildren().add(pauseMenu);
    anchorPane.getChildren().add(nextRoundLabel);
    game.continueGame();
  }

  void shoot(MouseEvent event) {
    if (gameInfo.getAmmo() <= 0) {
      soundManager.playSound(DuckHuntSounds.DRYSHOT);
      return;
    }
    soundManager.playSound(DuckHuntSounds.GUNSHOT);
    if (game.getGameInfo().getState() == GameState.RUNNING) {
      game.shoot(
          Double.valueOf(event.getX()).intValue(),
          Double.valueOf(event.getY()).intValue()
      );
    }
  }

  void shootObstacle() {
    if (gameInfo.getAmmo() <= 0) {
      soundManager.playSound(DuckHuntSounds.DRYSHOT);
      return;
    }
    soundManager.playSound(DuckHuntSounds.GUNSHOT);
    if (game.getGameInfo().getState() == GameState.RUNNING) {
      game.shootObstacle();
    }
  }

  void increaseScore() {
    score += 100;
    scoreLabel.setText(String.format("%010d", score));
  }

  @Override
  public void newState(GameInfo gameInfo) throws IllegalGameInfoException {
    if (gameInfo == null) {
      return;
    }
    this.gameInfo = gameInfo;

    Platform.runLater(() -> {
      ammoLabel.setText(String.valueOf(gameInfo.getAmmo()));
      if (gameInfo.getRound() > currentRound) {
        DuckHuntGameController.this.newRound();
        DuckHuntGameController.this.currentRound++;
      }
    });
  }

  @Override
  public void newDuckPosition(DucksInfo duckPosition) throws IllegalDuckPositionException {
    Platform.runLater(() -> {
      Arrays.stream(duckPosition.duckData()).forEach(duckData -> {
        ImageView duck = ducks.get(duckData.getId());
        switch (duckData.getStatus()) {
          case DEAD, ESCAPED -> {
            duck.setDisable(true);
            duck.setVisible(false);
          }
          case FALLING -> {
            duck.setX(duckData.getX());
            duck.setY(duckData.getY());
            duck.setImage(DuckHuntImageManager.FALLINGDUCK.getImageView().getImage());
          }
          case SCARRED -> {
            duck.setX(duckData.getX());
            duck.setY(duckData.getY());
            duck.setImage(DuckHuntImageManager.SCAREDDUCK.getImageView().getImage());
          }
          case FLYAWAY -> {
            duck.setX(duckData.getX());
            duck.setY(duckData.getY());
            duck.setImage(DuckHuntImageManager.NORTHDUCK.getImageView().getImage());
          }
          default -> {
            duck.setX(duckData.getX());
            duck.setY(duckData.getY());
            duck.setImage(DuckHuntImageManager.getDuckImageFromOrientation(
                duckData.getOrientation()).getImageView().getImage()
            );
          }
        }
      });
    });
  }

  @Override
  public void duckHit(int id) throws IllegalDuckIdException {
    Platform.runLater(() -> {
      increaseScore();
    });
  }
}
