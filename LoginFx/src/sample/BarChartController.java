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

public class BarChartController {

    @FXML
    VBox myBox;

    private static String macroInput = "";
    private static double RDAInput = 0.0;
    private static double actualInput = 0.0;
    private static int testing;
    private static List<String> outputRDA;

    //Storing things to pass back
    private static FoodItem outputFooditem;
    private static DecisionObject outputDecision;
    private static ProfileObject outputProfile;

    public void takeData(List<String> rdaRow, FoodItem food, ProfileObject profile, DecisionObject decision, int macroIndex) {
        outputFooditem = food;
        outputProfile = profile;
        outputDecision = decision;
        outputRDA = rdaRow;
        RDAInput = Double.parseDouble(outputRDA.get(macroIndex));
        testing = macroIndex-1;

        //Deciding which macro
        switch (testing) {
            case 0:
                actualInput = outputFooditem.getItemCals();
                break;
            case 1:
                actualInput = outputFooditem.getItemFat();
                break;
            case 2:
                actualInput = outputFooditem.getItemSatFat();
                break;
            case 3:
                actualInput = outputFooditem.getItemCarbs();
                break;
            case 4:
                actualInput = outputFooditem.getItemSugar();
                break;
            case 5:
                actualInput = outputFooditem.getItemFibre();
                break;
            case 6:
                actualInput = outputFooditem.getItemProtein();
                break;
            case 7:
                actualInput = outputFooditem.getItemSodium();
                break;
        }

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
//        myDataSeries.setName("RDA value");
//        myDataSeries2.setName("Actual Value");
        myDataSeries.getData().add(new XYChart.Data("Recommended Intake", RDAInput));
        myDataSeries2.getData().add(new XYChart.Data("Actual Intake", actualInput));

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
