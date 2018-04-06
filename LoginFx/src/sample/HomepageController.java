package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageController {

    @FXML
    Button start;

    public void startProgram(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("PersonalInput.fxml"));
            Parent outputView = loader.load();

            //access the controller and call the method
            PersonalInputController controller = loader.getController();
            controller.testingFunction();

            Scene newScene = new Scene(outputView);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Food item advice");
            primaryStage.setScene(newScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("IOException Caught!");
            alert.setHeaderText("Input Output Exception!");
            alert.setContentText("Number format exception! Please enter an Integer value into the name input" +
                    "box and resubmit the form!");
            alert.showAndWait();
        }

    }

    public void disclaimerDialog(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Disclaimer dialog");
        alert.setHeaderText("Software application disclaimer");
        alert.setContentText("Here holds the disclaimer information for the application.");
        alert.showAndWait();
    }
}
