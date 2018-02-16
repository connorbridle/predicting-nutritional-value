package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OutputController {

    FoodItem currentFoodItem;

    @FXML private Label calsLabel;
    @FXML private Label fatLabel;
    @FXML private Label satFatLabel;
    @FXML private Label carbsLabel;
    @FXML private Label sugarsLabel;
    @FXML private Label fibreLabel;
    @FXML private Label proteinLabel;
    @FXML private Label saltLabel;

    //Variables that hold the summed intake of all macro-nutrients
    public static double totalIntakeCal= 0.0;
    public static double totalIntakeFat= 0.0;
    public static double totalIntakeSatFat= 0.0;
    public static double totalIntakeCarbs= 500;
    public static double totalIntakeSugars= 0.0;
    public static double totalIntakeFibre= 500;
    public static double totalIntakeProtein= 0.0;
    public static double totalIntakeSalt = 0.0;

    //Variables that hold the RDA of all the macro-nutrients
    private final static double RDA_CALS = 150;
    private final static double RDA_FAT = 150;
    private final static double RDA_SATFAT = 150;
    private final static double RDA_CARBS = 150;
    private final static double RDA_SUGARS = 150;
    private final static double RDA_FIBRE = 150;
    private final static double RDA_PROTEIN = 150;
    private final static double RDA_SALT = 150;
    private static double currentIntake = 0;

    //All of the above might be redundant after using the csv file data structure
    List<List<String>> maleRDA;
    String maleRDAFilePath = "sample/Males_Datastructure.csv";
    List<List<String>> femaleRDA;
    String femaleRDAFilePath = "sample/Females_Datastructure.csv";


    //Main function that communicates with the other feeder functions
    public void startPrediction(FoodItem inputtedFoodItem) {
        currentFoodItem = inputtedFoodItem;
        System.out.println("GOT FOOD ITEM: " + currentFoodItem.getItemFat());

        double[] measurementArray = {totalIntakeCal,totalIntakeFat,totalIntakeSatFat,totalIntakeCarbs,totalIntakeSugars,
                totalIntakeFibre,totalIntakeProtein,totalIntakeSalt};
        double[] measurementRDA = {RDA_CALS, RDA_FAT, RDA_SATFAT, RDA_CARBS, RDA_SUGARS,
                RDA_FIBRE, RDA_PROTEIN, RDA_SALT};
        double[] output = populateMeasurementArray(measurementArray, measurementRDA);

        if (overRDA(output)) {
            //TODO output no decision as one of the macro-nutrients is over the RDA
        } else {
            //TODO continue with the decision making and calling the feeder methods
        }
    }

    //TODO maybe can combine the overRDA and populateMeasurementArray so that as soon as one macro-nutrient is over RDA,
    //TODO it stops populating the array and returns true.

    //Function that is used to measure whether the current intake is above the RDA. Returns an array filled with
    //either 1's or 0's. 1 meaning over RDA and 0 meaning under. Can then be used in the overRDA method
    private static double[] populateMeasurementArray(double[] measurementArray, double[] rdaMeasurements) {
        double[] myArray = new double[8];
        for (int i = 0; i < measurementArray.length; i++) {
            double temp = measurementArray[i];
            double rdatemp = rdaMeasurements[i];
            if ((temp + currentIntake) > rdatemp) {
                myArray[i] = 1; //If the food item takes the current intake over rda
            } else {
                myArray[i] = 0;
            }
        }
        return myArray;
    }

    //Function that checks whether or not any items are 1. In other words, if any of the macro-nutrients are over the RDA
    private static boolean overRDA(double[] inputArray) {
        //Loops array and looks for '1'
        System.out.println(Arrays.toString(inputArray));
        for (int i = 0; i < inputArray.length; i++) {
            if(inputArray[i] == 1) {
                return true;
            }
        }
        return false;
    }

    //TODO add a try catch block
    //Function that takes you back to the starting point
    public void returnToStart(ActionEvent event) throws IOException {
        Parent returnView = FXMLLoader.load(getClass().getResource("index.fxml"));
        Scene newScene = new Scene(returnView);
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("Home");
        primaryStage.setScene(newScene);
        primaryStage.show();
    }

    //TODO add a try catch block
    //Function that opens the detailed results page
    public void goToDetailedView(ActionEvent event) throws IOException{
        Parent detailedView = FXMLLoader.load(getClass().getResource("DetailedResults.fxml"));
        Scene newScene = new Scene(detailedView);
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("Detailed Macro-Nutrient Breakdown");
        primaryStage.setScene(newScene);
        primaryStage.show();
    }

    //TODO need to implement all the methods that will calculate the individul scores and influence what is placed on the screen in results
    //TODO might be better to have all these functions as a single one, and pass in the food item value and rda for each
    //Calculates a score for the calories contained in the food item
    private static int calculateCaloriesScore(double cals) {
        return 5;
    }

    //Calculates a score for the fat contained in the food item
    private static int calculateFatScore(double fat) {
        return 5;
    }

    //Calculates a score for the satfat contained in the food item
    private static int calculateSatFatScore(double satFat) {
        return 5;
    }

    //Calculates a score for the carbs contained in the food item
    private static int calculateCarbsScore(double carbs) {
        return 5;
    }

    //Calculates a score for the sugars contained in the food item
    private static int calculateSugarsScore(double sugars) {
        return 5;
    }

    //Calculates a score for the fibre contained in the food item
    private static int calculateFibreScore(double fibre) {
        return 5;
    }

    //Calculates a score for the fibre contained in the food item
    private static int calculateSaltScore() {
        return 5;
    }

    //Function that outputs a no decision from the algorithm
    private static void outputRedDecision() {

    }

    //Function that outputs a maybe decision from the algorithm
    private static void outputAmberDecision() {

    }

    //Function that outputs a yes decision from the algorithm
    private static void outputGreenDecision() {

    }

    public static List<List<String>> csvParser(String filePath) {
        File file= new File(filePath);

        // Gives 2d arraylist
        List<List<String>> lines = new ArrayList<>();
        Scanner inputStream = null;
        try {
            inputStream = new Scanner(file);
            while(inputStream.hasNext()){
                String line= inputStream.next();
                String[] values = line.split(",");
                // this adds the currently parsed line to the 2-dimensional string array
                lines.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
//        //Loop through arraylist
//        for (int i = 0; i < lines.size(); i++) {
//            for (int j = 0; i < lines.get(i).size(); i++) {
//                //Do some stuff
//            }
//        }

        return lines;
    }

}
