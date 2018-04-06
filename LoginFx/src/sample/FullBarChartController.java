package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FullBarChartController {

    @FXML
    VBox myBox;

    double[] macroInputValues = new double[8];
    private static List<String> outputRDA;

    //Storing things to pass back
    private static FoodItem outputFooditem;
    private static DecisionObject outputDecision;
    private static ProfileObject outputProfile;

    public void takeData(List<String> rdaRow, FoodItem food, ProfileObject profile, DecisionObject decision) {
        outputFooditem = food;
        outputProfile = profile;
        outputDecision = decision;
        outputRDA = rdaRow;

        //Storing in an array for easy access
        macroInputValues[0] = outputFooditem.getItemCals();
        macroInputValues[1] = outputFooditem.getItemFat();
        macroInputValues[2] = outputFooditem.getItemSatFat();
        macroInputValues[3] = outputFooditem.getItemCarbs();
        macroInputValues[4] = outputFooditem.getItemSugar();
        macroInputValues[5] = outputFooditem.getItemFibre();
        macroInputValues[6] = outputFooditem.getItemProtein();
        macroInputValues[7] = outputFooditem.getItemSodium();

        createGraph();
    }

    //Function that creates the graph
    private void createGraph() {
        //Creation of the axis
        CategoryAxis xAx = new CategoryAxis();
        xAx.setLabel("Macro Intakes");
        NumberAxis yAx = new NumberAxis();
        yAx.setLabel("Amount");

        //Creation of bar chart
        BarChart macroBarChart = new BarChart(xAx,yAx);

        XYChart.Series myDataSeries = new XYChart.Series();
        XYChart.Series myDataSeries2 = new XYChart.Series();
        myDataSeries.setName("Recommended Intake");
        myDataSeries2.setName("Food Item Value per 100g");

        myDataSeries.getData().add(new XYChart.Data("Calories (kcal)", Double.parseDouble(outputRDA.get(1))));
        myDataSeries.getData().add(new XYChart.Data("Fat (g)", Double.parseDouble(outputRDA.get(2))));
        myDataSeries.getData().add(new XYChart.Data("Saturated Fat (g)", Double.parseDouble(outputRDA.get(3))));
        myDataSeries.getData().add(new XYChart.Data("Carbohydrates (g)", Double.parseDouble(outputRDA.get(4))));
        myDataSeries.getData().add(new XYChart.Data("Sugars (g)", Double.parseDouble(outputRDA.get(5))));
        myDataSeries.getData().add(new XYChart.Data("Fibre (g)", Double.parseDouble(outputRDA.get(6))));
        myDataSeries.getData().add(new XYChart.Data("Protein (g)", Double.parseDouble(outputRDA.get(7))));
        myDataSeries.getData().add(new XYChart.Data("Salt (g)", Double.parseDouble(outputRDA.get(8))));

        //FoodItemInput
        myDataSeries2.getData().add(new XYChart.Data("Calories (kcal)", macroInputValues[0]));
        myDataSeries2.getData().add(new XYChart.Data("Fat (g)", macroInputValues[1]));
        myDataSeries2.getData().add(new XYChart.Data("Saturated Fat (g)", macroInputValues[2]));
        myDataSeries2.getData().add(new XYChart.Data("Carbohydrates (g)", macroInputValues[3]));
        myDataSeries2.getData().add(new XYChart.Data("Sugars (g)", macroInputValues[4]));
        myDataSeries2.getData().add(new XYChart.Data("Fibre (g)", macroInputValues[5]));
        myDataSeries2.getData().add(new XYChart.Data("Protein (g)", macroInputValues[6]));
        myDataSeries2.getData().add(new XYChart.Data("Salt (g)", macroInputValues[7]));



        macroBarChart.getData().add(myDataSeries); //Add data to the bar chart
        macroBarChart.getData().add(myDataSeries2); //Add data to the bar chart
        myBox.getChildren().add(macroBarChart); //Adds chart to anchor pane
    }

    public void returnToOutput(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("DetailedResults.fxml"));
            Parent outputView = loader.load();

            //access the controller and call the method
            DetailedResultsController controller = loader.getController();
            controller.storeValues(outputDecision, outputFooditem, outputProfile, outputRDA);

            Scene newScene = new Scene(outputView);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Home");
            primaryStage.setScene(newScene);
            primaryStage.show();
            //end new window code
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

