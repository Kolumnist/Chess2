package de.hhn.it.devtools.javafx.chess2;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Chess2PopUpRulesController {


  @FXML
  private TableColumn<Rules, String> columnDescription;

  @FXML
  private TableColumn<Rules, String> columnFigur;

  @FXML
  private TableView<Rules> myTableView;

  ObservableList<Rules> list = FXCollections.observableArrayList(

      new Rules("Fish",
          "He can walk 1 field to the right, legft or diagonally forward. He can only "
              + "defeat enemies forward diagonal.\bThe fish becomes a fishqueen by reaching the other end of the board."),
      new Rules("Fishqueen",
          "Its the same as the queen but doesn't go to jail."),
      new Rules("Elephant",
          "He moves 2 fields only and defeats enemies standing on the second field. "
              + "He can move in every direction."),
      new Rules("Bear",
          "Sits in the middle of the chessboard. It can move 1 field in every direction "
              + "and both players can move him on their turns. It can defeated by both.\bIt is a "
              + "friendly bear and can't defeat anyone on his own but if he gets defeated the piece "
              + "which defeated him gets defeated as well."),
      new Rules("Monkey",
          "He can move 1 field in every direction and jumps over other pieces once, after "
              + "jumping he can jump again if there is another piece which he hasn't jumped over yet. "
              + "He can only defeat an opposing piece when he jumps over another piece. When the King "
              + "is in jail and still has his banana, the monkey is able to free the King if another piece "
              + "is positioned so that the monkey lands on the field next to the king after jumping over the "
              + "positioned piece. The monkey frees the king, takes the banana and lands back to the field before "
              + "he was jumping over the positioned piece and the king lands next to the jail where the monkey freed him."),
      new Rules("Crow",
          "It can move to any field which is not occupied. It can only defeat an enemy in "
              + "field range when the opposing player defeated any piece on their turn"),
      new Rules("King",
          "He can do the same as before but he has a banana. If he gets defeated once he "
              + "goes to jail with his banana.\bThe monkey can then free him but he will lose the "
              + "banana and won't get out of jail anymore."),
      new Rules("Queen",
          "Queen is still the queen but also has to be in jail to win the game, upon "
              + "defeating she goes to jail. The queen can not get free from jail."),
      new Rules("Jail",
          "It is on the right and left side of the board and is an extension to it! "
              + "The defeated king and/or queen is imprisoned, only those two go to jail upon defeat."));


  public Chess2PopUpRulesController() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/chess2"
        + "/Chess2PopUpRules.fxml"));

    try {
      loader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  public void initialize() {
    columnFigur.setCellValueFactory(new PropertyValueFactory<Rules, String>("Figures"));
    columnDescription.setCellValueFactory(new PropertyValueFactory<Rules, String>("Description"));
    myTableView.setItems(list);
  }
}
