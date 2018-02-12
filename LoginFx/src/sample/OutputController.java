package sample;

import java.util.ArrayList;
import java.util.Arrays;

public class OutputController {

    FoodItem currentFoodItem;

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

    //Main function that communicates with the other feeder functions
    public void startPrediction(FoodItem inputtedFoodItem) {
        currentFoodItem = inputtedFoodItem;
        System.out.println("GOT FOOD ITEM: " + currentFoodItem.getItemFat());

        double[] measurementArray = {totalIntakeCal,totalIntakeFat,totalIntakeSatFat,totalIntakeCarbs,totalIntakeSugars,
                totalIntakeFibre,totalIntakeProtein,totalIntakeSalt};
        double[] measurementRDA = {RDA_CALS, RDA_FAT, RDA_SATFAT, RDA_CARBS, RDA_SUGARS,
                RDA_FIBRE, RDA_PROTEIN, RDA_SALT};
        double[] output = populateMeasurementArray(measurementArray, measurementRDA);

        System.out.println(overRDA(output));
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

    //TODO need to implement all the methods that will calculate the individul scores and influence what is placed on the screen in results


}
