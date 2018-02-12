package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;

public class Main extends Application {

    private final static int RDA_CALS = 150;
    private final static int RDA_FAT = 150;
    private final static int RDA_SATFAT = 150;
    private final static int RDA_CARBS = 150;
    private final static int RDA_SUGARS = 150;
    private final static int RDA_FIBRE = 150;
    private final static int RDA_PROTEIN = 150;
    private final static int RDA_SALT = 150;
    private static int currentIntake = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginF.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
