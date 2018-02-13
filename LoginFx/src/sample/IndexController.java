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
import java.util.ArrayList;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import javafx.scene.control.Alert;

public class IndexController {
    @FXML TextField caloriesText, fatText, satFatText, carbsText, sugarText, fibreText, proteinText, saltText;

    public void submitData(ActionEvent event) throws IOException {

        //Array will always be structured in the following order: [calories][fat][satfat][carbs][sugars][fibre][protein][salt][others]
        ArrayList<Double> breakdown = new ArrayList<>();
        FoodItem newFoodItem;
        double cals = 0;
        double fat = 0;
        double satFat = 0;
        double carbs = 0;
        double sugar = 0;
        double fibre = 0;
        double protein = 0;
        double salt = 0;

        //More efficient way of adding all the text boxes here, maybe need to rethink the data structure used
        //TODO try to find a better way of validating input here
        //Checking whether any of the inputs are null, if they are, display error message/prompt
        //TODO exception occurs somehwere after this point
        if (!(caloriesText.getText().equals("") || fatText.getText().equals("")|| satFatText.getText().equals("")|| carbsText.getText().equals("")
                || sugarText.getText().equals("")|| fibreText.getText().equals("")|| proteinText.getText().equals("")
                || saltText.getText().equals("")))
        {
            //TODO change these to Doubles as it's likely there will be some decimal point numbers inputted.
            try {
                //varibles for easy access
                cals = Double.parseDouble(caloriesText.getText());
                fat = Double.parseDouble(fatText.getText());
                satFat = Double.parseDouble(satFatText.getText());
                carbs = Double.parseDouble(carbsText.getText());
                sugar = Double.parseDouble(sugarText.getText());
                fibre = Double.parseDouble(fibreText.getText());
                protein = Double.parseDouble(proteinText.getText());
                salt = Double.parseDouble(saltText.getText());
                breakdown.add(cals);
                breakdown.add(fat);
                breakdown.add(satFat);
                breakdown.add(carbs);
                breakdown.add(sugar);
                breakdown.add(fibre);
                breakdown.add(protein);
                breakdown.add(salt);


            } catch (InputMismatchException e) {
                System.out.println("Error: Input mismatch!");
                System.out.println(e.getMessage());
            } catch (NumberFormatException g) {
                System.out.println("Error: Number format expection!");
                System.out.println(g.getMessage());
            }

            //Creation of new food item
            newFoodItem = new FoodItem(cals, fat, satFat, carbs, sugar, fibre, protein, salt);

            //TODO create a new window with a new controller that will function as the output window which displays results
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("OutputPage.fxml"));
            Parent outputView = loader.load();

            //access the controller and call the method
            OutputController controller = loader.getController();
            controller.startPrediction(newFoodItem);

            Scene newScene = new Scene(outputView);
            Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Food item adsvice");
            primaryStage.setScene(newScene);
            primaryStage.show();
            //end new window code

        } else {
            //Else show error message and wait
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input error!");
            alert.setHeaderText("Empty input value!");
            alert.setContentText("All fields are required for input, please enter a numerical value in each input box");
            alert.showAndWait();
        }
        doSomeStuff(breakdown);
    }

    private static void doSomeStuff(ArrayList<Double> input) {
        for (int i = 0; i < input.size(); i++) {
            System.out.println(input.get(i));
        }
    }
}
