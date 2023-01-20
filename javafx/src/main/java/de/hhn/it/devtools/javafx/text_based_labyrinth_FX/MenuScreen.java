package de.hhn.it.devtools.javafx.text_based_labyrinth_FX;

import de.hhn.it.devtools.apis.textbasedlabyrinth.InvalidSeedException;
import de.hhn.it.devtools.apis.textbasedlabyrinth.Map;
import de.hhn.it.devtools.apis.textbasedlabyrinth.RoomFailedException;
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
    private GameScreenController screenController;

    @FXML
    TextField playerName;
    @FXML
    ChoiceBox<Map> mapChoiceBox;
    @FXML
    TextField seedTextField;
    @FXML
    Button startButton;


    public MenuScreen(GameScreenController screenController) {
        this.screenController = screenController;
    }




    public void update() {
        playerName.clear();
        seedTextField.clear();
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void setViewModel(GameViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void startGame() throws UnknownTransitionException {
        ArrayList<Integer> integers = new ArrayList<>();
        int a = 0;
        while (a < seedTextField.getText().length()) {
            char c = seedTextField.getCharacters().charAt(a);
            if (Character.isDigit(c)) {
                if (integers.size() < 2) {
                    integers.add(seedTextField.getText().indexOf(a));
                }
            }
            a++;
        }

        try {
            viewModel.getGame().setCurrentLayout(mapChoiceBox.getValue(), new Seed(integers));
        } catch (RoomFailedException | InvalidSeedException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        viewModel.getGame().start();
        try {
            screenController.changeScreen(MenuScreen.SCREEN_NAME, GameMainScreen.SCREEN_NAME);
        } catch (UnknownTransitionException e) {
            throw new UnknownTransitionException(e.getMessage(), e.getFrom(), e.getTo());
        }

    }
}
