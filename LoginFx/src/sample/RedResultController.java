package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RedResultController {
    FoodItem outputFoodItem;
    DecisionObject outputDecision;
    @FXML
    BorderPane myPane;

    @FXML
    FoodItem inputtedFoodItem;
    @FXML
    Button continueButton;

    @FXML
    public void continueToResults(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DetailedResults.fxml"));
            continueButton.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("IOException caught!");
            alert.setHeaderText("Input-Output Exception!");
            alert.setContentText("An input-output exception was thrown and caught by the program.");
            alert.showAndWait();
        }
    }

    //TODO add person object
    public void storeValues(DecisionObject decision, FoodItem food) {
        outputFoodItem = food;
        outputDecision = decision;
    }
}
