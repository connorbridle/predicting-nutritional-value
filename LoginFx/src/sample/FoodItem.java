package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class FoodItem {
    //Class that deals with all the pre-stored food items that may not have a food table
    public final SimpleStringProperty itemName;
    public final SimpleDoubleProperty itemCals, itemFat, itemSatFat, itemCarbs, itemSugar, itemFibre, itemProtein, itemSodium;
    private Button button;

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
//        this.button = new Button("Select Item");
    }

    //Get methods for the private variables

    public double getItemCals() {
        return itemCals.get();
    }

    public double getItemCarbs() {
        return itemCarbs.get();
    }

    public double getItemFat() {
        return itemFat.get();
    }

    public double getItemFibre() {
        return itemFibre.get();
    }

    public double getItemProtein() {
        return itemProtein.get();
    }

    public double getItemSatFat() {
        return itemSatFat.get();
    }

    public double getItemSodium() {
        return itemSodium.get();
    }

    public double getItemSugar() {
        return itemSugar.get();
    }

    public String getItemName() {
        return itemName.get();
    }

    public Button getButton() {
        return button;
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    public void setItemCals(double itemCals) {
        this.itemCals.set(itemCals);
    }

    public void setItemFat(double itemFat) {
        this.itemFat.set(itemFat);
    }

    public void setItemSatFat(double itemSatFat) {
        this.itemSatFat.set(itemSatFat);
    }

    public void setItemCarbs(double itemCarbs) {
        this.itemCarbs.set(itemCarbs);
    }

    public void setItemSugar(double itemSugar) {
        this.itemSugar.set(itemSugar);
    }

    public void setItemFibre(double itemFibre) {
        this.itemFibre.set(itemFibre);
    }

    public void setItemProtein(double itemProtein) {
        this.itemProtein.set(itemProtein);
    }

    public void setItemSodium(double itemSodium) {
        this.itemSodium.set(itemSodium);
    }

    public void setButton(Button button) {
        this.button = button;
    }
}

