package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonalInputController implements Initializable {
    @FXML
    Button submitButton;

    //Inputs from user
    @FXML
    TextField name;
    @FXML
    TextField age;
    @FXML
    ChoiceBox<String> goalCombo;
    @FXML
    ChoiceBox<String> activityCombo;
    @FXML
    ChoiceBox<String> genderCombo;
    @FXML
    VBox mainInput;
    @FXML
    AnchorPane anchor;

    //PersonalObject variable to pass to index method
    ProfileObject outputProfile;

    @FXML
    public void continueToNext(ActionEvent event) {
        try {
            //This section deals with calling the function of the other class and passing the relevant objects
            //If no user profile being passed back
            if (outputProfile == null) {
                loadInputsToObject();
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("index.fxml"));
            Parent outputView = loader.load();


            //access the controller and call the method
            IndexController controller = loader.getController();
            controller.loadProfileObject(outputProfile);

            Scene newScene = new Scene(outputView);
            Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Home");
            primaryStage.setScene(newScene);
            primaryStage.show();
            //end new window code
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOexception thrown and caught!");
        }
    }

    public void testingFunction() {
        goalCombo.setItems(FXCollections.observableArrayList("Lose","Maintain","Gain"));
        activityCombo.setItems(FXCollections.observableArrayList("Low","Med","High"));
        genderCombo.setItems(FXCollections.observableArrayList("Male","Female","Other"));
    }

    //Function that loads all the user inputs into a ProfileObject
    private void loadInputsToObject() {
        try {
            String inputName = name.getText();
            int inputAge = Integer.parseInt(age.getText());
            String inputGoal = goalCombo.getValue();
            String inputActivity = activityCombo.getValue();
            String inputGender = genderCombo.getValue();
            ActivityLevel activity = null;
            Gender gender = null;
            Goal goal = null;

            //switch statements that deal with the enums
            //Gender
            switch (inputGender) {
                case "Male":
                    gender = Gender.MALE;
                    break;
                case "Female":
                    gender = Gender.FEMALE;
                    break;
                case "Other":
                    gender = Gender.OTHER;
            }
            //Goal
            switch (inputGoal) {
                case "Lose":
                    goal = Goal.LOSE;
                    break;
                case "Maintain":
                    goal = Goal.MAINTAIN;
                    break;
                case "Gain":
                    goal = Goal.GAIN;
                    break;
            }
            //Activity
            switch (inputActivity) {
                case "Low":
                    activity = ActivityLevel.LOW;
                    break;
                case "Medium":
                    activity = ActivityLevel.MEDIUM;
                    break;
                case "High":
                    activity = ActivityLevel.HIGH;
                    break;
            }
            outputProfile = new ProfileObject(inputName,inputAge, gender, activity, goal);
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Number format exception!");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        mainInput = new VBox();
//        goalCombo = new ChoiceBox();
//        goalCombo.setItems(FXCollections.observableArrayList("Lose","Maintain","Gain"));
//        mainInput.getChildren().add(goalCombo);
//        anchor.getChildren().add(mainInput);
////        goalCombo.getItems().setAll("Lose", "Maintain", "Gain");
////        goalCombo.setPromptText("Testing");
////        goalCombo.setEditable(true);
//        activityCombo = new ChoiceBox(FXCollections.observableArrayList("Low","Med","High"));
////        activityCombo.getItems().setAll("Low", "Medium", "High");
////        activityCombo.setEditable(true);
//        genderCombo = new ChoiceBox(FXCollections.observableArrayList("Male","Female","Other"));
////        genderCombo.getItems().setAll("Male", "Female", "Other");
////        genderCombo.setEditable(true);
//

    }

    //TODO fix this, not passing the correct number TESTING CAL: n -> INDEX CLASS 0
    public void loadDataBackIntoProfile(ProfileObject person) {
        outputProfile = person;
        System.out.println("TESTING CAL" + outputProfile.getTotalIntakeCal());
        System.out.println("TESTING FAT" + outputProfile.getTotalIntakeFat());
        System.out.println("TESTING SAT" + outputProfile.getTotalIntakeSatFat());
    }

}
