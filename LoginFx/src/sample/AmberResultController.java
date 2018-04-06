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
import java.util.List;

public class AmberResultController {
    private static DecisionObject outputDecision;
    private static FoodItem outputFoodItem;
    private static ProfileObject outputProfile;
    private static List<String> outputRDA;

    @FXML
    BorderPane myPane;
    @FXML
    Button continueButton;

    @FXML
    public void continueToResults(ActionEvent event) {
        try {
            //New window code
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("DetailedResults.fxml"));
            Parent outputView = loader.load();

            //access the controller and call the method
            DetailedResultsController controller = loader.getController();
            controller.storeValues(outputDecision, outputFoodItem, outputProfile, outputRDA);

            Scene newScene = new Scene(outputView);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Detailed Results");
            primaryStage.setScene(newScene);
            primaryStage.show();
            //end new window code
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("IOException caught!");
            alert.setHeaderText("Input-Output Exception!");
            alert.setContentText("An input-output exception was thrown and caught by the program.");
            alert.showAndWait();
        }
    }

    public void storeValues(DecisionObject decision, FoodItem food, ProfileObject person, List<String> currentRow) {
        outputFoodItem = food;
        outputDecision = decision;
        outputProfile = person;
        outputRDA = currentRow;
    }

    //Function that takes you back to the starting point
    public void returnToStart(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("index.fxml"));
            Parent outputView = loader.load();

            //access the controller and call the method
            IndexController controller = loader.getController();
            controller.loadProfileObject(outputProfile);

            Scene newScene = new Scene(outputView);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Food item advice");
            primaryStage.setScene(newScene);
            primaryStage.show();
            //end new window code
        } catch (IOException ex) {
            System.out.println("Input output exception caught!");
            System.out.println(ex.getStackTrace());
        }
    }
}
