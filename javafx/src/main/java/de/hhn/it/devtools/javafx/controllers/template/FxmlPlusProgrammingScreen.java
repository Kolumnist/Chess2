package de.hhn.it.devtools.javafx.controllers.template;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class FxmlPlusProgrammingScreen extends VBox implements Initializable {
  public static final String SCREEN = "second.screen";

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(FxmlPlusProgrammingScreen.class);

  private ScreenController screenController;

  @FXML
  private Label headingLabel;
  @FXML
  private TextArea loremTextArea;

  public FxmlPlusProgrammingScreen(final ScreenController screenController) {
    this.screenController = screenController;

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/template/SecondScreen.fxml"));
    loader.setRoot(this);
    loader.setController(this);

    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    headingLabel.setText("This is the second screen!");
    loremTextArea.setText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
    logger.info("Screen initialized.");
  }

  public void onActionGoBack() {
    screenController.switchTo(SCREEN, PurProgrammingScreen.SCREEN);
  }

  public void onActionGoNext() {
    screenController.switchTo(SCREEN, FxmlCreatesControllerScreen.SCREEN);
  }

}
