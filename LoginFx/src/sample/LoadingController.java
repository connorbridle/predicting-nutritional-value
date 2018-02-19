package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/*
Class that performs all the main calculations required for the output page,
and then passes the data to the output page
*/

public class LoadingController {

    public void cancelLoadingAndReturn(ActionEvent event) {
        try {
            Parent cancelView = FXMLLoader.load(getClass().getResource("index.fxml"));
            Scene newScene = new Scene(cancelView);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Home");
            primaryStage.setScene(newScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
