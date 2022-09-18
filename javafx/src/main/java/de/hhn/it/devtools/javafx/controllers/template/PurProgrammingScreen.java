package de.hhn.it.devtools.javafx.controllers.template;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PurProgrammingScreen extends BorderPane {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PurProgrammingScreen.class);

  public static final String SCREEN = "first.screen";

  private ScreenController screenController;
  private Button gotoFxmlScreenButton;
  private Label headingLabel;

  public PurProgrammingScreen(
      final ScreenController screenController) {
    this.screenController = screenController;
    headingLabel = new Label("This is the first Screen!");
    gotoFxmlScreenButton = new Button("goto SecondScreen");
    gotoFxmlScreenButton.setOnAction((e) -> screenController.switchTo(SCREEN, FxmlPlusProgrammingScreen.SCREEN));
    setTop(headingLabel);
    setCenter(gotoFxmlScreenButton);
  }

}
