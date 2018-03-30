package sample;

import java.util.ArrayList;

public class DecisionObject {
    private FoodItem foodItem;
    private String decisionString;
    private int[] individualScore;
    private int overallScore;
    private ArrayList<String> calsComments;
    private ArrayList<String> fatComments;
    private ArrayList<String> satFatComments;
    private ArrayList<String> carbsComments;
    private ArrayList<String> sugarsComments;
    private ArrayList<String> fibreComments;
    private ArrayList<String> proteinComments;
    private ArrayList<String> saltComments;
    private ArrayList<String> generalCommentsComments;

    //TODO add decision string to the constructor
    public DecisionObject(String decision, FoodItem foodItem, int[] individualScore, int overallScore, ArrayList<String> calsComments,
                          ArrayList<String> fatComments, ArrayList<String> satFatComments, ArrayList<String> carbsComments,
                          ArrayList<String> sugarsComments, ArrayList<String> fibreComments,
                          ArrayList<String> proteinComments, ArrayList<String> saltComments,
                          ArrayList<String> generalCommentsComments) {
        this.foodItem = foodItem;
        this.individualScore = individualScore;
        this.overallScore = overallScore;
        this.calsComments = calsComments;
        this.fatComments = fatComments;
        this.satFatComments = satFatComments;
        this.carbsComments = carbsComments;
        this.sugarsComments = sugarsComments;
        this.fibreComments = fibreComments;
        this.proteinComments = proteinComments;
        this.saltComments = saltComments;
        this.generalCommentsComments = generalCommentsComments;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public void setScore(int score) {
        this.overallScore = score;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public int getScore() {
        return overallScore;
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

    public int[] getIndividualScore() {
        return individualScore;
    }

    public String getDecisionString() {
        return decisionString;
    }

    public void setDecisionString(String decisionString) {
        this.decisionString = decisionString;
    }

    public void setIndividualScore(int[] individualScore) {
        this.individualScore = individualScore;
    }

    public void setOverallScore(int overallScore) {
        this.overallScore = overallScore;
    }

    public void setCalsComments(ArrayList<String> calsComments) {
        this.calsComments = calsComments;
    }

    public void setFatComments(ArrayList<String> fatComments) {
        this.fatComments = fatComments;
    }

    public void setSatFatComments(ArrayList<String> satFatComments) {
        this.satFatComments = satFatComments;
    }

    public void setCarbsComments(ArrayList<String> carbsComments) {
        this.carbsComments = carbsComments;
    }

    public void setSugarsComments(ArrayList<String> sugarsComments) {
        this.sugarsComments = sugarsComments;
    }

    public void setFibreComments(ArrayList<String> fibreComments) {
        this.fibreComments = fibreComments;
    }

    public void setProteinComments(ArrayList<String> proteinComments) {
        this.proteinComments = proteinComments;
    }

    public void setSaltComments(ArrayList<String> saltComments) {
        this.saltComments = saltComments;
    }

    public void setGeneralCommentsComments(ArrayList<String> generalCommentsComments) {
        this.generalCommentsComments = generalCommentsComments;
    }
}

