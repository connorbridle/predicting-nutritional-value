package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.*;
import java.util.*;

public class OutputController {


    @FXML private Label calsLabel;
    @FXML private Label fatLabel;
    @FXML private Label satFatLabel;
    @FXML private Label carbsLabel;
    @FXML private Label sugarsLabel;
    @FXML private Label fibreLabel;
    @FXML private Label proteinLabel;
    @FXML private Label saltLabel;
    @FXML private Button submitButton;

    //Variables that hold the summed intake of all macro-nutrients
    public static double totalIntakeCal= 0.0;
    public static double totalIntakeFat= 0.0;
    public static double totalIntakeSatFat= 0.0;
    public static double totalIntakeCarbs= 0.0;
    public static double totalIntakeSugars= 0.0;
    public static double totalIntakeFibre= 0.0;
    public static double totalIntakeProtein= 0.0;
    public static double totalIntakeSalt = 0.0;

    //TODO remove this as it's redundant now
    //Variables that hold the RDA of all the macro-nutrients
    private static double RDA_CALS = 150;
    private static double RDA_FAT = 150;
    private static double RDA_SATFAT = 150;
    private static double RDA_CARBS = 150;
    private static double RDA_SUGARS = 150;
    private static double RDA_FIBRE = 150;
    private static double RDA_PROTEIN = 150;
    private static double RDA_SALT = 150;
    private static double currentIntake = 0;

    //Variable that holds the value of a decision (1=Red, 2=Amber, 3=Green)
    private int foodItemDecision = 0;

    //Variable that holds the row index for the persons age when the person inputs their age and submits
    private static int rowIndex = 0;
    private static int inputtedAge = 25; //TODO Testing variable, delete when done

    //Score array to hold all the scores for the individual macros
    private static int overallScore = 0;
    private static int[] score = new int[8];

    //Int used in the overRDA function, to hold the index of the responsible marco
    private static int macroFailIndex;

    //Lists to hold any comments made by the score methods for more user involvement
    private static ArrayList<String> calsComments = new ArrayList<>();
    private static ArrayList<String> fatComments = new ArrayList<>();
    private static ArrayList<String> satFatComments = new ArrayList<>();
    private static ArrayList<String> carbsComments = new ArrayList<>();
    private static ArrayList<String> sugarsComments = new ArrayList<>();
    private static ArrayList<String> fibreComments = new ArrayList<>();
    private static ArrayList<String> proteinComments = new ArrayList<>();
    private static ArrayList<String> saltComments = new ArrayList<>();
    private static ArrayList<String> generalComments = new ArrayList<>();

    //Output DecisionObject and foodItem
    private static FoodItem outputFoodItem;
    private static DecisionObject outputDecisionObject;

    public static Date date = new Date();
    public static Calendar calendar = GregorianCalendar.getInstance();

    //TODO remove these after i'm finished and user the above objects instead
    //Testing DecisionObject and FoodItem that will be removed later
    FoodItem testingFoodItem = new FoodItem("TestingNAme", RDA_CALS, RDA_FAT, RDA_SATFAT, RDA_CARBS, RDA_SUGARS, RDA_FIBRE,
            RDA_PROTEIN, RDA_SALT);
    DecisionObject testingDecision = new DecisionObject(testingFoodItem, score, overallScore, calsComments, fatComments,
            satFatComments, carbsComments, sugarsComments, fibreComments, proteinComments, saltComments, generalComments);


    //All of the above might be redundant after using the csv file data structure
    List<List<String>> maleRDA;
    String maleRDAFilePath = "/Users/connorbridle/Desktop/Third-Year-Project/typ/LoginFx/src/sample/Males_Datastructure.csv";
    List<List<String>> femaleRDA;
    String femaleRDAFilePath = "/Users/connorbridle/Desktop/Third-Year-Project/typ/LoginFx/src/sample/Females_Datastructure.csv";
    List<String> currentRow;


