package de.hhn.it.devtools.javafx.controllers.memory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;

public class CardController implements Initializable{
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(CardController.class);
    private MemoryScreenController screenController;

    @FXML
    private Pane pane;

    @FXML
    private ImageView picture;

    @FXML
    private Pane cover;

    @FXML
    private void onMouseClicked(MouseEvent event) {

    }

    public CardController(MemoryScreenController screenController) {
        this.screenController = screenController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
