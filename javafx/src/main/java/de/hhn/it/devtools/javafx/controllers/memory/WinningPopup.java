package de.hhn.it.devtools.javafx.controllers.memory;

import de.hhn.it.devtools.javafx.controllers.MemoryServiceController;
import de.hhn.it.devtools.javafx.controllers.template.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WinningPopup extends VBox implements Initializable {
    public static final String OPEN_POPUP = "winning.popup.open";
    public static final String CLOSE_POPUP = "winning.popup.close";


    MemoryScreenController screenController;
    RadioButton hover;

    @FXML
    private Button closeDifficulty;

    @FXML
    private Button saveDifficulty;

    @FXML
    private RadioButton easy;

    @FXML
    private RadioButton medium;

    @FXML
    private RadioButton hard;

    @FXML
    private RadioButton extrem;

    @FXML
    void onCloseButtonPressed(ActionEvent event) {
        screenController.switchTo(CLOSE_POPUP);
    }

    @FXML
    void onSaveButtonPressed(ActionEvent event) {
        screenController.changeDifficulty(hover.getText().toString().trim().toLowerCase());
        screenController.switchTo(CLOSE_POPUP);
    }

    @FXML
    void onSelect(ActionEvent event) {
        hover = (RadioButton) event.getSource();
    }

    @FXML
    void handle(MouseEvent event) {
        Object source = event.getSource();
        if (source == closeDifficulty) {
            if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
                closeDifficulty.setStyle("-fx-background-color: LIGHTGRAY; -fx-border-color: BLACK; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
            } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
                closeDifficulty.setStyle("-fx-background-color: WHITE; -fx-border-color: BLACK; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
            }
        } else if (source == saveDifficulty) {
            if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
                saveDifficulty.setStyle("-fx-background-color: LIGHTGRAY; -fx-border-color: BLACK; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
            } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
                saveDifficulty.setStyle("-fx-background-color: WHITE; -fx-border-color: BLACK; -fx-border-radius: 10; -fx-background-radius: 10; -fx-border-width: 3;");
            }
        }
    }

    public WinningPopup(final MemoryScreenController screenController) {
        this.screenController = screenController;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/memory/WinningScreen.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}