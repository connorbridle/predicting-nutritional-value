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

    //Variables that hold the current intake of all macro-nutrients
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
    private static double RDA_CALS = 0;
    private static double RDA_FAT = 0;
    private static double RDA_SATFAT = 0;
    private static double RDA_CARBS = 0;
    private static double RDA_SUGARS = 0;
    private static double RDA_FIBRE = 0;
    private static double RDA_PROTEIN = 0;
    private static double RDA_SALT = 0;
    private static double currentIntake = 0;


    //Variable that holds the row index for the persons age when the person inputs their age and submits
    private static int rowIndex = 0;
    private static int inputtedAge = 0;
    private static Gender inputtedGender= null;

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
    private static ProfileObject outputPerson;

    public static Date date = new Date();
    public static Calendar calendar = GregorianCalendar.getInstance();

    //TODO remove these after i'm finished and user the above objects instead
    //Testing DecisionObject and FoodItem that will be removed later
    FoodItem testingFoodItem = new FoodItem("TestingNAme", RDA_CALS, RDA_FAT, RDA_SATFAT, RDA_CARBS, RDA_SUGARS, RDA_FIBRE,
            RDA_PROTEIN, RDA_SALT);
    //TODO change this from amber
    DecisionObject testingDecision = new DecisionObject("amber", testingFoodItem, score, overallScore, calsComments, fatComments,
            satFatComments, carbsComments, sugarsComments, fibreComments, proteinComments, saltComments, generalComments);


    //All of the above might be redundant after using the csv file data structure
    List<List<String>> maleRDA;
    String maleRDAFilePath = "/Users/connorbridle/Desktop/Third-Year-Project/typ/LoginFx/src/sample/Males_Datastructure.csv";
    List<List<String>> femaleRDA;
    String femaleRDAFilePath = "/Users/connorbridle/Desktop/Third-Year-Project/typ/LoginFx/src/sample/Females_Datastructure.csv";
    List<String> currentRow;


    //================================================================================
    // Function that takes in the objects from IndexController
    //================================================================================
    public void storeObjectInputs(FoodItem inputtedFoodItem, ProfileObject person) {
        outputFoodItem = inputtedFoodItem;
        loadFoodItem(outputFoodItem);
        outputPerson = person;
        inputtedAge = outputPerson.getAge(); //Sets the age
        inputtedGender = outputPerson.getGender(); //Sets the gender

    }

    public void loadFoodItem(FoodItem input) {
        calsLabel.setText(Double.toString(input.getItemCals()));
        fatLabel.setText(Double.toString(input.getItemFat()));
        satFatLabel.setText(Double.toString(input.getItemSatFat()));
        carbsLabel.setText(Double.toString(input.getItemCarbs()));
        sugarsLabel.setText(Double.toString(input.getItemSugar()));
        fibreLabel.setText(Double.toString(input.getItemFibre()));
        proteinLabel.setText(Double.toString(input.getItemProtein()));
        saltLabel.setText(Double.toString(input.getItemSodium()));
    }

    //================================================================================
    // Main function that communicates with the other feeder functions
    //================================================================================
    public void startPrediction(ActionEvent event) {
//        outputFoodItem = inputtedFoodItem;
        overallScore = 0;

        //Clear the arraylists
        calsComments = new ArrayList<>();
        fatComments = new ArrayList<>();
        satFatComments = new ArrayList<>();
        carbsComments = new ArrayList<>();
        sugarsComments = new ArrayList<>();
        fibreComments = new ArrayList<>();
        proteinComments = new ArrayList<>();
        saltComments = new ArrayList<>();
        generalComments = new ArrayList<>();

        //Initialise the decision object with null values!
        outputDecisionObject = new DecisionObject(null, outputFoodItem, null, 0, null,
                null, null, null, null, null,
                null, null, null);
        //Fills 2d array list with csv values
        //TODO if the person is male, parse male csv, if female parse female csv
        maleRDA = csvParser(maleRDAFilePath);
        femaleRDA = csvParser(femaleRDAFilePath);
        rowIndex = returnRowIndex(inputtedAge); //TODO age input
        //
        if (inputtedGender == Gender.MALE) {
            currentRow = maleRDA.get(rowIndex);
        } else if (inputtedGender == Gender.FEMALE) {
            currentRow = femaleRDA.get(rowIndex);
        } else {
            currentRow = maleRDA.get(rowIndex);
        }

        //Pass the values contained to the various score methods
        //Each method takes the the macro, the current row of the csv file rdas, and the profile object
        int calsScore = calculateCaloriesScore(outputFoodItem.getItemCals(), currentRow, outputPerson);
        int fatScore = calculateFatScore(outputFoodItem.getItemFat(), currentRow, outputPerson);
        int satFatScore = calculateSatFatScore(outputFoodItem.getItemSatFat(), currentRow, outputPerson);
        int carbsScore = calculateCarbsScore(outputFoodItem.getItemCarbs(), currentRow, outputPerson);
        int sugarsScore = calculateSugarsScore(outputFoodItem.getItemSugar(), currentRow, outputPerson);
        int fibreScore = calculateFibreScore(outputFoodItem.getItemFibre(), currentRow, outputPerson);
        int proteinScore = calculateProteinScore(outputFoodItem.getItemProtein(), currentRow, outputPerson);
        int saltScore = calculateSaltScore(outputFoodItem.getItemSodium(), currentRow, outputPerson);
        int[] arrayOfScores = new int[]{calsScore,fatScore,satFatScore,carbsScore,sugarsScore,fibreScore,proteinScore,saltScore};
//        int[] normalisedScores = new int[8];
//
//        //Scaling down between 1 and 10
//        int maxRange = Arrays.stream(arrayOfScores).max().getAsInt();
//        int minRange = Arrays.stream(arrayOfScores).min().getAsInt();
//        System.out.println("TESTING!@@£@£@£@£: " + maxRange + minRange);
//        for (int i = 0; i < arrayOfScores.length; i++) {
//            int beforeScale = arrayOfScores[i];
//            System.out.println("before scale: " + beforeScale);
//            int afterScale = scaleNumbersBetween(beforeScale, 0,100,minRange,maxRange);
//            System.out.println("after scale: " + afterScale);
//            normalisedScores[i] = afterScale;
//        }
//
//        System.out.println("DSFFDFSDFSF" + arrayOfScores[0]);
//        System.out.println("DSFFDFSDFSF" + normalisedScores[0]);


        //Map to hold the scores calculated by the functions below
        HashMap<String, Integer> mapOfScores = new HashMap<>();
        mapOfScores.put("cals", calsScore);
        mapOfScores.put("fat", fatScore);
        mapOfScores.put("satFat", satFatScore);
        mapOfScores.put("carbs", carbsScore);
        mapOfScores.put("sugars", sugarsScore);
        mapOfScores.put("fibre", fibreScore);
        mapOfScores.put("protein", proteinScore);
        mapOfScores.put("salt", saltScore);

        overallScore = (calsScore + fatScore + satFatScore + carbsScore + sugarsScore +
                fibreScore + proteinScore + saltScore)/8;

        //Assigning the RDA values from the current row of the csv file
        //Could comment that it is more efficient to use a 2d array here
        RDA_CALS = Double.parseDouble(currentRow.get(1));
        RDA_FAT = Double.parseDouble(currentRow.get(2));
        RDA_SATFAT = Double.parseDouble(currentRow.get(3));
        RDA_CARBS = Double.parseDouble(currentRow.get(4));
        RDA_SUGARS = Double.parseDouble(currentRow.get(5));
        RDA_FIBRE = Double.parseDouble(currentRow.get(6));
        RDA_PROTEIN = Double.parseDouble(currentRow.get(7));
        RDA_SALT = Double.parseDouble(currentRow.get(8));
        System.out.println(currentRow);

        //Increment the total variables with the new food item inputted
        totalIntakeCal += outputFoodItem.getItemCals();
        totalIntakeFat += outputFoodItem.getItemFat();
        totalIntakeSatFat += outputFoodItem.getItemSatFat();
        totalIntakeCarbs += outputFoodItem.getItemCarbs();
        totalIntakeSugars += outputFoodItem.getItemSugar();
        totalIntakeFibre += outputFoodItem.getItemFibre();
        totalIntakeProtein += outputFoodItem.getItemProtein();
        totalIntakeSalt += outputFoodItem.getItemSodium();

        //Increment that total variables on the profile object
        outputPerson.setTotalIntakeCal(totalIntakeCal);
        outputPerson.setTotalIntakeFat(totalIntakeFat);
        outputPerson.setTotalIntakeSatFat(totalIntakeSatFat);
        outputPerson.setTotalIntakeCarbs(totalIntakeCarbs);
        outputPerson.setTotalIntakeSugars(totalIntakeSugars);
        outputPerson.setTotalIntakeFibre(totalIntakeFibre);
        outputPerson.setTotalIntakeProtein(totalIntakeProtein);
        outputPerson.setTotalIntakeSalt(totalIntakeSalt);

        //store the output person into an object file
        try {
            FileOutputStream fileInput =  new FileOutputStream(new File("profileObject.txt"));
            ObjectOutputStream objectInput = new ObjectOutputStream(fileInput);
            objectInput.writeObject(outputPerson);

            //Close streams
            objectInput.close();
            fileInput.close();
        } catch (FileNotFoundException fnf) {
            fnf.printStackTrace();
            System.out.println("File not found!");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO Exception!");
        }

        //Add all the components to the null DecisionObject
        outputDecisionObject.setCalsComments(calsComments);
        outputDecisionObject.setFatComments(fatComments);
        outputDecisionObject.setSatFatComments(satFatComments);
        outputDecisionObject.setCarbsComments(carbsComments);
        outputDecisionObject.setSugarsComments(sugarsComments);
        outputDecisionObject.setFibreComments(fibreComments);
        outputDecisionObject.setProteinComments(proteinComments);
        outputDecisionObject.setSaltComments(saltComments);
        outputDecisionObject.setGeneralCommentsComments(generalComments);
        outputDecisionObject.setOverallScore(overallScore);
        outputDecisionObject.setIndividualScore(new int[] {calsScore,fatScore,satFatScore,carbsScore,sugarsScore,fibreScore,proteinScore,saltScore});


        double[] measurementArray = {totalIntakeCal,totalIntakeFat,totalIntakeSatFat,totalIntakeCarbs,totalIntakeSugars,
                totalIntakeFibre,totalIntakeProtein,totalIntakeSalt};
        //TODO make this read from the appropiate value from the csv file
        double[] measurementRDA = {RDA_CALS, RDA_FAT, RDA_SATFAT, RDA_CARBS, RDA_SUGARS,
                RDA_FIBRE, RDA_PROTEIN, RDA_SALT};
        double[] output = populateMeasurementArray(measurementArray, measurementRDA);

        //If overRDA in any of the macros, no need to do any further calculations
        if (overRDA(output)) {
            System.out.println("ASSDSDSDASDASD");
            populateComments(macroFailIndex); //Populates the relevant comments array with suitable notification to user
            outputRedDecision(testingDecision, event); //TODO change this to output the proper decision object
            System.out.println("RED -> Not advisable to eat!");
        } else {
            //TODO continue with the decision making and calling the feeder methods
            makeFinalDecision(overallScore, arrayOfScores, outputDecisionObject, event);
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
        //TODO change decision object
        outputDecisionObject = new DecisionObject("amber", outputFoodItem, score, overallScore, calsComments, fatComments,
                satFatComments, carbsComments, sugarsComments, fibreComments, proteinComments, saltComments, generalComments);
    }
    //Scales the number between the range provided
//    private static int scaleNumbersBetween(int numberInput, int minRange, int maxRange, int min, int max) {
//        return ((maxRange - minRange)*(numberInput-min)) / ((max-min) + minRange);
//    }


    //Function that makes the final decision based on an inputted score variable
    private void makeFinalDecision(int score, int[] scores, DecisionObject object, ActionEvent event) {
        //TODO maybe remove score and object here and replace them with global static variables
        System.out.println("MAKE FINAL DECISION FUNCTION SCORE: " + score);
//        if (overallScore < 100) {
//            //Output decision green
//            System.out.println("SCORE LESS THAN 100: OUTPUT GREEN");
//            outputGreenDecision(object, event);
//        } else if (overallScore < 200 && score > 100) {
//            //Output decision amber
//            System.out.println("SCORE BETWEEN 100 AND 200: OUTPUT AMBER");
//            outputAmberDecision(object, event);
//        } else {
//            //Output decision red
//            System.out.println("ELSE: OUTPUT RED");
//            outputRedDecision(object, event);
//        }

        //New decision section
        boolean isOver = false;
        ArrayList<Integer> overIndex = new ArrayList<>();
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] >= 50) {
                isOver = true;
                overIndex.add(scores[i]);
            }
        }
        if (isOver) {
            //RED
            outputRedDecision(object, event);
        } else if (score <= 100 && score > 60) {
            //If overall scores is over a certain point
            outputRedDecision(object, event);
        } else if (score <= 60 && score > 40) {
            //if overall scores are over certain point and under upper threshold
            outputAmberDecision(object, event);
        } else if (score <= 40 && score > 0) {
            //If scores are below a certain point
            outputGreenDecision(object, event);
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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("PersonalInput.fxml"));
            Parent outputView = loader.load();

            //access the controller and call the method
            PersonalInputController controller = loader.getController();
            controller.loadDataBackIntoProfile(outputPerson);

            Scene newScene = new Scene(outputView);
            Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Home");
            primaryStage.setScene(newScene);
            primaryStage.show();
            //end new window code
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

    protected static int calculateCaloriesScore(double cals, List<String> currentRow, ProfileObject person) {
        //First thing to do is to find what percentage the inputted value is compared to the rda
        double percentage = (cals / (Double.parseDouble(currentRow.get(1))) ) * 100;
        int returnScore = (int)percentage;

        //Calories and weight loss goal
        if (cals > 250 && person.getGoal() == Goal.LOSE && person.getActivityLevel() == ActivityLevel.LOW) {
            calsComments.add("Consuming a large serving of calories whilst trying to lose weight and whilst at " +
                    "a low level of activity can be unproductive to reach your aims.");
            returnScore += 25;
        } else if (cals > 250 && person.getGoal() == Goal.MAINTAIN && person.getActivityLevel() == ActivityLevel.LOW) {
            returnScore += 10;
        } else if (cals > 250 && person.getGoal() == Goal.LOSE) {
            calsComments.add("Consuming large amounts of calories in one serving when attempting to lose " +
                    "weight is unproductive.");
            returnScore += 25;
        } else if (cals > 250 && person.getGoal() == Goal.MAINTAIN) {
            calsComments.add("Consuming large amounts of calories in one serving can cause an increase in body " +
                    "weight.");
            returnScore += 10;
        }
        //Large food intake before bed could lead to obesity
        calendar.setTime(date);
        int currentTime = calendar.get(Calendar.HOUR_OF_DAY);
        if (currentTime > 21 && cals < 250) {
            calsComments.add("Studies show that smaller meals before bed will not contribute to increased " +
                    "risk of obesity");
        } else if (currentTime > 21 && cals < 250) {
            calsComments.add("Studies show that smaller meals before bed will not contribute to increased " +
                    "risk of obesity");
            returnScore += 25;
        }
        //If the score happens to exceed 100, then set the score to the max value (100)
        if (returnScore > 100) {
            returnScore = 100;
        }
        return returnScore;
    }

    //Calculates a score for the fat contained in the food item
    protected static int calculateFatScore(double fat, List<String> currentRow, ProfileObject person) {
        //First thing to do is to find what percentage the inputted value is compared to the rda
        double percentage  = (fat / (Double.parseDouble(currentRow.get(2))) ) * 100;
        int returnScore = (int)percentage;

        //Eatwell guide measures
        if (fat >= 21){
            //Classed as high fat content by the eat-well guide
            fatComments.add("Eatwell guide suggests fat content of 21g or higher per 100g of food is " +
                    "considered high intake.");
            returnScore += 25;
        } else if (fat >= 4 && fat <= 20) {
            //Classed as medium fat content per 100g by the eat-well guide
            fatComments.add("Eatwell guide suggests fat content more than 4g and less than 20g per 100g of food" +
                    "is a medium fat content.");
            returnScore += 10;
        } else if (fat <= 3) {
            //Classed as a low fat content item per 100g by the eat-well guide
            fatComments.add("Eatwell guide suggests fat content less than or equal to 3g per 100g of food is" +
                    "considered low intake.");
        }

        //Goal and activity level measures
        if (fat >= 21 && person.getGoal() == Goal.LOSE && person.getActivityLevel() == ActivityLevel.LOW) {
            fatComments.add("Consuming large amounts of fat whilst trying to lose weight and with a current low activity " +
                    "level is unproductive.");
            returnScore += 25;
        } else if(fat >= 21 && person.getGoal() == Goal.MAINTAIN && person.getActivityLevel() == ActivityLevel.LOW) {
            fatComments.add("Consuming large amounts of fat with a low exercise rate can lead to weight increase.");
            returnScore += 10;
        } else if(fat >= 21 && person.getGoal() == Goal.LOSE) {
            fatComments.add("Consuming large amounts of protein when trying to lose weight is unproductive");
            returnScore += 25;
        } else if (fat >= 21 && person.getGoal() == Goal.MAINTAIN) {
            fatComments.add("Consuming large amounts of calories in one serving can cause a weight increase.");
            returnScore += 10;
        }

        //If the score happens to exceed 100, then set the score to the max value (100)
        if (returnScore > 100) {
            returnScore = 100;
        }
        System.out.println("RETURN SCORE !!!!" + returnScore);
        return returnScore;
    }

    //Calculates a score for the satfat contained in the food item
    protected static int calculateSatFatScore(double satFat, List<String> currentRow, ProfileObject person) {
        //First thing to do is to find what percentage the inputted value is compared to the rda
        double percentage  = ( satFat / (Double.parseDouble(currentRow.get(3))) ) * 100;
        int returnScore = (int)percentage;
        //BASE MEASURES
        //Eat-well guide measures
        if (satFat >= 5) {
            //Classed as high satfat content per 100g by the eat-well guide
            satFatComments.add("Eatwell guide suggests saturated-fat content more than 5g per 100g of food" +
                    "is considered high intake.");
            returnScore += 25;
        } else if (satFat > 1.5 && satFat < 5) {
            //Classed as medium fat content per 100g by the eat-well guide
            satFatComments.add("Eatwell guide suggests saturated-fat content between 1.5g and 5g per 100g of" +
                    "food is considered medium content");
            returnScore += 10;
        } else if (satFat <= 1.5) {
            //Classed as low fat content per 100g by the eat-well guide
            satFatComments.add("Eatwell guide suggests saturated-fat content less than or equal to 1.5g per " +
                    "100g of food is considered low intake.");
        }

        //WITH ADDITIONAL MEASURES
        //sat fat and activity level
        if (satFat >= 5) {
            if (person.getGoal() == Goal.LOSE) {
                satFatComments.add("Eating high levels of saturated fat when you are trying to lose weight " +
                        "is not productive.");
                returnScore += 25;
            } else if (person.getGoal() == Goal.MAINTAIN) {
                satFatComments.add("Eating high levels of saturated fat when maintaining weight is not " +
                        "productive and may lead to weight gain.");
                returnScore += 25;
            } else if (person.getGoal() == Goal.GAIN) {
                satFatComments.add("Eating high levels of saturated is general bad for your body and heart " +
                        "regardless of weight-loss goal.");
                returnScore += 25;
            }
        } else if (satFat > 1.5 && satFat < 5) {
            if (person.getGoal() == Goal.LOSE) {
                satFatComments.add("Eating medium levels of saturated fat when attempting to lose weight can " +
                        "be unproductive.");
                returnScore += 25;
            } else if (person.getGoal() == Goal.MAINTAIN) {
                satFatComments.add("Eating medium levels of saturated fat when maintaining weight can be bad " +
                        "for your heart.");
                returnScore += 10;
            } else if (person.getGoal() == Goal.GAIN) {
                satFatComments.add("Eating medium levels of saturated fat when gaining weight can be bad for your heart.");
                returnScore += 10;
            }
        } else if (satFat <= 1.5) {
            if (person.getGoal() == Goal.LOSE) {
                satFatComments.add("Eating low levels of saturated fat when attempting to lose weight is recommended.");
                returnScore += 0;
            } else if (person.getGoal() == Goal.MAINTAIN) {
                satFatComments.add("Eating low levels of saturated fat when maintaining body weight is good.");
                returnScore += 0;
            } else if (person.getGoal() == Goal.GAIN) {
                satFatComments.add("Eating low levels of saturated fat when attempting to gain weight is good. " +
                        "Eating more of other macronutrients will yield better results while keeping you healthy.");
                returnScore += 0;
            }
        }

        if (satFat >= 5 && person.getGoal() == Goal.LOSE) {
            satFatComments.add("Eating high levels of Saturated fat when you are trying to lose weight " +
                    "is not productive.");
            returnScore += 25;
        } else if (satFat >= 5 && person.getGoal() == Goal.MAINTAIN) {
            satFatComments.add("Eating high levels of Saturated fat could lead to an increase in weight, " +
                    "therefore it is advisable to avoid");
        }

        //If the score happens to exceed 100, then set the score to the max value (100)
        if (returnScore > 100) {
            returnScore = 100;
        }
        return returnScore;
    }

    //Calculates a score for the carbs contained in the food item
    protected static int calculateCarbsScore(double carbs, List<String> currentRow, ProfileObject person) {
        //First thing to do is to find what percentage the inputted value is compared to the rda
        double percentage = (carbs / (Double.parseDouble(currentRow.get(4))) ) * 100;
        int returnScore = (int)percentage;

        //Values taken from health.gov guidelines article.
        if (carbs > 40) {
            if (person.getActivityLevel() == ActivityLevel.LOW) {
                if (person.getGoal() == Goal.LOSE) {
                    carbsComments.add("Consuming large quantities of carbohydrates whilst at a low level of activity " +
                            "and whilst trying to lose weight is unproductive.");
                    returnScore += 25;
                } else if (person.getGoal() == Goal.MAINTAIN) {
                    carbsComments.add("Consuming large quantities of carbohydrates whilst at a low level of activity " +
                            "and whilst maintaining current weight may cause weight gain.");
                    returnScore += 25;
                } else if (person.getGoal() == Goal.GAIN) {
                    carbsComments.add("Consuming large quantities of carbohydrates whilst at a low level of activity " +
                            "and whilst trying to gain weight could be beneficial.");
                }
            } else if (person.getActivityLevel() == ActivityLevel.MEDIUM) {
                if (person.getGoal() == Goal.LOSE) {
                    carbsComments.add("Consuming large quantities of carbohydrates whilst at a medium level of activity " +
                            "and whilst trying to lose weight is unproductive.");
                    returnScore += 25;
                } else if (person.getGoal() == Goal.MAINTAIN) {
                    carbsComments.add("Consuming large quantities of carbohydrates whilst at a medium level of activity " +
                            "and whilst trying to maintain weight could cause an increase in weight.");
                    returnScore += 10;
                } else if (person.getGoal() == Goal.GAIN) {
                    carbsComments.add("Consuming large quantities of carbohydrates whilst at a medium level of activity " +
                            "and whilst trying to gain weight may cause weight gain.");
                    returnScore += 0;
                }

            } else if (person.getActivityLevel() == ActivityLevel.HIGH) {
                if (person.getGoal() == Goal.LOSE) {
                    carbsComments.add("Consuming large quantities of carbohydrates whilst at a high level of activity " +
                            "and whilst trying to lose weight could be unproductive.");
                    returnScore += 10;
                } else if (person.getGoal() == Goal.MAINTAIN) {
                    carbsComments.add("Consuming large quantities of carbohydrates whilst at a high level of activity " +
                            "and whilst trying to maintain weight could cause weight gain.");
                    returnScore += 10;
                } else if (person.getGoal() == Goal.GAIN) {
                    carbsComments.add("Consuming large quantities of carbohydrates whilst at a high level of activity " +
                            "and whilst trying to gain weight could cause weight gain.");
                    returnScore += 0;
                }
            }

        } else if (carbs < 32 && carbs > 10) {
            if (person.getActivityLevel() == ActivityLevel.LOW) {
                if (person.getGoal() == Goal.LOSE) {
                    carbsComments.add("Consuming a medium amount of carbohydrates whilst at a low level of activity " +
                            "and whilst trying to lose weight may cause weight gain.");
                    returnScore += 10;
                } else if (person.getGoal() == Goal.MAINTAIN) {
                    carbsComments.add("Consuming a medium amount of carbohydrates whilst at a low level of activity " +
                            "and whilst trying to maintain weight could result in weight gain.");
                    returnScore += 10;
                } else if (person.getGoal() == Goal.GAIN) {
                    carbsComments.add("Consuming a medium amount of carbohydrates whilst at a low level of activity " +
                            "and whilst trying to gain weight could be productive to your goals.");
                    returnScore += 0;
                }

            } else if (person.getActivityLevel() == ActivityLevel.MEDIUM) {
                if (person.getGoal() == Goal.LOSE) {
                    carbsComments.add("Consuming a medium amount of carbohydrates whilst at a medium level of activity " +
                            "and whilst trying to lose weight could be unproductive to your goals.");
                    returnScore += 10;
                } else if (person.getGoal() == Goal.MAINTAIN) {
                    carbsComments.add("Consuming a medium amount of carbohydrates whilst at a medium level of activity " +
                            "and whilst trying to maintain weight could cause a small amount of weight gain.");
                    returnScore += 10;
                } else if (person.getGoal() == Goal.GAIN) {
                    carbsComments.add("Consuming a medium amount of carbohydrates whilst at a medium level of activity " +
                            "and whilst trying to gain weight could be productive to your goals.");
                    returnScore += 0;
                }

            } else if (person.getActivityLevel() == ActivityLevel.HIGH) {
                if (person.getGoal() == Goal.LOSE) {
                    carbsComments.add("Consuming a medium amount of carbohydrates whilst at a high level of activity " +
                            "and whilst trying to lose weight is good for a source of energy.");
                    returnScore += 0;
                } else if (person.getGoal() == Goal.MAINTAIN) {
                    carbsComments.add("Consuming a medium amount of carbohydrates whilst at a high level of activity " +
                            "and whilst trying to lose weight is good for a source of energy.");
                    returnScore += 0;
                } else if (person.getGoal() == Goal.GAIN) {
                    carbsComments.add("Consuming a larger serving of carbohydrates could be beneficial whilst at a high " +
                            "level of activity and whilst trying to gain weight.");
                    returnScore += 10;
                }

            }

        } else if (carbs < 10) {
            if (person.getActivityLevel() == ActivityLevel.LOW) {
                if (person.getGoal() == Goal.LOSE) {
                    carbsComments.add("Consuming a small serving of carbohydrates whilst at a low level of activity " +
                            "and whilst trying to lose weight is ok.");
                    returnScore += 0;
                } else if (person.getGoal() == Goal.MAINTAIN) {
                    carbsComments.add("Consuming a small serving of carbohydrates whilst at a low level of activity " +
                            "and whilst trying to maintain weight can be deemed as ok.");
                    returnScore += 0;
                } else if (person.getGoal() == Goal.GAIN) {
                    carbsComments.add("Consuming a small serving of carbohydrates whilst at a low level of activity " +
                            "and whilst trying to gain weight can be deemed as ok.");
                    returnScore += 0;
                }

            } else if (person.getActivityLevel() == ActivityLevel.MEDIUM) {
                if (person.getGoal() == Goal.LOSE) {
                    carbsComments.add("Consuming a small serving of carbohydrates whilst at a medium level of activity " +
                            "and whilst trying to lose weight can be deemed as ok.");
                    returnScore += 0;
                } else if (person.getGoal() == Goal.MAINTAIN) {
                    carbsComments.add("Consuming a small serving of carbohydrates whilst at a medium level of activity " +
                            "and whilst trying to maintain weight can be deemed as ok.");
                    returnScore += 0;
                } else if (person.getGoal() == Goal.GAIN) {
                    carbsComments.add("Consuming a small serving of carbohydrates whilst at a medium level of activity " +
                            "and whilst trying to gain weight can be deemed as ok.");
                    returnScore += 0;
                }

            } else if (person.getActivityLevel() == ActivityLevel.HIGH) {
                if (person.getGoal() == Goal.LOSE) {
                    carbsComments.add("Consuming a small serving of carbohydrates whilst at a high level of activity " +
                            "and whilst trying to lose weight can be deemed as ok.");
                    returnScore += 0;
                } else if (person.getGoal() == Goal.MAINTAIN) {
                    carbsComments.add("Consuming a small serving of carbohydrates whilst at a high level of activity " +
                            "and whilst trying to maintain weight can be deemed as ok.");
                    returnScore += 0;
                } else if (person.getGoal() == Goal.GAIN) {
                    carbsComments.add("Consuming a small serving of carbohydrates whilst at a high level of activity " +
                            "and whilst trying to gain weight can be deemed as ok.");
                    returnScore += 0;
                }

            }
        }

        //Consuming dietary carbs after exhausted exercise has been clearly demonstrated
        if (carbs > 50 && person.getActivityLevel() == ActivityLevel.HIGH) {
            carbsComments.add("Consuming carbohydrates just before or after exhaustive exercise can result in " +
                    "hypoglycemia and early onset exhaustion.");
            returnScore += 10;
        }

        return returnScore;
    }

    //Calculates a score for the sugars contained in the food item
    //MAX SCORE = 100% +10
    protected static int calculateSugarsScore(double sugars, List<String> currentRow, ProfileObject person) {
        //First thing to do is to find what percentage the inputted value is compared to the rda
        double percentage = (sugars / (Double.parseDouble(currentRow.get(5))) ) * 100;
        int returnScore = (int)percentage;

        //Eat-well guide measures
        if (sugars > 15) {
            //Classed as a high sugar content item per 100g by the eat-well guide
            sugarsComments.add("Eatwell guide suggests sugar content higher than 15g per 100g of food item is " +
                    "considered high intake.");
            returnScore += 25;
        } else if (sugars <=15 && sugars >=6) {
            //Classed as a medium sugar content item per 100g by the eat-well guide
            sugarsComments.add("Eatwell guide suggests sugar content between 6g and 15g per 100g of food item " +
                    "is considered medium intake.");
            returnScore += 10;
        } else if (sugars < 6) {
            //Classed as a low sugar content item per 100g by the eat-well guide
            sugarsComments.add("Eatwell guide suggests sugar content less than 6g per 100g of food item " +
                    "is considered low intake.");
        }

        //Sugars and weight goals
        if (sugars > 15 && person.getGoal() == Goal.LOSE) {
            sugarsComments.add("Consuming large portions of sugar is detrimental to your weight loss goal.");
            returnScore += 25;
        } else if (sugars > 15 && person.getGoal() == Goal.MAINTAIN) {
            sugarsComments.add("Consuming large portions of sugar and sugary snacks can lead to weight " +
                    "gain.");
        }

        //Sugars and exercise
        if (sugars <=15 && sugars >=6 && person.getActivityLevel() == ActivityLevel.HIGH) {
            sugarsComments.add("Consuming a moderate amount of sugar during exercise can increase " +
                    "energy stores.");
        } else if (sugars <=15 && sugars >=6 && person.getActivityLevel() == ActivityLevel.MEDIUM) {
            sugarsComments.add("Consuming a moderate amount of sugar during mild exercise may be detrimental " +
                    "to your efforts.");
            returnScore += 10;
        } else if (sugars > 15 && person.getActivityLevel() == ActivityLevel.LOW) {
            sugarsComments.add("Consuming a moderate amount of sugar in low periods of exercise is not advisable.");
            returnScore += 25;
        }

        //Eating sugar at night may prevent you from sleeping
        calendar.setTime(date);
        int currentTime = calendar.get(Calendar.HOUR_OF_DAY);
        if (currentTime > 21) {
            sugarsComments.add("Eating sugar close to when you sleep is shown to keep you awake.");
            returnScore += 25;
        }

        //If the score happens to exceed 100, then set the score to the max value (100)
        if (returnScore > 100) {
            returnScore = 100;
        }
        return returnScore;
    }

    //Calculates a score for the fibre contained in the food item
    protected static int calculateFibreScore(double fibre, List<String> currentRow, ProfileObject person) {
        //First thing to do is to find what percentage the inputted value is compared to the rda
        double percentage = (fibre / (Double.parseDouble(currentRow.get(6))) ) * 100;
        int returnScore = (int)percentage;

        //High fibre diet in woman cuts breast cancer risk (NHS)
        if (fibre > 10 && person.getGender() == Gender.FEMALE) {
            fibreComments.add("The NHS posted an article suggested that young women with a high fibre diet " +
                    "may have a lower breast cancer risk.");
        } else if (fibre < 10 && person.getGender() == Gender.FEMALE) {
            fibreComments.add("The NHS posted an article suggesting high fibre intake for young women could reduce " +
                    "breast cancer risk.");
        } else if (fibre > 10 && (person.getGender() == Gender.MALE || person.getGender() == Gender.FEMALE)) {
            fibreComments.add("The NHS posted an article conducted by British and Dutch researchers that suggested " +
                    "high fibre intake could could reduce the risk of colorectal cancer.");
        }

        //If the score happens to exceed 100, then set the score to the max value (100)
        if (returnScore > 100) {
            returnScore = 100;
        }
        return returnScore;
    }

    //Calculates a score for the protein contained in the food item
    protected static int calculateProteinScore(double protein, List<String> currentRow, ProfileObject person) {
        //First thing to do is to find what percentage the inputted value is compared to the rda
        double percentage = (protein / (Double.parseDouble(currentRow.get(7))) ) * 100;
        int returnScore = (int)percentage;

        //If the protein is above 30g in a single meal, body might not be able to absorb it all
        if (protein > 30) {
            proteinComments.add("Consuming more than 30g of protein in one sitting has been proven to be " +
                    "pointless as the excess is excreted as waste product");
            returnScore += 25;
        }

        //According to EU, food that has protein content of over 20% of total energy value is considered high protein
        if(person.getActivityLevel() == ActivityLevel.HIGH && protein > 20 && protein < 30 && person.getGoal() ==
                Goal.GAIN) {
            proteinComments.add("This food item is high in protein according to the European Commission!");
            proteinComments.add("Consuming protein during, before or after high levels of exercise is said to " +
                    "be a good way to increase muscle mass!");
            //Score decrease
        } else if (person.getActivityLevel() == ActivityLevel.HIGH && protein > 20 && protein < 30
                && person.getGoal() == Goal.LOSE) {
            proteinComments.add("High protein whilst trying to loose weight is said to be fine as long as the protein " +
                    "is not contributing to excess calories");
        } else if (person.getActivityLevel() == ActivityLevel.HIGH && protein < 5) {
            proteinComments.add("It may be beneficial to consume a food item higher in protein after or before " +
                    "exercise to maximise the benefit.");
            returnScore += 10;
            //score increase
        } else if (protein > 20 && protein < 30) {
            proteinComments.add("This food item is high in protein according to the European Commission!");
            //score increase
        }
        //If the score happens to exceed 100, then set the score to the max value (100)
        if (returnScore > 100) {
            returnScore = 100;
        }
        return 5;
    }

    //Calculates a score for the fibre contained in the food item
    //MAX SCORE = 100% + 10
    protected static int calculateSaltScore(double salt, List<String> currentRow, ProfileObject person) {
        //First thing to do is to find what percentage the inputted value is compared to the rda
        double percentage = (salt/ (Double.parseDouble(currentRow.get(8))) ) * 100;
        int returnScore = (int)percentage;
        System.out.println(percentage);

        //Eat-well guide measures
        if (salt > 1.5) {
            //Classed as high a salt content item per 100g by the eat-well guide
            saltComments.add("Eatwell guide suggests salt content higher than 1.5g per 100g of food item " +
                    "is considered high intake.");
            returnScore += 50;
        } else if (salt <= 1.5 && salt >= 0.3) {
            //Classed as a medium salt content item per 100g by the eat-well guide
            saltComments.add("Eatwell guide suggests salt content between 0.3g and 1.5g per 100g of food item " +
                    "is considered medium intake.");
            returnScore += 25;
        } else if (salt < 0.3) {
            //Classed as a low salt content item per 100g by the eat-well guide
            saltComments.add("Eatwell guide suggests salt content lower than 0.3g per 100g of food item " +
                    "is considered low intake");
        }

        //If the score happens to exceed 100, then set the score to the max value (100)
        if (returnScore > 100) {
            returnScore = 100;
        }
        return returnScore;
    }

    //Function that outputs a no decision from the algorithm
    private void outputRedDecision(DecisionObject decisionMade, ActionEvent event) {
        try {
            //Sets the decision string to red
            decisionMade.setDecisionString("red");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("RedResultOuput.fxml"));
            Parent outputView = loader.load();
            //access the controller and call the method
            RedResultController controller = loader.getController();
            controller.storeValues(decisionMade, outputFoodItem, outputPerson, currentRow);

            Scene newScene = new Scene(outputView);
            Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Food item advice");
            primaryStage.setScene(newScene);
            primaryStage.show();
            //end new window code
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Function that outputs a maybe decision from the algorithm
    private void outputAmberDecision(DecisionObject decisionMade, ActionEvent event) {
        try {
            //Sets the decision string to amber
            decisionMade.setDecisionString("amber");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AmberResultOutput.fxml"));
            Parent outputView = loader.load();
            //access the controller and call the method
            AmberResultController controller = loader.getController();
            controller.storeValues(decisionMade, outputFoodItem, outputPerson, currentRow);

            Scene newScene = new Scene(outputView);
            Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Food item advice");
            primaryStage.setScene(newScene);
            primaryStage.show();
            //end new window code
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Function that outputs a yes decision from the algorithm
    private void outputGreenDecision(DecisionObject decisionMade, ActionEvent event) {
        try {
            //Sets the decision string to green
            decisionMade.setDecisionString("green");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("GreenResultOutput.fxml"));
            Parent outputView = loader.load();
            //access the controller and call the method
            GreenResultController controller = loader.getController();
            controller.storeValues(decisionMade, outputFoodItem, outputPerson, currentRow);

            Scene newScene = new Scene(outputView);
            Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Food item advice");
            primaryStage.setScene(newScene);
            primaryStage.show();
            //end new window code
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static int returnRowIndex(int age) {
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

        return lines;
    }
    

    //Function that takes back the object inputs from the graph

}
