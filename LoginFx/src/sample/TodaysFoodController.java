package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TodaysFoodController implements Initializable {

    @FXML
    Button backButton;
    @FXML
    TableView<FoodItem> table;
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

    //Function that returns to the homepage
    public void goBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("index.fxml")); //Get the Sample food root
            backButton.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<FoodItem> getFoodItem() {
        ObservableList<FoodItem> storedFoodItems = FXCollections.observableArrayList(); //Holds all the food items read from csv file

        //Opens the csv file and begins to work through it creating FoodItem objects on the fly
        File file = new File("/Users/connorbridle/Desktop/Third-Year-project/typ/LoginFx/src/sample/recordedFoodsToday.csv");

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
    }
}
