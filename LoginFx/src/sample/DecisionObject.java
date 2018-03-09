package sample;

import java.util.ArrayList;

public class DecisionObject {
    private FoodItem foodItem;
    private int score;
    private ArrayList<String> calsComments;
    private ArrayList<String> fatComments;
    private ArrayList<String> satFatComments;
    private ArrayList<String> carbsComments;
    private ArrayList<String> sugarsComments;
    private ArrayList<String> fibreComments;
    private ArrayList<String> proteinComments;
    private ArrayList<String> saltComments;
    private ArrayList<String> generalCommentsComments;

    public DecisionObject(FoodItem foodItem, int score) {
        this.foodItem = foodItem;
        this.score = score;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<String> getCalsComments() {
        return calsComments;
    }

    public ArrayList<String> getCarbsComments() {
        return carbsComments;
    }

    public ArrayList<String> getFatComments() {
        return fatComments;
    }

    public ArrayList<String> getSatFatComments() {
        return satFatComments;
    }

    public ArrayList<String> getSugarsComments() {
        return sugarsComments;
    }

    public ArrayList<String> getFibreComments() {
        return fibreComments;
    }

    public ArrayList<String> getProteinComments() {
        return proteinComments;
    }

    public ArrayList<String> getSaltComments() {
        return saltComments;
    }

    public ArrayList<String> getGeneralCommentsComments() {
        return generalCommentsComments;
    }
}
