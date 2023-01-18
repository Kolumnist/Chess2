package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.apis.ttrpgsheets.CharacterDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.CharacterSheet;
import de.hhn.it.devtools.apis.ttrpgsheets.CharacterSheetListener;
import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DescriptionType;
import de.hhn.it.devtools.apis.ttrpgsheets.DiceDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.DiceType;
import de.hhn.it.devtools.apis.ttrpgsheets.OriginType;
import de.hhn.it.devtools.apis.ttrpgsheets.StatDescriptor;
import de.hhn.it.devtools.apis.ttrpgsheets.StatType;
import de.hhn.it.devtools.components.ttrpgsheets.DefaultCharacterSheet;
import java.util.HashMap;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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

  private final CharacterSheet characterSheet;

  HashMap<StatType, SimpleIntegerProperty> stat2Property;
  HashMap<DescriptionType, SimpleStringProperty> description2Property;
  SimpleIntegerProperty diceProperty;

  public CharacterSheetController() {
    characterSheet = new DefaultCharacterSheet(CharacterDescriptor.EMPTY);
    stat2Property = new HashMap<>();
    description2Property = new HashMap<>();
    for (StatType statType : StatType.values()) {
      stat2Property.put(statType, new SimpleIntegerProperty());
    }
    for (DescriptionType descriptionType : DescriptionType.values()) {
      description2Property.put(descriptionType, new SimpleStringProperty());
    }
    diceProperty = new SimpleIntegerProperty();
  }

  @FXML
  void initialize() {
    characterSheet.addCallback(this);
    // Stats binding
    // Level
    levelLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.valueOf(stat2Property.get(StatType.LEVEL).get()),
            stat2Property.get(StatType.LEVEL)));
    // Health / Max health
    healthLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.format("%d/%d", stat2Property.get(StatType.HEALTH).get(),
            stat2Property.get(StatType.MAX_HEALTH).get()),
            stat2Property.get(StatType.HEALTH), stat2Property.get(StatType.MAX_HEALTH)));
    // Defence
    defenceLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.valueOf(stat2Property.get(StatType.DEFENCE).get()),
            stat2Property.get(StatType.DEFENCE)));
    // Strength
    strengthLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.valueOf(stat2Property.get(StatType.STRENGTH).get()),
            stat2Property.get(StatType.STRENGTH)));
    // Agility
    agilityLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.valueOf(stat2Property.get(StatType.AGILITY).get()),
            stat2Property.get(StatType.AGILITY)));
    // Dexterity
    dexterityLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.valueOf(stat2Property.get(StatType.DEXTERITY).get()),
            stat2Property.get(StatType.DEXTERITY)));

    // Descriptions binding
    // Player name
    playerNameTextField.textProperty().addListener((o, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.PLAYER_NAME, newValue));
    playerNameTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.PLAYER_NAME));

    // Dice binding
    diceLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.valueOf(diceProperty.get()), diceProperty));
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
    characterSheet.rollDice();
  }

  @FXML
  void onSave(ActionEvent event) {

  }

  @FXML
  void onSetD2(ActionEvent event) {
    characterSheet.changeDiceType(DiceType.D2);
  }

  @FXML
  void onSetD4(ActionEvent event) {
    characterSheet.changeDiceType(DiceType.D4);
  }

  @FXML
  void onSetD6(ActionEvent event) {
    characterSheet.changeDiceType(DiceType.D6);
  }

  @FXML
  void onSetD8(ActionEvent event) {
    characterSheet.changeDiceType(DiceType.D8);
  }

  @FXML
  void onSetD10(ActionEvent event) {
    characterSheet.changeDiceType(DiceType.D10);
  }

  @FXML
  void onSetD12(ActionEvent event) {
    characterSheet.changeDiceType(DiceType.D12);
  }

  @FXML
  void onSetD20(ActionEvent event) {
    characterSheet.changeDiceType(DiceType.D20);
  }

  @FXML
  void onSetD100(ActionEvent event) {
    characterSheet.changeDiceType(DiceType.D100);
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

  // Callbacks
  @Override
  public void statChanged(StatDescriptor stat) {
    Platform.runLater(() -> {
      StatType statType = stat.getStatType();
      stat2Property.get(statType).set(characterSheet.getStatDisplayValue(statType));
    });
  }

  @Override
  public void descriptionChanged(DescriptionDescriptor description) {
    Platform.runLater(() ->
            description2Property.get(description.getDescriptionType()).set(description.getText()));
  }

  @Override
  public void diceChanged(DiceDescriptor dice) {
    Platform.runLater(() -> {
      diceProperty.set(dice.getResult());
      switch (dice.getDiceType()) {
        case D2 -> diceMenuButton.setText("D2");
        case D4 -> diceMenuButton.setText("D4");
        case D6 -> diceMenuButton.setText("D6");
        case D8 -> diceMenuButton.setText("D8");
        case D10 -> diceMenuButton.setText("D10");
        case D12 -> diceMenuButton.setText("D12");
        case D20 -> diceMenuButton.setText("D20");
        case D100 -> diceMenuButton.setText("D100");
      }
    });
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