    //Main function that communicates with the other feeder functions
    public void startPrediction(FoodItem inputtedFoodItem) {
        System.out.println("START PREDICTION CALLED");
        outputFoodItem = inputtedFoodItem;
        System.out.println("GOT FOOD ITEM: " + outputFoodItem.getItemCals() + outputFoodItem.getItemFat() +
        outputFoodItem.getItemSatFat());

        //Fills 2d array list with csv values
        //TODO if the person is male, parse male csv, if female parse female csv
        maleRDA = csvParser(maleRDAFilePath);
        femaleRDA = csvParser(femaleRDAFilePath);
        rowIndex = returnRowIndex(inputtedAge); //TODO age input
        currentRow = maleRDA.get(rowIndex);

        //Pass the values contained to the various score methods
        int calsScore = calculateCaloriesScore(inputtedFoodItem.getItemCals(), currentRow);
        int fatScore = calculateFatScore(inputtedFoodItem.getItemFat(), currentRow);
        int satFatScore = calculateSatFatScore(inputtedFoodItem.getItemSatFat(), currentRow);
        int carbsScore = calculateCarbsScore(inputtedFoodItem.getItemCarbs(), currentRow);
        int sugarsScore = calculateSugarsScore(inputtedFoodItem.getItemSugar(), currentRow);
        int fibreScore = calculateFibreScore(inputtedFoodItem.getItemFibre(), currentRow);
        int proteinScore = calculateProteinScore(inputtedFoodItem.getItemProtein(), currentRow);
        int saltScore = calculateSaltScore(inputtedFoodItem.getItemSodium(), currentRow);
        overallScore = calsScore + fatScore + satFatScore + carbsScore + sugarsScore +
                fibreScore + proteinScore + saltScore;

        //Assigning the RDA values from the current row of the csv file
        RDA_CALS = Double.parseDouble(currentRow.get(1));
        RDA_FAT = Double.parseDouble(currentRow.get(2));
        RDA_SATFAT = Double.parseDouble(currentRow.get(3));
        RDA_CARBS = Double.parseDouble(currentRow.get(4));
        RDA_SUGARS = Double.parseDouble(currentRow.get(5));
        RDA_FIBRE = Double.parseDouble(currentRow.get(6));
        RDA_PROTEIN = Double.parseDouble(currentRow.get(7));
        RDA_SALT = Double.parseDouble(currentRow.get(8));
        System.out.println(currentRow);
        System.out.println(RDA_CALS);

        //Increment the total variables with the new food item inputted
        totalIntakeCal += inputtedFoodItem.getItemCals();
        totalIntakeFat += inputtedFoodItem.getItemFat();
        totalIntakeSatFat += inputtedFoodItem.getItemSatFat();
        totalIntakeCarbs += inputtedFoodItem.getItemCarbs();
        totalIntakeSugars += inputtedFoodItem.getItemSugar();
        totalIntakeFibre += inputtedFoodItem.getItemFibre();
        totalIntakeProtein += inputtedFoodItem.getItemProtein();
        totalIntakeSalt += inputtedFoodItem.getItemSodium();

        double[] measurementArray = {totalIntakeCal,totalIntakeFat,totalIntakeSatFat,totalIntakeCarbs,totalIntakeSugars,
                totalIntakeFibre,totalIntakeProtein,totalIntakeSalt};
        //TODO make this read from the appropiate value from the csv file
        double[] measurementRDA = {RDA_CALS, RDA_FAT, RDA_SATFAT, RDA_CARBS, RDA_SUGARS,
                RDA_FIBRE, RDA_PROTEIN, RDA_SALT};
        double[] output = populateMeasurementArray(measurementArray, measurementRDA);

        //If overRDA in any of the macros, no need to do any further calculations
        if (overRDA(output)) {
            populateComments(macroFailIndex); //Populates the relevant comments array with suitable notification to user
            foodItemDecision = 1; //Equivalent to saying that the decision is red/no
            outputRedDecision(testingDecision); //TODO change this to output the proper decision object
            System.out.println("RED -> Not advisable to eat!");
        } else {
            //TODO continue with the decision making and calling the feeder methods
            makeFinalDecision(overallScore, outputDecisionObject);
//            int calsScore = calculateCaloriesScore(inputtedFoodItem.getItemCals(), currentRow);
//            int fatScore = calculateFatScore(inputtedFoodItem.getItemFat(), currentRow);
//            int satFatScore = calculateSatFatScore(inputtedFoodItem.getItemSatFat(), currentRow);
//            int carbsScore = calculateCarbsScore(inputtedFoodItem.getItemCarbs(), currentRow);
//            int sugarsScore = calculateSugarsScore(inputtedFoodItem.getItemSugar(), currentRow);
//            int fibreScore = calculateFibreScore(inputtedFoodItem.getItemFibre(), currentRow);
//            int proteinScore = calculateProteinScore(inputtedFoodItem.getItemProtein(), currentRow);
//            int saltScore = calculateSaltScore(inputtedFoodItem.getItemSodium(), currentRow);
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

    //Function that consolidates and constructs the final decision object that will be fed to the next controller
    private static void constructDecisionObject() {
        outputDecisionObject = new DecisionObject(outputFoodItem, score, overallScore, calsComments, fatComments,
                satFatComments, carbsComments, sugarsComments, fibreComments, proteinComments, saltComments, generalComments);
    }

    //Function that makes the final decision based on an inputted score variable
    private void makeFinalDecision(int score, DecisionObject object) {
        //TODO maybe remove score and object here and replace them with global static variables
        System.out.println("MAKE FINAL DECISION FUNCTION SCORE: " + score);
        if (overallScore < 100) {
            //Output decision green
            System.out.println("SCORE LESS THAN 100: OUTPUT GREEN");
            outputGreenDecision(object);
        } else if (overallScore < 200 && score > 100) {
            //Output decision amber
            System.out.println("SCORE BETWEEN 100 AND 200: OUTPUT AMBER");
            outputAmberDecision(object);
        } else {
            //Output decision red
            System.out.println("ELSE: OUTPUT RED");
            outputRedDecision(object);
        }
    }

    //Function that deals with adding comments if any of the macros are over the RDAs
    private static void populateComments(int failIndex) {
        switch (failIndex) {
            case 0:
                calsComments.add("Your daily calorie intake is over governments recommended daily allowance!");
                break;
            case 1:
                fatComments.add("Your daily fat intake is over governments recommended daily allowance");
                break;
            case 2:
                satFatComments.add("Your daily saturated-fat intake is over governments recommended daily allowance");
                break;
            case 3:
                carbsComments.add("Your daily carbohydrates intake is over governments recommended daily allowance");
                break;
            case 4:
                sugarsComments.add("Your daily sugars intake is over governments recommended daily allowance");
                break;
            case 5:
                fibreComments.add("Your daily fibre intake is over governments recommended daily allowance");
                break;
            case 6:
                proteinComments.add("Your daily protein intake is over governments recommended daily allowance");
                break;
            case 7:
                saltComments.add("Your daily salt intake is over governments recommended daily allowance");
                break;

        }
    }

    //Function that checks whether or not any items are 1. In other words, if any of the macro-nutrients are over the RDA
    private static boolean overRDA(double[] inputArray) {
        //Loops array and looks for '1'
        System.out.println(Arrays.toString(inputArray));
        for (int i = 0; i < inputArray.length; i++) {
            //Returns true as soon as a 1 was found
            if(inputArray[i] == 1) {
                macroFailIndex = i;
                return true;
            }
        }
        return false;
    }

    //Function that takes you back to the starting point
    public void returnToStart(ActionEvent event) {
        try {
            Parent returnView = FXMLLoader.load(getClass().getResource("index.fxml"));
            Scene newScene = new Scene(returnView);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Home");
            primaryStage.setScene(newScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Function that opens the detailed results page
    public void seeResults(ActionEvent event) {
        try {
            Parent detailedView = FXMLLoader.load(getClass().getResource("DetailedResults.fxml"));
            Scene newScene = new Scene(detailedView);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Detailed Macro-Nutrient Breakdown");
            primaryStage.setScene(newScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Input/Output exception!");
            e.printStackTrace();
        }
    }

    //TODO need to implement all the methods that will calculate the individul scores and influence what is placed on the screen in results
    //TODO might be better to have all these functions as a single one, and pass in the food item value and rda for each
    //Calculates a score for the calories contained in the food item

    private static int calculateCaloriesScore(double cals, List<String> currentRow) {
        //First thing to do is to find what percentage the inputted value is compared to the rda
        double percentage = (cals / (Double.parseDouble(currentRow.get(1))) ) * 100;
        int returnScore = (int)percentage;

        //Large food intake before bed could lead to obesity
        calendar.setTime(date);
        int currentTime = calendar.get(Calendar.HOUR_OF_DAY);
        if (currentTime > 21 && cals < 250) {
            calsComments.add("Studies show that smaller meals before bed will not contribute to increased " +
                    "risk of obesity");
        }
        return returnScore;
    }

    //Calculates a score for the fat contained in the food item
    private static int calculateFatScore(double fat, List<String> currentRow) {
        //First thing to do is to find what percentage the inputted value is compared to the rda
        double percentage  = (fat / (Double.parseDouble(currentRow.get(2))) ) * 100;
        int returnScore = (int)percentage;

        //Eatwell guide measures
        if (fat >= 21){
            //Classed as high fat content by the eat-well guide
            fatComments.add("Eatwell guide suggests fat content of 21g or higher per 100g of food is " +
                    "considered high intake.");
        } else if (fat >= 4 && fat <= 20) {
            //Classed as medium fat content per 100g by the eat-well guide
            fatComments.add("Eatwell guide suggests fat content more than 4g and less than 20g per 100g of food" +
                    "is a medium fat content.");
        } else if (fat <= 3) {
            //Classed as a low fat content item per 100g by the eat-well guide
            fatComments.add("Eatwell guide suggests fat content less than or equal to 3g per 100g of food is" +
                    "considered low intake.");
        }
        return returnScore;
    }

    //Calculates a score for the satfat contained in the food item
    private static int calculateSatFatScore(double satFat, List<String> currentRow) {
        //First thing to do is to find what percentage the inputted value is compared to the rda
        double percentage  = ( satFat / (Double.parseDouble(currentRow.get(3))) ) * 100;
        int score = (int)percentage;
        //Eat-well guide measures
        if (satFat >= 5) {
            //Classed as high satfat content per 100g by the eat-well guide
            satFatComments.add("Eatwell guide suggests saturated-fat content more than 5g per 100g of food" +
                    "is considered high intake.");
        } else if (satFat > 1.5 && satFat < 5) {
            //Classed as medium fat content per 100g by the eat-well guide
            satFatComments.add("Eatwell guide suggests saturated-fat content between 1.5g and 5g per 100g of" +
                    "food is considered medium content");
        } else if (satFat <= 1.5) {
            //Classed as low fat content per 100g by the eat-well guide
            satFatComments.add("Eatwell guide suggests saturated-fat content less than or equal to 1.5g per " +
                    "100g of food is considered low intake.");
        }
        int returnScore = (int)percentage;
        return returnScore;
    }

    //Calculates a score for the carbs contained in the food item
    private static int calculateCarbsScore(double carbs, List<String> currentRow) {
        //First thing to do is to find what percentage the inputted value is compared to the rda
        double percentage = (carbs / (Double.parseDouble(currentRow.get(4))) ) * 100;
        int returnScore = (int)percentage;
        return returnScore;
    }

    //Calculates a score for the sugars contained in the food item
    //MAX SCORE = 100% +10
    private static int calculateSugarsScore(double sugars, List<String> currentRow) {
        //First thing to do is to find what percentage the inputted value is compared to the rda
        double percentage = (sugars / (Double.parseDouble(currentRow.get(5))) ) * 100;
        int returnScore = (int)percentage;

        //Eat-well guide measures
        if (sugars > 15) {
            //Classed as a high sugar content item per 100g by the eat-well guide
            sugarsComments.add("Eatwell guide suggests sugar content higher than 15g per 100g of food item is " +
                    "considered high intake.");
        } else if (sugars <=15 && sugars >=6) {
            //Classed as a medium sugar content item per 100g by the eat-well guide
            sugarsComments.add("Eatwell guide suggests sugar content between 6g and 15g per 100g of food item " +
                    "is considered medium intake.");
        } else if (sugars < 6) {
            //Classed as a low sugar content item per 100g by the eat-well guide
            sugarsComments.add("Eatwell guide suggests sugar content less than 6g per 100g of food item " +
                    "is considered low intake.");
        }

        //Eating sugar at night may prevent you from sleeping
        calendar.setTime(date);
        int currentTime = calendar.get(Calendar.HOUR_OF_DAY);
        if (currentTime > 21) {
            sugarsComments.add("Eating sugar close to when you sleep is shown to keep you awake.");
        }

        return returnScore;
    }

    //Calculates a score for the fibre contained in the food item
    private static int calculateFibreScore(double fibre, List<String> currentRow) {
        //First thing to do is to find what percentage the inputted value is compared to the rda
        double percentage = (fibre / (Double.parseDouble(currentRow.get(6))) ) * 100;
        int returnScore = (int)percentage;
        return returnScore;
    }

    //Calculates a score for the protein contained in the food item
    private static int calculateProteinScore(double protein, List<String> currentRow) {
        //First thing to do is to find what percentage the inputted value is compared to the rda
        double percentage = (protein / (Double.parseDouble(currentRow.get(7))) ) * 100;

        //If the protein is above 30g in a single meal, body might not be able to absorb it all
        if (protein > 30) {
            proteinComments.add("Consuming more than 30g of protein in one sitting has been proven to be " +
                    "pointless as the excess is excreted as waste product");
        }
        return 5;
    }

    //Calculates a score for the fibre contained in the food item
    //MAX SCORE = 100% + 10
    private static int calculateSaltScore(double salt, List<String> currentRow) {
        //First thing to do is to find what percentage the inputted value is compared to the rda
        double percentage = (salt/ (Double.parseDouble(currentRow.get(8))) ) * 100;
        int score = (int)percentage;

        //Eat-well guide measures
        if (salt > 1.5) {
            //Classed as high a salt content item per 100g by the eat-well guide
            saltComments.add("Eatwell guide suggests salt content higher than 1.5g per 100g of food item " +
                    "is considered high intake.");
            score += 10;
        } else if (salt <= 1.5 && salt >= 0.3) {
            //Classed as a medium salt content item per 100g by the eat-well guide
            saltComments.add("Eatwell guide suggests salt content between 0.3g and 1.5g per 100g of food item " +
                    "is considered medium intake.");
            score += 5;
        } else if (salt < 0.3) {
            //Classed as a low salt content item per 100g by the eat-well guide
            saltComments.add("Eatwell guide suggests salt content lower than 0.3g per 100g of food item " +
                    "is considered low intake");
        }

        return score;
    }

    //Function that outputs a no decision from the algorithm
    private void outputRedDecision(DecisionObject decisionMade) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("RedResultOuput.fxml"));
            System.out.println("Outputting red decision");
            submitButton.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Function that outputs a maybe decision from the algorithm
    private void outputAmberDecision(DecisionObject decisionMade) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AmberResultOutput.fxml"));
            submitButton.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Function that outputs a yes decision from the algorithm
    private void outputGreenDecision(DecisionObject decisionMade) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("GreenResultOutput.fxml"));
            submitButton.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int returnRowIndex(int age) {
        return (age - 18);
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
