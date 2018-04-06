package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DetailedResultsController2 {

    private static ProfileObject outputProfile; //Profile to be passed back to index
    private static FoodItem outputFoodItem; //For use on this stage only, when the restart button is passed
    private static DecisionObject outputDecision; //For use on this stage only,
    private static List<String> outputRDA;

    @FXML
    VBox generalCom;

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

    public void storeValues(DecisionObject decision, FoodItem food, ProfileObject profile) {
        outputProfile = profile;
        outputFoodItem = food;
        outputDecision = decision;
        populateTableWithValues();
    }

    private void populateTableWithValues() {
        //================================================================================
        // When called, populates the table with the relevant comments from the General comments array
        //================================================================================
        if (outputDecision.getGeneralCommentsComments().size() == 0) {
            Label label = new Label("No general comments!");
            generalCom.getChildren().add(label);
        } else {
            //Loop through the comments array and add a label for each one found
            for (int i = 0; i < outputDecision.getGeneralCommentsComments().size(); i++) {
                Label label = new Label(outputDecision.getGeneralCommentsComments().get(i));
                generalCom.setSpacing(30);
                generalCom.getChildren().add(label);
            }
        }
    }

    public void returnToPrevious(ActionEvent event) {
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
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("IOException caught!");
            alert.setHeaderText("Input-Output Exception!");
            alert.setContentText("An input-output exception was thrown and caught by the program.");
            alert.showAndWait();
        }
    }
}
