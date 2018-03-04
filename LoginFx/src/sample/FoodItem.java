package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class FoodItem {
    //Class that deals with all the pre-stored food items that may not have a food table
    private static SimpleDoubleProperty itemCals, itemFat, itemSatFat, itemCarbs, itemSugar, itemFibre, itemProtein, itemSodium;
    private static SimpleStringProperty itemName;

    //Constructor
    public FoodItem(String name, double calories, double fat, double satFat, double carbs, double sugars, double fibre,
                    double protein, double sodium) {
        itemName = new SimpleStringProperty(name);
        itemCals = new SimpleDoubleProperty(calories);
        itemFat = new SimpleDoubleProperty(fat);
        itemSatFat = new SimpleDoubleProperty(satFat);
        itemCarbs = new SimpleDoubleProperty(carbs);
        itemSugar = new SimpleDoubleProperty(sugars);
        itemFibre = new SimpleDoubleProperty(fibre);
        itemProtein = new SimpleDoubleProperty(protein);
        itemSodium = new SimpleDoubleProperty(sodium);
    }

    //Get methods for the private variables
    public static double getItemCals() {
        return itemCals.get();
    }

    public static double getItemCarbs() {
        return itemCarbs.get();
    }

    public double getItemFat() {
        return itemFat.get();
    }

    public static double getItemFibre() {
        return itemFibre.get();
    }

    public static double getItemProtein() {
        return itemProtein.get();
    }

    public static double getItemSatFat() {
        return itemSatFat.get();
    }

    public static double getItemSodium() {
        return itemSodium.get();
    }

    public static double getItemSugar() {
        return itemSugar.get();
    }

//    public static void setItemCals(double itemCals) {
//        FoodItem.itemCals.get() = itemCals;
//    }
//
//    public static void setItemCarbs(double itemCarbs) {
//        FoodItem.itemCarbs = itemCarbs;
//    }
//
//    public static void setItemFat(double itemFat) {
//        FoodItem.itemFat = itemFat;
//    }
//
//    public static void setItemFibre(double itemFibre) {
//        FoodItem.itemFibre = itemFibre;
//    }
//
//    public static void setItemName(String itemName) {
//        FoodItem.itemName = itemName;
//    }
//
//    public static void setItemProtein(double itemProtein) {
//        FoodItem.itemProtein = itemProtein;
//    }
//
//    public static void setItemSatFat(double itemSatFat) {
//        FoodItem.itemSatFat = itemSatFat;
//    }
//
//    public static void setItemSodium(double itemSodium) {
//        FoodItem.itemSodium = itemSodium;
//    }
//
//    public static void setItemSugar(double itemSugar) {
//        FoodItem.itemSugar = itemSugar;
//    }
}

