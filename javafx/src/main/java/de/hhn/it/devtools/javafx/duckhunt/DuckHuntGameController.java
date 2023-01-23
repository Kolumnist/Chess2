package de.hhn.it.devtools.javafx.duckhunt;

import de.hhn.it.devtools.apis.duckhunt.*;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.components.duckhunt.DuckHunt;
import de.hhn.it.devtools.components.duckhunt.ScreenDimension;
import java.net.URL;
import java.util.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
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
import javafx.util.Duration;

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
  @FXML private Label gameOverLabel;
  @FXML private VBox pauseMenu;


  private DuckHunt game;
  private Stage stage;
  private Scene scene;
  private HashMap<Integer, ImageView> ducks;
  private GameSettingsDescriptor gameSettings;
  private DuckHuntSoundManager soundManager;
  private GameInfo gameInfo;
  private int currentRound = 1;
  private long score = 0;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // stage creation
    DuckHuntAttributeStore duckHuntAttributeStore = DuckHuntAttributeStore.getReference();
    stage = (Stage) duckHuntAttributeStore.getAttribute("gameStage");
    scene = new Scene(anchorPane, 960, 720);
    URL cursorPath = getClass().getResource("/images/duckhunt/Crosshair.png");
    Image cursorImage = new Image(cursorPath.toExternalForm(), 300, 300, false, false);
    scene.setCursor(new ImageCursor(cursorImage));
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

    int duckCount = 1;
    for (Map.Entry<Integer, ImageView> entry : ducks.entrySet()) {
      anchorPane.getChildren().add(
          anchorPane.getChildren().indexOf(backgroundImage) + duckCount, entry.getValue());
      duckCount++;
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
    stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
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

  private List<ImageView> getDuckList() {
    return anchorPane.getChildren().stream()
        .filter(node -> node instanceof ImageView && node.getId().contains("duck"))
        .map(ImageView.class::cast)
        .toList();
  }

  void newRound() {
    Platform.runLater(() -> {
      game.pauseGame();
    });
    final KeyFrame kf1 = new KeyFrame(Duration.seconds(2), e -> {
      nextRoundLabel.getParent().setVisible(false);
      nextRoundLabel.getParent().setDisable(true);
    });
    final KeyFrame kf2 = new KeyFrame(Duration.seconds(0), e -> {
      nextRoundLabel.getParent().setVisible(true);
      nextRoundLabel.getParent().setDisable(false);
      game.continueGame();
    });
    final Timeline timeline = new Timeline(kf1, kf2);
    Platform.runLater(timeline::play);
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
    gameInfo.setPlayerScore(gameInfo.getPlayerScore());
    scoreLabel.setText(String.format("%010d",
            gameInfo.getPlayerScore() * 100));
  }

  @Override
  public void newState(GameInfo gameInfo) throws IllegalGameInfoException {
    if (gameInfo == null) {
      return;
    }
    this.gameInfo = gameInfo;
    drawAmmo(gameInfo.getAmmo());

    if (gameInfo.getRound() > currentRound) {
      drawMissed();
      newRound();
      currentRound++;
    }
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
            duck.setDisable(false);
            duck.setVisible(true);
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

  private void drawAmmo(int ammoCount) {
    Platform.runLater(() -> {
        ammoLabel.setText(String.valueOf(ammoCount));
    });
  }

  private void drawMissed() {
    Platform.runLater(() -> {
      String missedLabelText = "";
      for (int x = 0; x < gameInfo.getMissedCount(); x++) {
        missedLabelText = missedLabelText.concat("█");
      }
      hitLabel.setText(missedLabelText);
    });
  }

  private void gameOver() {
    exitButton.getParent().setDisable(false);
    exitButton.getParent().setVisible(true);
    continueButton.setDisable(true);
    continueButton.setVisible(false);
    gameOverLabel.setDisable(false);
    gameOverLabel.setVisible(true);
  }
}
