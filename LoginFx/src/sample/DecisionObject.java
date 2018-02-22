package sample;

public class DecisionObject {
    private FoodItem foodItem;
    private int score;

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
}
