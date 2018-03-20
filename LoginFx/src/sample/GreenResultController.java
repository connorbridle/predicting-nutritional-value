package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.IOException;

public class GreenResultController {

    @FXML
    Button continueButton;

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
}
