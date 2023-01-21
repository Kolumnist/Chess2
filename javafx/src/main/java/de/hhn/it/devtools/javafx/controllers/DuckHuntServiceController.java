package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.javafx.duckhunt.DuckHuntAttributeStore;
import de.hhn.it.devtools.javafx.duckhunt.DuckHuntMenuController;
import de.hhn.it.devtools.javafx.duckhunt.DuckHuntScreenController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DuckHuntServiceController extends Controller implements Initializable {

  @FXML
  private AnchorPane backgroundAnchorPane;
  DuckHuntScreenController screenController;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    screenController = new DuckHuntScreenController(backgroundAnchorPane);
    DuckHuntAttributeStore duckHuntAttributeStore = DuckHuntAttributeStore.getReference();
    duckHuntAttributeStore.setAttribute(DuckHuntScreenController.SCREEN_CONTROLLER, screenController);

    screenController.switchTo(null, DuckHuntMenuController.SCREEN);
  }
}