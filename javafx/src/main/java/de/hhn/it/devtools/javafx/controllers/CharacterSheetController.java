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
import de.hhn.it.devtools.javafx.ttrpgsheets.CharacterSheetFileIo;
import java.util.HashMap;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The controller class for the character sheet module.
 */
public class CharacterSheetController extends Controller implements CharacterSheetListener {
  private static final Logger logger = LoggerFactory.getLogger(CharacterSheetController.class);

  @FXML
  private TextField ageTextField;
  @FXML
  private Label agilityLabel;
  @FXML
  private Label agilityLevelLabel;
  @FXML
  private Button agilityLevelUpButton;
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
  private Button defenceLevelUpButton;
  @FXML
  private Label defenceOtherLabel;
  @FXML
  private Label dexterityLabel;
  @FXML
  private Label dexterityLevelLabel;
  @FXML
  private Button dexterityLevelUpButton;
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
  private Button healthDownButton;
  @FXML
  private Label healthLabel;
  @FXML
  private Button healthLevelDownButton;
  @FXML
  private Label healthLevelLabel;
  @FXML
  private Button healthLevelUpButton;
  @FXML
  private Label healthOtherLabel;
  @FXML
  private Button healthOtherDownButton;
  @FXML
  private Button healthUpButton;
  @FXML
  private TextField heightTextField;
  @FXML
  private Button levelDownButton;
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
  private Button strengthLevelUpButton;
  @FXML
  private Label strengthOtherLabel;
  @FXML
  private Label unusedLevelPointsLabel;
  @FXML
  private TextField weightTextField;

  private final CharacterSheet characterSheet;
  private final CharacterSheetFileIo characterSheetFileIo;

  HashMap<StatType, SimpleIntegerProperty> stat2Property;
  HashMap<StatType, SimpleIntegerProperty> statLevel2Property;
  HashMap<StatType, SimpleIntegerProperty> statOther2Property;
  HashMap<DescriptionType, SimpleStringProperty> description2Property;
  SimpleStringProperty diceTypeProperty;
  SimpleIntegerProperty diceResultProperty;
  SimpleIntegerProperty freeLevelPointsProperty;

  /**
   * Constructor for the controller class.
   * An empty character sheet gets loaded for initialization.
   */
  public CharacterSheetController() {
    characterSheet = new DefaultCharacterSheet(CharacterDescriptor.EMPTY);
    characterSheetFileIo = new CharacterSheetFileIo();
    // Maps for stats
    stat2Property = new HashMap<>();
    statLevel2Property = new HashMap<>();
    statOther2Property = new HashMap<>();
    for (StatType statType : StatType.values()) {
      stat2Property.put(statType, new SimpleIntegerProperty());
      statLevel2Property.put(statType, new SimpleIntegerProperty());
      statOther2Property.put(statType, new SimpleIntegerProperty());
    }
    // Map for descriptions
    description2Property = new HashMap<>();
    for (DescriptionType descriptionType : DescriptionType.values()) {
      description2Property.put(descriptionType, new SimpleStringProperty(""));
    }
    // Dice Properties
    diceTypeProperty = new SimpleStringProperty();
    diceResultProperty = new SimpleIntegerProperty();
    freeLevelPointsProperty = new SimpleIntegerProperty();
  }

