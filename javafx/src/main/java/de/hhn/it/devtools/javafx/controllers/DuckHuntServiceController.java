package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.apis.duckhunt.GameSettingsDescriptor;
import de.hhn.it.devtools.javafx.Main;
import de.hhn.it.devtools.javafx.duckhunt.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DuckHuntServiceController extends Controller implements Initializable {

  @FXML
  private AnchorPane backgroundAnchorPane;
  private DuckHuntScreenController screenController;
  private GameSettingsDescriptor gameSettings;
  private Stage gameStage;
  private DuckHuntSoundManager soundManager;

  public DuckHuntServiceController() {
    gameStage = new Stage();
    gameStage.setTitle("Duck Hunt");

    // Specifies the modality for new window.
    gameStage.initModality(Modality.WINDOW_MODAL);

    // Specifies the owner Window (parent) for new window
    gameStage.initOwner(Main.primaryStage);

    // Set position of second window, related to primary window.
    gameStage.setX(Main.primaryStage.getX() + 200);
    gameStage.setY(Main.primaryStage.getY() + 100);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    screenController = new DuckHuntScreenController(backgroundAnchorPane);
    gameSettings = new GameSettingsDescriptor();
    soundManager = new DuckHuntSoundManager();
    DuckHuntAttributeStore duckHuntAttributeStore = DuckHuntAttributeStore.getReference();
    duckHuntAttributeStore.setAttribute(DuckHuntScreenController.SCREEN_CONTROLLER, screenController);
    duckHuntAttributeStore.setAttribute("gameSettings", gameSettings);
    duckHuntAttributeStore.setAttribute("gameStage", gameStage);
    duckHuntAttributeStore.setAttribute("soundManager", soundManager);

    screenController.switchTo(null, DuckHuntMenuController.SCREEN);
  }
}