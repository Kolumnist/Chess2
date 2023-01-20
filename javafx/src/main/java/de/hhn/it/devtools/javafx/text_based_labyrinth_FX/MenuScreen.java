package de.hhn.it.devtools.javafx.text_based_labyrinth_FX;

import de.hhn.it.devtools.apis.textbasedlabyrinth.Map;
import de.hhn.it.devtools.apis.textbasedlabyrinth.Seed;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuScreen extends AnchorPane implements Initializable {



    public static final String SCREEN_NAME = "MenuScreen";

    private GameViewModel viewModel;

    @FXML
    TextField playerName;
    @FXML
    ChoiceBox<Map> mapChoiceBox;
    @FXML
    TextField seedTextField;
    @FXML
    Button startButton;


    public MenuScreen(GameScreenController screenController) {

    }




    public void update() {

    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void setViewModel(GameViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void startGame() {
        ArrayList<Integer> integers = new ArrayList<>();

    }
}
