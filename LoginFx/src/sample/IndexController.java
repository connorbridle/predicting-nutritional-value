package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.IOException;

public class IndexController {
    @FXML
    TextField caloriesText, fatText, satFatText, carbsText, sugarText, fibreText, proteinText, saltText;
    @FXML
    Button sampleFoodButton;
    @FXML
    TableView table;

    Stage window;

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
                //TODO exception occurs somewhere after this point
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

            //TODO add a food name input box and then take a foodname into this constructor instead of a string
            String jacksGay = "Jack's packing god dammit";
            //Creation of new food item
            newFoodItem = new FoodItem(jacksGay, cals, fat, satFat, carbs, sugar, fibre, protein, salt);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("RedResultOuput.fxml"));
            Parent outputView = loader.load();

            //access the controller and call the method
            RedResultController controller = loader.getController();
            controller.startPrediction(newFoodItem);

            Scene newScene = new Scene(outputView);
            Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Food item advice");
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

    public void sampleFoods() {
        try{
            //Creation of rows for the table
            TableColumn<FoodItem, String> nameColumn = new TableColumn<>("Food Name");
            nameColumn.setMinWidth(200);
            nameColumn.setCellValueFactory(new PropertyValueFactory<FoodItem, String>("itemName"));
            //Calories
            TableColumn<FoodItem, Double> calsColumn = new TableColumn<>("Calories");
            calsColumn.setMinWidth(100);
            calsColumn.setCellValueFactory(new PropertyValueFactory<FoodItem, Double>("itemCals"));
            //Fat
            TableColumn<FoodItem, Double> fatColumn = new TableColumn<>("Fat");
            fatColumn.setMinWidth(100);
            fatColumn.setCellValueFactory(new PropertyValueFactory<FoodItem, Double>("itemFat"));
            //Saturated Fat
            TableColumn<FoodItem, Double> satFatColumn = new TableColumn<>("Saturated Fat");
            satFatColumn.setMinWidth(100);
            satFatColumn.setCellValueFactory(new PropertyValueFactory<FoodItem, Double>("itemSatFat"));
            //Carbohydrates
            TableColumn<FoodItem, Double> carbsColumn = new TableColumn<>("Carbohydrates");
            carbsColumn.setMinWidth(100);
            carbsColumn.setCellValueFactory(new PropertyValueFactory<FoodItem, Double>("itemCarbs"));
            //Sugars
            TableColumn<FoodItem, Double> sugarsColumn = new TableColumn<>("Sugars");
            sugarsColumn.setMinWidth(100);
            sugarsColumn.setCellValueFactory(new PropertyValueFactory<FoodItem, Double>("itemSugar"));
            //Fibre
            TableColumn<FoodItem, Double> fibreColumn = new TableColumn<>("Fibre");
            fibreColumn.setMinWidth(100);
            fibreColumn.setCellValueFactory(new PropertyValueFactory<FoodItem, Double>("itemFibre"));
            //Protein
            TableColumn<FoodItem, Double> proteinColumn = new TableColumn<>("Protein");
            proteinColumn.setMinWidth(100);
            proteinColumn.setCellValueFactory(new PropertyValueFactory<FoodItem, Double>("itemProtein"));
            //Salt
            TableColumn<FoodItem, Double> saltColumn = new TableColumn<>("Salt");
            saltColumn.setMinWidth(100);
            saltColumn.setCellValueFactory(new PropertyValueFactory<FoodItem, Double>("itemSodium"));

            table = new TableView<>();
            table.setItems(getFoodItem()); //Returns an observable list of items
            table.getColumns().addAll(nameColumn, calsColumn, fatColumn, satFatColumn, carbsColumn, sugarsColumn, fibreColumn, proteinColumn, saltColumn); //Adds all the columns to the table


            Parent root = FXMLLoader.load(getClass().getResource("SampleFood.fxml")); //Get the Sample food root
            sampleFoodButton.getScene().setRoot(root); //TODO null pointer
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Function that gets all the food items from the stored csv file and returns them in an observable list
    public ObservableList<FoodItem> getFoodItem() {
        ObservableList<FoodItem> storedFoodItems = FXCollections.observableArrayList(); //Holds all the food items read from csv file

        //Opens the csv file and begins to work through it creating FoodItem objects on the fly
        File file = new File("C:\\Users\\Connor\\Desktop\\Third-year-project\\typ\\LoginFx\\src\\sample\\food_samples.csv");

        Scanner inputStream = null;
        try {
            inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String line = inputStream.next();
                String[] values = line.split(",");
                storedFoodItems.add(new FoodItem(values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]), Double.parseDouble(values[3]), Double.parseDouble(values[4]),
                        Double.parseDouble(values[5]), Double.parseDouble(values[6]), Double.parseDouble(values[7]), Double.parseDouble(values[8])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return storedFoodItems;
    }


}
