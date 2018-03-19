package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RedResultController {

    @FXML
    BorderPane myPane;

    @FXML
    FoodItem inputtedFoodItem;

    @FXML
    public void continueToResults(ActionEvent event) {
        try {
            Parent resultsContinueView = FXMLLoader.load(getClass().getResource("OutputPage.fxml"));
            Scene newScene = new Scene(resultsContinueView);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Results");
            primaryStage.setScene(newScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void startPrediction(FoodItem foodItem) {
        inputtedFoodItem = foodItem;
    }
}
