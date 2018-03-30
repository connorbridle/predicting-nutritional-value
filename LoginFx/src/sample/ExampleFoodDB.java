package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ExampleFoodDB implements Initializable{
    @FXML
    private TableView<FoodItem> table;
    @FXML
    private TableColumn<FoodItem, String> nameColumn;
    @FXML
    private TableColumn<FoodItem, Double> calsColumn;
    @FXML
    private TableColumn<FoodItem, Double> fatColumn;
    @FXML
    private TableColumn<FoodItem, Double> satFatColumn;
    @FXML
    private TableColumn<FoodItem, Double> carbsColumn;
    @FXML
    private TableColumn<FoodItem, Double> sugarsColumn;
    @FXML
    private TableColumn<FoodItem, Double> fibreColumn;
    @FXML
    private TableColumn<FoodItem, Double> proteinColumn;
    @FXML
    private TableColumn<FoodItem, Double> saltColumn;
    @FXML
    private Button SubmitFoodButton;

    //Stores variable then passes it back
    private ProfileObject person;

    public void sampleFoods() {

    }

    //Function that gets all the food items from the stored csv file and returns them in an observable list
    public ObservableList<FoodItem> getFoodItem() {
        ObservableList<FoodItem> storedFoodItems = FXCollections.observableArrayList(); //Holds all the food items read from csv file

        //Opens the csv file and begins to work through it creating FoodItem objects on the fly
        File file = new File("/Users/connorbridle/Desktop/Third-Year-project/typ/LoginFx/src/sample/food_samples.csv");

        Scanner inputStream = null;
        try {
            inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String line = inputStream.next();
                String[] values = line.split(",");
                storedFoodItems.add(new FoodItem((String)values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]), Double.parseDouble(values[3]), Double.parseDouble(values[4]),
                        Double.parseDouble(values[5]), Double.parseDouble(values[6]), Double.parseDouble(values[7]), Double.parseDouble(values[8])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return storedFoodItems;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            //Sets up the columns in the table
            nameColumn.setCellValueFactory(new PropertyValueFactory<FoodItem, String>("itemName"));
            calsColumn.setCellValueFactory(new PropertyValueFactory<FoodItem, Double>("itemCals"));
            fatColumn.setCellValueFactory(new PropertyValueFactory<FoodItem, Double>("itemFat"));
            satFatColumn.setCellValueFactory(new PropertyValueFactory<FoodItem, Double>("itemSatFat"));
            carbsColumn.setCellValueFactory(new PropertyValueFactory<FoodItem, Double>("itemCarbs"));
            sugarsColumn.setCellValueFactory(new PropertyValueFactory<FoodItem, Double>("itemSugar"));
            fibreColumn.setCellValueFactory(new PropertyValueFactory<FoodItem, Double>("itemFibre"));
            proteinColumn.setCellValueFactory(new PropertyValueFactory<FoodItem, Double>("itemProtein"));
            saltColumn.setCellValueFactory(new PropertyValueFactory<FoodItem, Double>("itemSodium"));
            table.setItems(getFoodItem());
        } catch (IllegalStateException e) {
            e.printStackTrace();
            System.out.println("ded");
        }
    }

    //Load data function
    public void loadData(ProfileObject inputPerson) {
        person = inputPerson;
    }

    //Function that deals with the submit food item button. Opens a new index stage and then sends over the values to relevant controller
    @FXML
    private void submitFoodItemButton(ActionEvent event) {
        FoodItem foodItemVariable = table.getItems().get(table.getSelectionModel().getFocusedIndex()); //Stores the selected food item in a variable
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("index.fxml"));
            Parent outputView = loader.load();

            //access the controller and call the method
            IndexController controller = loader.getController();
            controller.loadProfileObject(person);
            controller.loadFoodItem(foodItemVariable);

            Scene newScene = new Scene(outputView);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Food item advice");
            primaryStage.setScene(newScene);
            primaryStage.show();
            //end new window code
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
