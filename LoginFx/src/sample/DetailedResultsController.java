package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DetailedResultsController implements Initializable {

    private static FoodItem outputFoodItem;
    private static DecisionObject outputDecision;
    private static ProfileObject outputProfile;
    private static List<String> outputRDA;

    @FXML
    Button calsButton, fatButton, satFatButton, carbsButton, sugarsButton, fibreButton, proteinButton, saltButton;
    @FXML
    Label calsLabel, fatLabel, satFatLabel, carbsLabel, sugarsLabel, fibreLabel, proteinLabel, saltLabel;
    @FXML
    VBox calsCom, fatCom, satFatCom, carbsCom, sugarsCom, fibreCom, proteinCom, saltCom;
    @FXML
    ScrollPane calsScroll;
    @FXML
    GridPane grid;

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
    public void storeValues(DecisionObject decision, FoodItem food, ProfileObject person, List<String> currentRow) {
        outputFoodItem = food;
        outputDecision = decision;
        outputProfile = person;
        outputRDA = currentRow;
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
            TextArea newText = new TextArea(outputDecision.getCalsComments().get(i));
            newText.setWrapText(true);
            newText.setEditable(false);
            newText.setFocusTraversable(false);
            newText.setMinWidth(300);
            newText.setPrefWidth(300);
            newText.setMaxWidth(300);
            calsCom.getChildren().add(newText);
        }
        //Fat comments
        for (int i = 0; i < outputDecision.getFatComments().size(); i++) {
            TextArea newText = new TextArea(outputDecision.getFatComments().get(i));
            newText.setWrapText(true);
            newText.setEditable(false);
            newText.setFocusTraversable(false);
            newText.setMinWidth(300);
            newText.setPrefWidth(300);
            newText.setMaxWidth(300);
            fatCom.getChildren().add(newText);
        }
        //Saturated Fat comments
        for (int i = 0; i < outputDecision.getSatFatComments().size(); i++) {
            TextArea newText = new TextArea(outputDecision.getSatFatComments().get(i));
            newText.setWrapText(true);
            newText.setEditable(false);
            newText.setFocusTraversable(false);
            newText.setMinWidth(300);
            newText.setPrefWidth(300);
            newText.setMaxWidth(300);
            satFatCom.getChildren().add(newText);
        }
        //Carbohydrates comments
        for (int i = 0; i < outputDecision.getCarbsComments().size(); i++) {
            TextArea newText = new TextArea(outputDecision.getCarbsComments().get(i));
            newText.setWrapText(true);
            newText.setEditable(false);
            newText.setFocusTraversable(false);
            newText.setMinWidth(300);
            newText.setPrefWidth(300);
            newText.setMaxWidth(300);
            carbsCom.getChildren().add(newText);
        }
        //Sugars comments
        for (int i = 0; i < outputDecision.getSugarsComments().size(); i++) {
            TextArea newText = new TextArea(outputDecision.getSugarsComments().get(i));
            newText.setWrapText(true);
            newText.setEditable(false);
            newText.setFocusTraversable(false);
            newText.setMinWidth(300);
            newText.setPrefWidth(300);
            newText.setMaxWidth(300);
            sugarsCom.getChildren().add(newText);
        }
        //fibre comments
        for (int i = 0; i < outputDecision.getFibreComments().size(); i++) {
            TextArea newText = new TextArea(outputDecision.getFibreComments().get(i));
            newText.setWrapText(true);
            newText.setEditable(false);
            newText.setFocusTraversable(false);
            newText.setMinWidth(300);
            newText.setPrefWidth(300);
            newText.setMaxWidth(300);
            fibreCom.getChildren().add(newText);
        }
        //protein comments
        for (int i = 0; i < outputDecision.getProteinComments().size(); i++) {
            TextArea newText = new TextArea(outputDecision.getProteinComments().get(i));
            newText.setWrapText(true);
            newText.setEditable(false);
            newText.setFocusTraversable(false);
            newText.setMinWidth(300);
            newText.setPrefWidth(300);
            newText.setMaxWidth(300);
            proteinCom.getChildren().add(newText);
        }
        //Salt comments
        for (int i = 0; i < outputDecision.getSaltComments().size(); i++) {
            TextArea newText = new TextArea(outputDecision.getSaltComments().get(i));
            newText.setWrapText(true);
            newText.setEditable(false);
            newText.setFocusTraversable(false);
            newText.setMinWidth(300);
            newText.setPrefWidth(300);
            newText.setMaxWidth(300);
            saltCom.getChildren().add(newText);
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
            controller.storeValues(outputDecision, outputFoodItem, outputProfile, outputRDA);

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
            controller.storeValues(outputDecision, outputFoodItem, outputProfile, outputRDA);

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
            controller.storeValues(outputDecision, outputFoodItem, outputProfile, outputRDA);

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

    //Function that opens up the graph view
    public void openGraphView(ActionEvent event, int index) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("BarChart.fxml"));
            Parent outputView = loader.load();

            //access the controller and call the method
            BarChartController controller = loader.getController();
            controller.takeData(outputRDA,outputFoodItem, outputProfile, outputDecision, index);

            Scene newScene = new Scene(outputView);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Output Page");
            primaryStage.setScene(newScene);
            primaryStage.show();
            //end new window code
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openFullGraphView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FullBarChart.fxml"));
            Parent outputView = loader.load();

            //access the controller and call the method
            FullBarChartController controller = loader.getController();
            controller.takeData(outputRDA,outputFoodItem, outputProfile, outputDecision);

            Scene newScene = new Scene(outputView);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Full Input Comparison");
            primaryStage.setScene(newScene);
            primaryStage.show();
            //end new window code
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Functions that deal with each of the buttons in the graph, pass the relevant index that will be used in the rda list
    public void calsButton(ActionEvent event) {
        int index = 1;
        openGraphView(event,index);
    }
    public void fatButton(ActionEvent event) {
        int index = 2;
        openGraphView(event,index);
    }
    public void satFatButton(ActionEvent event) {
        int index = 3;
        openGraphView(event,index);
    }
    public void carbsButton(ActionEvent event) {
        int index = 4;
        openGraphView(event,index);
    }
    public void sugarsButton(ActionEvent event) {
        int index = 5;
        openGraphView(event,index);
    }
    public void fibreButton(ActionEvent event) {
        int index = 6;
        openGraphView(event,index);
    }
    public void proteinButton(ActionEvent event) {
        int index = 7;
        openGraphView(event,index);
    }
    public void saltButton(ActionEvent event) {
        int index = 8;
        openGraphView(event,index);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setGridLinesVisible(true);
        calsScroll = new ScrollPane();
        calsScroll.setMaxWidth(400);
    }
}
