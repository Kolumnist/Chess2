package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.apis.ttrpgsheets.CharacterDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.CharacterSheetListener;
import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DiceDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.OriginType;
import de.hhn.it.devtools.apis.ttrpgsheets.StatDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.StatType;
import de.hhn.it.devtools.components.ttrpgsheets.DefaultCharacterSheet;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharacterSheetController extends Controller implements CharacterSheetListener {
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

  private final DefaultCharacterSheet characterSheet;

  HashMap<StatType, SimpleIntegerProperty> stat2Property;

  private final SimpleBooleanProperty isLevelOne;
  // TODO Properties erstellen für alle Labels usw.

  public CharacterSheetController() {
    characterSheet = new DefaultCharacterSheet(CharacterDescriptor.EMPTY);
    isLevelOne = new SimpleBooleanProperty();
    stat2Property = new HashMap<>();
    for (StatType statType : StatType.values()) {
      stat2Property.put(statType, new SimpleIntegerProperty());
    }
    // TODO vlt. ein standard ausgefüllter Bogen am anfang laden
  }

  @FXML
  void initialize() {
    characterSheet.addCallback(this);
    // TODO Properties hier binden
    levelLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.valueOf(stat2Property.get(StatType.LEVEL).get()),
            stat2Property.get(StatType.LEVEL)));

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
    characterSheet.incrementStat(StatType.LEVEL, OriginType.OTHER);
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
  public void statChanged(StatDescriptor stat) {
    StatType statType = stat.getStatType();
    stat2Property.get(statType).set(characterSheet.getStatDisplayValue(statType));
  }

  @Override
  public void descriptionChanged(DescriptionDescriptor description) {

  }

  @Override
  public void diceChanged(DiceDescriptor dice) {

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
