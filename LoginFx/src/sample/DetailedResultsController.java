package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DetailedResultsController {

    private static FoodItem outputFoodItem;
    private static DecisionObject outputDecision;
    private static ProfileObject outputProfile;

    @FXML
    Label calsLabel, fatLabel, satFatLabel, carbsLabel, sugarsLabel, fibreLabel, proteinLabel, saltLabel;
    @FXML
    VBox calsCom, fatCom, satFatCom, carbsCom, sugarsCom, fibreCom, proteinCom, saltCom;

    public void continueToNext(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("DetailedResults2.fxml"));
            Parent outputView = loader.load();

            //access the controller and call the method
            DetailedResultsController2 controller = loader.getController();
            controller.storeValues(outputDecision, outputFoodItem, outputProfile);

            Scene newScene = new Scene(outputView);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Food item advice");
            primaryStage.setScene(newScene);
            primaryStage.show();
            //end new window code
        } catch (IOException e) {
            System.out.println("Input output exception!");
            System.out.println(e.getStackTrace());
        }
    }
    //TODO add person object
    public void storeValues(DecisionObject decision, FoodItem food, ProfileObject person) {
        outputFoodItem = food;
        outputDecision = decision;
        outputProfile = person;
        //Populate table with comments
        populateTableWithValues();
    }

    private void populateTableWithValues() {
        //Sets the food item information row in the table using the fxml labels
        calsLabel.setText(Double.toString(outputFoodItem.itemCals.get()));
        fatLabel.setText(Double.toString(outputFoodItem.itemFat.get()));
        satFatLabel.setText(Double.toString(outputFoodItem.itemSatFat.get()));
        carbsLabel.setText(Double.toString(outputFoodItem.itemCarbs.get()));
        sugarsLabel.setText(Double.toString(outputFoodItem.itemSugar.get()));
        fibreLabel.setText(Double.toString(outputFoodItem.itemFibre.get()));
        proteinLabel.setText(Double.toString(outputFoodItem.itemProtein.get()));
        saltLabel.setText(Double.toString(outputFoodItem.itemSodium.get()));

        //================================================================================
        // On initialisation, goes through each of the comments and adds labels to the vbox
        //================================================================================

        //If any of the comment lists are empty, add the string "No comments"
        if (outputDecision.getCalsComments().size() == 0) {
            outputDecision.getCalsComments().add("No comments!");
        }
        if (outputDecision.getFatComments().size() == 0) {
            outputDecision.getFatComments().add("No comments!");
        }
        if (outputDecision.getSatFatComments().size() == 0) {
            outputDecision.getSatFatComments().add("No comments!");
        }
        if (outputDecision.getCarbsComments().size() == 0) {
            outputDecision.getCarbsComments().add("No comments!");
        }
        if (outputDecision.getSugarsComments().size() == 0) {
            outputDecision.getSugarsComments().add("No comments!");
        }
        if (outputDecision.getFibreComments().size() == 0) {
            outputDecision.getFibreComments().add("No comments!");
        }
        if (outputDecision.getProteinComments().size() == 0) {
            outputDecision.getProteinComments().add("No comments!");
        }
        if (outputDecision.getSaltComments().size() == 0) {
            outputDecision.getSaltComments().add("No comments!");
        }


        //Calories comments
        for (int i = 0; i < outputDecision.getCalsComments().size(); i++) {
            calsCom.getChildren().add(new Label(outputDecision.getCalsComments().get(i)));
        }
        //Fat comments
        for (int i = 0; i < outputDecision.getFatComments().size(); i++) {
            fatCom.getChildren().add(new Label(outputDecision.getFatComments().get(i)));
        }
        //Saturated Fat comments
        for (int i = 0; i < outputDecision.getSatFatComments().size(); i++) {
            Label label = new Label(outputDecision.getSatFatComments().get(i));
            label.setMaxHeight(500);
            label.setWrapText(true);
            label.setTextAlignment(TextAlignment.JUSTIFY);
            satFatCom.getChildren().add(new Label("TESTING@£$"));
            satFatCom.getChildren().add(label);
        }
        //Carbohydrates comments
        for (int i = 0; i < outputDecision.getCarbsComments().size(); i++) {
            carbsCom.getChildren().add(new Label(outputDecision.getCarbsComments().get(i)));
        }
        //Sugars comments
        for (int i = 0; i < outputDecision.getSugarsComments().size(); i++) {
            sugarsCom.getChildren().add(new Label(outputDecision.getSugarsComments().get(i)));
        }
        //fibre comments
        for (int i = 0; i < outputDecision.getFibreComments().size(); i++) {
            fibreCom.getChildren().add(new Label(outputDecision.getFibreComments().get(i)));
        }
        //protein comments
        for (int i = 0; i < outputDecision.getProteinComments().size(); i++) {
            proteinCom.getChildren().add(new Label(outputDecision.getProteinComments().get(i)));
        }
        //Salt comments
        for (int i = 0; i < outputDecision.getSaltComments().size(); i++) {
            saltCom.getChildren().add(new Label(outputDecision.getSaltComments().get(i)));
        }

    }

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

    //Function that returns to the previous screen
    public void returnToPrevious(ActionEvent event) {
        if (outputDecision.getDecisionString().equals("red")) {
            returnRed(event);
        } else if (outputDecision.getDecisionString().equals("amber")) {
            returnAmber(event);
        } else if (outputDecision.getDecisionString().equals("green")) {
            returnGreen(event);
        }
    }

    //Return to red
    private void returnRed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("RedResultOuput.fxml"));
            Parent outputView = loader.load();

            //access the controller and call the method
            RedResultController controller = loader.getController();
            controller.storeValues(outputDecision, outputFoodItem, outputProfile);

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
    //Return to amber
    private void returnAmber(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AmberResultOutput.fxml"));
            Parent outputView = loader.load();

            //access the controller and call the method
            AmberResultController controller = loader.getController();
            controller.storeValues(outputDecision, outputFoodItem, outputProfile);

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

    //Return to green
    private void returnGreen(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("GreenResultOutput.fxml"));
            Parent outputView = loader.load();

            //access the controller and call the method
            GreenResultController controller = loader.getController();
            controller.storeValues(outputDecision, outputFoodItem, outputProfile);

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
}
