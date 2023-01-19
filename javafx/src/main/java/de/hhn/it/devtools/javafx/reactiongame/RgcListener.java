package de.hhn.it.devtools.javafx.reactiongame;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.reactiongame.AimTargetDescriptor;
import de.hhn.it.devtools.apis.reactiongame.GameState;
import de.hhn.it.devtools.apis.reactiongame.ObstacleDescriptor;
import de.hhn.it.devtools.apis.reactiongame.ReactiongameListener;
import de.hhn.it.devtools.components.reactiongame.provider.RgcService;
import de.hhn.it.devtools.javafx.controllers.ReactionGameController;
import de.hhn.it.devtools.javafx.controllers.reactiongame.RgcGameController;
import de.hhn.it.devtools.javafx.controllers.reactiongame.RgcScreenController;
import de.hhn.it.devtools.javafx.controllers.template.SingletonAttributeStore;
import java.io.IOException;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class RgcListener implements ReactiongameListener {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RgcListener.class);


  private final RgcScreenController screenController;
  private final AnchorPane pane;
  private final RgcService service;

  private final RgcGameController controller;

  private final RgcDisplayer displayer;


  public RgcListener(AnchorPane pane, RgcService service, RgcGameController controller) {
    this.pane = pane;
    this.service = service;
    this.controller = controller;

    displayer = new RgcDisplayer(controller.getInfoLable());

    service.addCallback(this);

    SingletonAttributeStore singletonAttributeStore = SingletonAttributeStore.getReference();
    screenController = (RgcScreenController)
        singletonAttributeStore.getAttribute(ReactionGameController.RGC_SCREEN_CONTROLLER);
  }

  @Override
  public void addAimTarget(AimTargetDescriptor aimTarget) {
    logger.info("Added aim target " + aimTarget.getId());


    RgcAimTargetFx aimTargetFx = new RgcAimTargetFx(aimTarget, service);

    Platform.runLater(() -> pane.getChildren().add(aimTargetFx));
  }

  @Override
  public void removeAimTarget(int aimTargetId) {
    logger.info("Remove aim target " + aimTargetId);
    for (Node n :
        pane.getChildren()) {
      if (n instanceof RgcAimTargetFx) {
        if (n.getId() != null && n.getId().equals(aimTargetId + "")) {
          Platform.runLater(() -> pane.getChildren().remove(n));
          return;
        }
      }
    }
  }

  @Override
  public void addObstacle(ObstacleDescriptor obstacle) {
    logger.info("Added obstacle " + obstacle.getId());
    Platform.runLater(() -> pane.getChildren().add(new RgcObstacleFx(obstacle, service)));

  }

  @Override
  public void removeObstacle(int obstacleId) {
    logger.info("Remove obstacle " + obstacleId);
    for (Node n :
        pane.getChildren()) {
      if (n instanceof RgcObstacleFx) {
        if (n.getId() != null && n.getId().equals(obstacleId + "")) {
          Platform.runLater(() -> pane.getChildren().remove(n));
          return;
        }
      }
    }
  }

  @Override
  public void hitObstacle(int obstacleId) {
    logger.info("Hit obstacle " + obstacleId);
    for (Node n :
        pane.getChildren()) {
      if (n instanceof RgcObstacleFx) {
        if (n.getId() != null && n.getId().equals(obstacleId + "")) {
          Platform.runLater(() -> ((RgcObstacleFx) n).setFill(Color.RED));
          return;
        }
      }
    }
  }

  @Override
  public void currentLife(int numberOfLifes) {
    Platform.runLater(() -> controller.getLiveLable().setText(numberOfLifes + ""));

    Platform.runLater(() -> displayer.displayText("HIT!", 2000));
  }

  @Override
  public void aimTargetHit(char keyPressed, int aimTargetId) {

  }

  @Override
  public void changeGameState(GameState state) {

  }

  @Override
  public void changeScore(int score) {
    controller.getScoreLabel().setText(score + "");
  }

  @Override
  public void pauseRun() {

  }

  @Override
  public void continueRun() {

  }

  @Override
  public void gameOver() {
    Platform.runLater(() -> {
      try {
        controller.deleteHandlers();
        screenController.switchTo("RgcEnterPlayerName");
        service.removeCallback(this);
      } catch (IOException | IllegalParameterException e) {
        throw new RuntimeException(e);
      }
    });
  }

}
