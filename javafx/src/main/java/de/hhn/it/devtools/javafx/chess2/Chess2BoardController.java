package de.hhn.it.devtools.javafx.chess2;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

public class Chess2BoardController {

  // button border color silver: #474747

  //region Buttons in Board
    @FXML
    private Button button00;

    @FXML
    private Button button01;

    @FXML
    private Button button02;

    @FXML
    private Button button03;

    @FXML
    private Button button04;

    @FXML
    private Button button05;

    @FXML
    private Button button06;

    @FXML
    private Button button07;

    @FXML
    private Button button10;

    @FXML
    private Button button11;

    @FXML
    private Button button12;

    @FXML
    private Button button13;

    @FXML
    private Button button14;

    @FXML
    private Button button15;

    @FXML
    private Button button16;

    @FXML
    private Button button17;

    @FXML
    private Button button20;

    @FXML
    private Button button21;

    @FXML
    private Button button22;

    @FXML
    private Button button23;

    @FXML
    private Button button24;

    @FXML
    private Button button25;

    @FXML
    private Button button26;

    @FXML
    private Button button27;

    @FXML
    private Button button30;

    @FXML
    private Button button31;

    @FXML
    private Button button32;

    @FXML
    private Button button33;

    @FXML
    private Button button34;

    @FXML
    private Button button35;

    @FXML
    private Button button36;

    @FXML
    private Button button37;

    @FXML
    private Button button40;

    @FXML
    private Button button41;

    @FXML
    private Button button42;

    @FXML
    private Button button43;

    @FXML
    private Button button44;

    @FXML
    private Button button45;

    @FXML
    private Button button46;

    @FXML
    private Button button47;

    @FXML
    private Button button50;

    @FXML
    private Button button51;

    @FXML
    private Button button52;

    @FXML
    private Button button53;

    @FXML
    private Button button54;

    @FXML
    private Button button55;

    @FXML
    private Button button56;

    @FXML
    private Button button57;

    @FXML
    private Button button60;

    @FXML
    private Button button61;

    @FXML
    private Button button62;

    @FXML
    private Button button63;

    @FXML
    private Button button64;

    @FXML
    private Button button65;

    @FXML
    private Button button66;

    @FXML
    private Button button67;

    @FXML
    private Button button70;

    @FXML
    private Button button71;

    @FXML
    private Button button72;

    @FXML
    private Button button73;

    @FXML
    private Button button74;

    @FXML
    private Button button75;

    @FXML
    private Button button76;

    @FXML
    private Button button77;

    @FXML
    private Button buttonjailLy3;

    @FXML
    private Button buttonjailLy4;

    @FXML
    private Button buttonjailRy3;

    @FXML
    private Button buttonjailRy4;

  //endregion Buttons in Board
  public Chess2BoardController() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/chess2"
        + "/Chess2Board.fxml"));

    try {
      loader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
