package de.hhn.it.devtools.javafx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharacterSheetController extends Controller {
  private static final Logger logger = LoggerFactory.getLogger(CharacterSheetController.class);

  @FXML
  private TextField ageTextField;
  @FXML
  private TextField agilityTextField;
  @FXML
  private TextField characterClassTextField;
  @FXML
  private TextField characterNameTextField;
  @FXML
  private TextField defenceTextField;
  @FXML
  private TextField dexterityTextField;
  @FXML
  private MenuButton diceMenuButton;
  @FXML
  private TextField eyeColorTextField;
  @FXML
  private TextField hairColorTextField;
  @FXML
  private TextField healthTextField;
  @FXML
  private TextField heightTextField;
  @FXML
  private TextField levelTextField;
  @FXML
  private TextField nickNameTextField;
  @FXML
  private TextArea otherTextField;
  @FXML
  private TextField playerNameTextField;
  @FXML
  private TextField raceTextField;
  @FXML
  private TextField skinColorTextField;
  @FXML
  private TextField strengthTextField;
  @FXML
  private TextField weightTextField;

  public CharacterSheetController() {
    logger.info("Test CharacterSheet");
  }

  @FXML
  void initialize() {

  }

  @FXML
  void onAgilityLevelDown(ActionEvent event) {

  }

  @FXML
  void onAgilityLevelUp(ActionEvent event) {

  }

  @FXML
  void onAgilityOtherDown(ActionEvent event) {

  }

  @FXML
  void onAgilityOtherUp(ActionEvent event) {

  }

  @FXML
  void onDefenceLevelDown(ActionEvent event) {

  }

  @FXML
  void onDefenceLevelUp(ActionEvent event) {

  }

  @FXML
  void onDefenceOtherDown(ActionEvent event) {

  }

  @FXML
  void onDefenceOtherUp(ActionEvent event) {

  }

  @FXML
  void onDexterityLevelDown(ActionEvent event) {

  }

  @FXML
  void onDexterityLevelUp(ActionEvent event) {

  }

  @FXML
  void onDexterityOtherDown(ActionEvent event) {

  }

  @FXML
  void onDexterityOtherUp(ActionEvent event) {

  }

  @FXML
  void onHealthDown(ActionEvent event) {

  }

  @FXML
  void onHealthLevelDown(ActionEvent event) {

  }

  @FXML
  void onHealthLevelUp(ActionEvent event) {

  }

  @FXML
  void onHealthOtherDown(ActionEvent event) {

  }

  @FXML
  void onHealthOtherUp(ActionEvent event) {

  }

  @FXML
  void onHealthUp(ActionEvent event) {

  }

  @FXML
  void onLevelDown(ActionEvent event) {

  }

  @FXML
  void onLevelUp(ActionEvent event) {

  }

  @FXML
  void onLoad(ActionEvent event) {

  }

  @FXML
  void onRollDice(ActionEvent event) {

  }

  @FXML
  void onSave(ActionEvent event) {

  }

  @FXML
  void onStrengthLevelDown(ActionEvent event) {

  }

  @FXML
  void onStrengthLevelUp(ActionEvent event) {

  }

  @FXML
  void onStrengthOtherDown(ActionEvent event) {

  }

  @FXML
  void onStrengthOtherUp(ActionEvent event) {

  }

  @Override
  void pause() {
    logger.debug("pause: -");
  }

  @Override
  void resume() {
    logger.debug("resume: -");
  }
}
