package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;

public class PersonalInputController {
    @FXML
    Button submitButton;

    @FXML
    public void continueToNext(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("index.fxml"));
            submitButton.getScene().setRoot(root);
        } catch (IOException e) {

        }
    }
}
