package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DetailedResultsController {

    private FoodItem outputFoodItem;
    private DecisionObject outputDecision;

    public void continueToNext(ActionEvent event) {
        try {
            Parent detailedViewNext = FXMLLoader.load(getClass().getResource("DetailedResults2.fxml"));
            Scene newScene = new Scene(detailedViewNext);
            Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Detailed Results Breakdown");
            primaryStage.setScene(newScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Input output exception!");
            System.out.println(e.getStackTrace());
        }
    }
    //TODO add person object
    public void storeValues(DecisionObject decision, FoodItem food) {
        outputFoodItem = food;
        outputDecision = decision;
    }

}
