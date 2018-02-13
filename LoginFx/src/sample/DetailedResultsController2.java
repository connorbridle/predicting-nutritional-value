package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DetailedResultsController2 {

    //Function that takes you back to the starting point
    public void returnToStart(ActionEvent event) {
        try {
            Parent returnView = FXMLLoader.load(getClass().getResource("index.fxml"));
            Scene newScene = new Scene(returnView);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Home");
            primaryStage.setScene(newScene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println("Input output exception caught!");
            System.out.println(ex.getStackTrace());
        }
    }
}
