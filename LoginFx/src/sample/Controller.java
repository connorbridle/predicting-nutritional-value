package sample;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    @FXML
    private Button myButton;



    public void login()  {
        if (textUsername.getText().equals("username") && textPassword.getText().equals("password")) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("index.fxml"));
                myButton.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            //do something else
        }
    }

}
