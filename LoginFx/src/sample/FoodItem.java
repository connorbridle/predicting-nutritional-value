package sample;

public class FoodItem {
    //Class that deals with all the pre-stored food items that may not have a food table
    private static double itemCals, itemFat, itemSatFat, itemCarbs, itemSugar, itemFibre, itemProtein, itemSodium;

    //Constructor
    public FoodItem(double calories, double fat, double satFat, double carbs, double sugars, double fibre,
                    double protein, double sodium) {
        itemCals = calories;
        itemFat = fat;
        itemSatFat = satFat;
        itemCarbs = carbs;
        itemSugar = sugars;
        itemFibre = fibre;
        itemProtein = protein;
        itemSodium = sodium;
    }

    //Get methods for the private variables
    public static double getItemCals() {
        return itemCals;
    }

    public static double getItemCarbs() {
        return itemCarbs;
    }

    public double getItemFat() {
        return itemFat;
    }

    public static double getItemFibre() {
        return itemFibre;
    }

    public static double getItemProtein() {
        return itemProtein;
    }

    public static double getItemSatFat() {
        return itemSatFat;
    }

    public static double getItemSodium() {
        return itemSodium;
    }

    public static double getItemSugar() {
        return itemSugar;
    }
}