  @FXML
  void initialize() {
    // Adding the listener, this will update the whole sheet
    characterSheet.addCallback(this);

    // ########## Stats binding ##########
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

    // ########## Stats level binding ##########
    // Level
    unusedLevelPointsLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.valueOf(freeLevelPointsProperty.get()), freeLevelPointsProperty));

    // Health / Max health
    healthLevelLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.valueOf(statLevel2Property.get(StatType.MAX_HEALTH).get()),
            statLevel2Property.get(StatType.MAX_HEALTH)));
    // Defence
    defenceLevelLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.valueOf(statLevel2Property.get(StatType.DEFENCE).get()),
            statLevel2Property.get(StatType.DEFENCE)));
    // Strength
    strengthLevelLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.valueOf(statLevel2Property.get(StatType.STRENGTH).get()),
            statLevel2Property.get(StatType.STRENGTH)));
    // Agility
    agilityLevelLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.valueOf(statLevel2Property.get(StatType.AGILITY).get()),
            statLevel2Property.get(StatType.AGILITY)));
    // Dexterity
    dexterityLevelLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.valueOf(statLevel2Property.get(StatType.DEXTERITY).get()),
            statLevel2Property.get(StatType.DEXTERITY)));

    // ########## Stats other binding ##########
    // Health / Max health
    healthOtherLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.valueOf(statOther2Property.get(StatType.MAX_HEALTH).get()),
            statOther2Property.get(StatType.MAX_HEALTH)));
    // Defence
    defenceOtherLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.valueOf(statOther2Property.get(StatType.DEFENCE).get()),
            statOther2Property.get(StatType.DEFENCE)));
    // Strength
    strengthOtherLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.valueOf(statOther2Property.get(StatType.STRENGTH).get()),
            statOther2Property.get(StatType.STRENGTH)));
    // Agility
    agilityOtherLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.valueOf(statOther2Property.get(StatType.AGILITY).get()),
            statOther2Property.get(StatType.AGILITY)));
    // Dexterity
    dexterityOtherLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.valueOf(statOther2Property.get(StatType.DEXTERITY).get()),
            statOther2Property.get(StatType.DEXTERITY)));

    // ########## Descriptions binding ##########
    // Player name
    playerNameTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.PLAYER_NAME, newValue));
    playerNameTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.PLAYER_NAME));
    // Character name
    characterNameTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.CHARACTER_NAME, newValue));
    characterNameTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.CHARACTER_NAME));
    // Nickname
    nickNameTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.NICKNAME, newValue));
    nickNameTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.NICKNAME));
    // Age
    ageTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.AGE, newValue));
    ageTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.AGE));
    // Race
    raceTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.RACE, newValue));
    raceTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.RACE));
    // Height
    heightTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.HEIGHT, newValue));
    heightTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.HEIGHT));
    // Weight
    weightTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.WEIGHT, newValue));
    weightTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.WEIGHT));
    // Skin color
    skinColorTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.SKIN_COLOR, newValue));
    skinColorTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.SKIN_COLOR));
    // Hair color
    hairColorTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.HAIR_COLOR, newValue));
    hairColorTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.HAIR_COLOR));
    // Eye color
    eyeColorTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.EYE_COLOR, newValue));
    eyeColorTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.EYE_COLOR));
    // Character class
    characterClassTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.CHARACTER_CLASS, newValue));
    characterClassTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.CHARACTER_CLASS));
    // Other
    otherTextField.textProperty().addListener((observable, oldValue, newValue) ->
            characterSheet.changeDescription(DescriptionType.OTHER, newValue));
    otherTextField.textProperty().bindBidirectional(
            description2Property.get(DescriptionType.OTHER));

    // ########## Dice binding ##########
    diceMenuButton.textProperty().bind(diceTypeProperty);
    diceLabel.textProperty().bind(Bindings.createStringBinding(
            () -> String.valueOf(diceResultProperty.get()), diceResultProperty));

    // ########## Logical bindings health ##########
    // disable health up at full health
    healthUpButton.disableProperty().bind(Bindings.createBooleanBinding(
            () -> stat2Property.get(StatType.HEALTH).get()
                    >= stat2Property.get(StatType.MAX_HEALTH).get(),
            stat2Property.get(StatType.HEALTH), stat2Property.get(StatType.MAX_HEALTH)));
    // disable health down at 0 health
    healthDownButton.disableProperty().bind(Bindings.createBooleanBinding(
            () -> stat2Property.get(StatType.HEALTH).get() <= 0,
            stat2Property.get(StatType.HEALTH)));
    // disable health other down if health would drop below 1
    healthOtherDownButton.disableProperty().bind(Bindings.createBooleanBinding(
            () -> stat2Property.get(StatType.HEALTH).subtract(1).get() < 1,
            stat2Property.get(StatType.HEALTH)));

    // ########## Logical bindings for leveling stats ##########
    // BooleanBinding for: if there are no free level points
    BooleanBinding noFreeLevelPointsBinding = Bindings.createBooleanBinding(
            () -> freeLevelPointsProperty.get() <= 0, freeLevelPointsProperty);
    // disable level down at level 1 or if there are no available level points
    levelDownButton.disableProperty().bind(Bindings.createBooleanBinding(
            () -> stat2Property.get(StatType.LEVEL).get() <= 1, stat2Property.get(StatType.LEVEL),
            freeLevelPointsProperty).or(noFreeLevelPointsBinding));
    // disable health level down if health would drop below 1
    healthLevelDownButton.disableProperty().bind(Bindings.createBooleanBinding(
            () -> stat2Property.get(StatType.HEALTH).subtract(
                    characterSheet.getStatDescriptor(StatType.MAX_HEALTH).getOffset()).get() < 1,
            stat2Property.get(StatType.HEALTH)));
    // disable health level up if there are no available level points
    healthLevelUpButton.disableProperty().bind(noFreeLevelPointsBinding);
    // disable defence level up if there are no available level points
    defenceLevelUpButton.disableProperty().bind(noFreeLevelPointsBinding);
    // disable strength level up if there are no available level points
    strengthLevelUpButton.disableProperty().bind(noFreeLevelPointsBinding);
    // disable agility level up if there are no available level points
    agilityLevelUpButton.disableProperty().bind(noFreeLevelPointsBinding);
    // disable dexterity level up if there are no available level points
    dexterityLevelUpButton.disableProperty().bind(noFreeLevelPointsBinding);
  }

  @FXML
  void onAgilityLevelDown(ActionEvent event) {
    logger.info("Agility level down pressed");
    characterSheet.decrementStat(StatType.AGILITY, OriginType.LEVEL_POINT);
  }

  @FXML
  void onAgilityLevelUp(ActionEvent event) {
    logger.info("Agility level up pressed");
    characterSheet.incrementStat(StatType.AGILITY, OriginType.LEVEL_POINT);
  }

  @FXML
  void onAgilityOtherDown(ActionEvent event) {
    logger.info("Agility other down pressed");
    characterSheet.decrementStat(StatType.AGILITY, OriginType.OTHER);
  }

  @FXML
  void onAgilityOtherUp(ActionEvent event) {
    logger.info("Agility other up pressed");
    characterSheet.incrementStat(StatType.AGILITY, OriginType.OTHER);
  }

  @FXML
  void onDefenceLevelDown(ActionEvent event) {
    logger.info("Defence level down pressed");
    characterSheet.decrementStat(StatType.DEFENCE, OriginType.LEVEL_POINT);
  }

  @FXML
  void onDefenceLevelUp(ActionEvent event) {
    logger.info("Defence level up pressed");
    characterSheet.incrementStat(StatType.DEFENCE, OriginType.LEVEL_POINT);
  }

  @FXML
  void onDefenceOtherDown(ActionEvent event) {
    logger.info("Defence other down pressed");
    characterSheet.decrementStat(StatType.DEFENCE, OriginType.OTHER);
  }

  @FXML
  void onDefenceOtherUp(ActionEvent event) {
    logger.info("Defence other up pressed");
    characterSheet.incrementStat(StatType.DEFENCE, OriginType.OTHER);
  }

  @FXML
  void onDexterityLevelDown(ActionEvent event) {
    logger.info("Dexterity level down pressed");
    characterSheet.decrementStat(StatType.DEXTERITY, OriginType.LEVEL_POINT);
  }

  @FXML
  void onDexterityLevelUp(ActionEvent event) {
    logger.info("Dexterity level up pressed");
    characterSheet.incrementStat(StatType.DEXTERITY, OriginType.LEVEL_POINT);
  }

  @FXML
  void onDexterityOtherDown(ActionEvent event) {
    logger.info("Dexterity other down pressed");
    characterSheet.decrementStat(StatType.DEXTERITY, OriginType.OTHER);
  }

  @FXML
  void onDexterityOtherUp(ActionEvent event) {
    logger.info("Dexterity other up pressed");
    characterSheet.incrementStat(StatType.DEXTERITY, OriginType.OTHER);
  }

  @FXML
  void onHealthDown(ActionEvent event) {
    logger.info("Health down pressed");
    characterSheet.decrementStat(StatType.HEALTH, OriginType.DAMAGE);
  }

  @FXML
  void onHealthUp(ActionEvent event) {
    logger.info("Health up pressed");
    characterSheet.incrementStat(StatType.HEALTH, OriginType.DAMAGE);
  }

  @FXML
  void onHealthLevelDown(ActionEvent event) {
    logger.info("Health level down pressed");
    characterSheet.decrementStat(StatType.MAX_HEALTH, OriginType.LEVEL_POINT);
    characterSheet.decrementStat(StatType.HEALTH, OriginType.DAMAGE,
            characterSheet.getStatDescriptor(StatType.MAX_HEALTH).getOffset());
  }

  @FXML
  void onHealthLevelUp(ActionEvent event) {
    logger.info("Health level up pressed");
    characterSheet.incrementStat(StatType.MAX_HEALTH, OriginType.LEVEL_POINT);
    characterSheet.incrementStat(StatType.HEALTH, OriginType.DAMAGE,
            characterSheet.getStatDescriptor(StatType.MAX_HEALTH).getOffset());
  }

  @FXML
  void onHealthOtherDown(ActionEvent event) {
    logger.info("Health other down pressed");
    characterSheet.decrementStat(StatType.MAX_HEALTH, OriginType.OTHER);
    characterSheet.decrementStat(StatType.HEALTH, OriginType.DAMAGE);
  }

  @FXML
  void onHealthOtherUp(ActionEvent event) {
    logger.info("Health other up pressed");
    characterSheet.incrementStat(StatType.MAX_HEALTH, OriginType.OTHER);
    characterSheet.incrementStat(StatType.HEALTH, OriginType.DAMAGE);
  }

  @FXML
  void onStrengthLevelDown(ActionEvent event) {
    logger.info("Strength level down pressed");
    characterSheet.decrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT);
  }

  @FXML
  void onStrengthLevelUp(ActionEvent event) {
    logger.info("Strength level up pressed");
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.LEVEL_POINT);
  }

  @FXML
  void onStrengthOtherDown(ActionEvent event) {
    logger.info("Strength other down pressed");
    characterSheet.decrementStat(StatType.STRENGTH, OriginType.OTHER);
  }

  @FXML
  void onStrengthOtherUp(ActionEvent event) {
    logger.info("Strength other up pressed");
    characterSheet.incrementStat(StatType.STRENGTH, OriginType.OTHER);
  }

  @FXML
  void onLevelDown(ActionEvent event) {
    logger.info("Level down pressed");
    characterSheet.decrementStat(StatType.LEVEL, OriginType.OTHER);
  }

  @FXML
  void onLevelUp(ActionEvent event) {
    logger.info("Level up pressed");
    characterSheet.incrementStat(StatType.LEVEL, OriginType.OTHER);
  }

  @FXML
  void onLoad(ActionEvent event) {
    logger.info("Load pressed");
    characterSheetFileIo.setParent(((Node) event.getSource()).getScene().getWindow());
    CharacterDescriptor loadedCharacter = characterSheetFileIo.loadCharacterFile();
    if (loadedCharacter != null) {
      characterSheet.unwrapCharacter(loadedCharacter);
    }
  }

  @FXML
  void onSave(ActionEvent event) {
    logger.info("Save pressed");
    String name = description2Property.get(DescriptionType.CHARACTER_NAME).get();
    characterSheetFileIo.setParent(((Node) event.getSource()).getScene().getWindow());
    characterSheetFileIo.saveCharacterFile(name.isBlank() ? "My Character" : name,
            characterSheet.wrapCharacter());
  }

  @FXML
  void onRollDice(ActionEvent event) {
    logger.info("Roll dice pressed");
    characterSheet.rollDice();
  }

  @FXML
  void onSetD2(ActionEvent event) {
    logger.info("Set D2 pressed");
    characterSheet.changeDiceType(DiceType.D2);
  }

  @FXML
  void onSetD4(ActionEvent event) {
    logger.info("Set D4 pressed");
    characterSheet.changeDiceType(DiceType.D4);
  }

  @FXML
  void onSetD6(ActionEvent event) {
    logger.info("Set D6 pressed");
    characterSheet.changeDiceType(DiceType.D6);
  }

  @FXML
  void onSetD8(ActionEvent event) {
    logger.info("Set D8 pressed");
    characterSheet.changeDiceType(DiceType.D8);
  }

  @FXML
  void onSetD10(ActionEvent event) {
    logger.info("Set D10 pressed");
    characterSheet.changeDiceType(DiceType.D10);
  }

  @FXML
  void onSetD12(ActionEvent event) {
    logger.info("Set D12 pressed");
    characterSheet.changeDiceType(DiceType.D12);
  }

  @FXML
  void onSetD20(ActionEvent event) {
    logger.info("Set D20 pressed");
    characterSheet.changeDiceType(DiceType.D20);
  }

  @FXML
  void onSetD100(ActionEvent event) {
    logger.info("Set D100 pressed");
    characterSheet.changeDiceType(DiceType.D100);
  }

  private int calculateFreeLevelPoints() {
    int total = 5 + stat2Property.get(StatType.LEVEL).get();
    int used = 0;
    for (SimpleIntegerProperty value : statLevel2Property.values()) {
      used += value.get();
    }
    return total - used;
  }

  // Callbacks
  @Override
  public void statChanged(StatDescriptor stat) {
    Platform.runLater(() -> {
      StatType statType = stat.getStatType();
      stat2Property.get(statType).set(characterSheet.getStatDisplayValue(statType));
      statLevel2Property.get(statType).set(stat.getAbilityPointsUsed());
      statOther2Property.get(statType).set(stat.getMiscellaneous());
      freeLevelPointsProperty.set(calculateFreeLevelPoints());
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
      diceResultProperty.set(dice.getResult());
      switch (dice.getDiceType()) {
        case D2 -> diceTypeProperty.set("D2");
        case D4 -> diceTypeProperty.set("D4");
        case D6 -> diceTypeProperty.set("D6");
        case D8 -> diceTypeProperty.set("D8");
        case D10 -> diceTypeProperty.set("D10");
        case D12 -> diceTypeProperty.set("D12");
        case D20 -> diceTypeProperty.set("D20");
        case D100 -> diceTypeProperty.set("D100");
        default -> {
        }
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