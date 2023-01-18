package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.components.ttrpgsheets.DefaultCharacterSheet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharacterSheetController extends Controller {
  private static final Logger logger = LoggerFactory.getLogger(CharacterSheetController.class);

  @FXML
  private TextField ageTextField;
  @FXML
  private Label agilityLabel;
  @FXML
  private Label agilityLevelLabel;
  @FXML
  private Label agilityOtherLabel;
  @FXML
  private TextField characterClassTextField;
  @FXML
  private TextField characterNameTextField;
  @FXML
  private Label defenceLabel;
  @FXML
  private Label defenceLevelLabel;
  @FXML
  private Label defenceOtherLabel;
  @FXML
  private Label dexterityLabel;
  @FXML
  private Label dexterityLevelLabel;
  @FXML
  private Label dexterityOtherLabel;
  @FXML
  private Label diceLabel;
  @FXML
  private MenuButton diceMenuButton;
  @FXML
  private TextField eyeColorTextField;
  @FXML
  private TextField hairColorTextField;
  @FXML
  private Label healthLabel;
  @FXML
  private Label healthLevelLabel;
  @FXML
  private Label healthOtherLabel;
  @FXML
  private TextField heightTextField;
  @FXML
  private Label levelLabel;
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
  private Label strengthLabel;
  @FXML
  private Label strengthLevelLabel;
  @FXML
  private Label strengthOtherLabel;
  @FXML
  private Label unusedLevelPointsLabel;
  @FXML
  private TextField weightTextField;

  private DefaultCharacterSheet characterSheet;
  // TODO Properties erstellen für alle Labels usw.

  public CharacterSheetController() {
    characterSheet = new DefaultCharacterSheet(null);
    // TODO vlt. ein standard ausgefüllter Bogen am anfang laden
  }

  @FXML
  void initialize() {
    // TODO Properties hier binden
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
