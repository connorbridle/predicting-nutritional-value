package sample;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

import javax.swing.*;
import java.io.IOException;

public class Controller {

    @FXML
    private Label lblStatus; private Label testLabel;

    @FXML
    private TextField textUsername;

    @FXML
    private  TextField textPassword;



    public void login(ActionEvent event) throws IOException {
        if (textUsername.getText().equals("username") && textPassword.getText().equals("password")) {
            Parent newView = FXMLLoader.load(getClass().getResource("index.fxml"));
            Scene newScene = new Scene(newView);
            Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(newScene);
            primaryStage.show();
        } else {
            //do something else
        }
    }
}
