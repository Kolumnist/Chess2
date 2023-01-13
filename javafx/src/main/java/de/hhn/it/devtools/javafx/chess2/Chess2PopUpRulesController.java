package de.hhn.it.devtools.javafx.chess2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Chess2PopUpRulesController {


  @FXML
  private TableColumn<Rules, String> columnDescription;

  @FXML
  private TableColumn<Rules, String> columnFigur;

  @FXML
  private TableView<Rules> myTableView;

  ObservableList<Rules> list = FXCollections.observableArrayList(

      new Rules("Fish",
          "He can walk 1 field to the right, legft or diagonally forward. He can only\n "
              + "defeat enemies forward diagonal.\bThe fish becomes a fishqueen by reaching the other end of the board.\n"),
      new Rules("Fishqueen",
          "Its the same as the queen but doesn't go to jail.\n"),
      new Rules("Elephant",
          "He moves 2 fields only and defeats enemies standing on the second field.\n "
              + "He can move in every direction.\n"),
      new Rules("Bear",
          "Sits in the middle of the chessboard. It can move 1 field in every direction\n "
              + "and both players can move him on their turns. It can defeated by both.\bIt is a\n "
              + "friendly bear and can't defeat anyone on his own but if he gets defeated the piece\n "
              + "which defeated him gets defeated as well.\n"),
      new Rules("Monkey",
          "He can move 1 field in every direction and jumps over other pieces once, after\n "
              + "jumping he can jump again if there is another piece which he hasn't jumped over yet.\n "
              + "He can only defeat an opposing piece when he jumps over another piece. When the King\n "
              + "is in jail and still has his banana, the monkey is able to free the King if another piece\n "
              + "is positioned so that the monkey lands on the field next to the king after jumping over the\n "
              + "positioned piece. The monkey frees the king, takes the banana and lands back to the field before\n "
              + "he was jumping over the positioned piece and the king lands next to the jail where the monkey freed him.\n"),
      new Rules("Crow",
          "It can move to any field which is not occupied. It can only defeat an enemy in\n "
              + "field range when the opposing player defeated any piece on their turn\n"),
      new Rules("King",
          "He can do the same as before but he has a banana. If he gets defeated once he\n "
              + "goes to jail with his banana.\bThe monkey can then free him but he will lose the\n "
              + "banana and won't get out of jail anymore.\n"),
      new Rules("Queen",
          "Queen is still the queen but also has to be in jail to win the game, upon\n "
              + "defeating she goes to jail. The queen can not get free from jail\n"),
      new Rules("Jail",
          "It is on the right and left side of the board and is an extension to it!\n "
              + "The defeated king and/or queen is imprisoned, only those two go to jail upon defeat.\n"));


  @FXML
  public void initialize() {
    Stage dialog = new Stage();
    columnFigur.setCellValueFactory(new PropertyValueFactory<Rules, String>("Figures"));
    columnDescription.setCellValueFactory(new PropertyValueFactory<Rules, String>("Description"));
    myTableView.setFixedCellSize(Region.USE_COMPUTED_SIZE);
    myTableView.setItems(list);
    VBox dialogVbox = new VBox(50);
    dialogVbox.getChildren().add(myTableView);
    Scene dialogScene = new Scene(dialogVbox, 1000, 1000);
    dialog.setScene(dialogScene);
    dialog.show();

  }
}
