package de.hhn.it.devtools.javafx.text_based_labyrinth_FX;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryScreen extends AnchorPane implements Initializable {



    public static final String SCREEN_NAME = "InventoryScreen";

    private GameViewModel viewModel;


    @FXML
    TextField itemInspectTextField;


    public InventoryScreen(GameScreenController screenController) {

    }




    public void update() {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void updateInspectField(String text) {
        itemInspectTextField.clear();
        itemInspectTextField.setText(text);
    }
}
