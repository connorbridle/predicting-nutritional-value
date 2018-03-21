package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("PersonalInput.fxml"));
        primaryStage.setTitle("Home");
        Scene newScene = new Scene(root);
        primaryStage.setScene(newScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
