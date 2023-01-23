package de.hhn.it.devtools.javafx.minesweeper.helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ViewSwitchHelper {

    private static Scene scene;
    private static BorderPane borderPane;

    public static void setScene(Scene scene2Set) {
        ViewSwitchHelper.scene = scene2Set;
    }

    public static void setMainBorderPane(BorderPane borderPane) {
        ViewSwitchHelper.borderPane = borderPane;
    }

    public static void switchTo(String filename) throws IOException {
        try {
            Parent root = FXMLLoader.load(ViewSwitchHelper.class.getResource("/fxml/"
                    + filename + ".fxml"));

//          scene.setRoot(root);
            borderPane.getChildren().clear();
            borderPane.getChildren().add(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
