package sample;

import java.io.Serializable;

public class ProfileObject implements Serializable {

    private int age;
    private String username;
    private Gender gender;
    private int currentHour; //TODO Current hour will be set in here manually using gregorian calender
    private ActivityLevel activityLevel;
    private Goal goal;
    //Variables that hold the summed intake of all macro-nutrients
    private double totalIntakeCal;
    private double totalIntakeFat;
    private double totalIntakeSatFat;
    private double totalIntakeCarbs;
    private double totalIntakeSugars;
    private double totalIntakeFibre;
    private double totalIntakeProtein;
    private double totalIntakeSalt;


    public ProfileObject(int age, String username, Gender gender, int currentHour, ActivityLevel activityLevel, Goal goal) {
        this.age = age;
        this.username = username;
        this.gender = gender;
        this.currentHour = currentHour;
        this.activityLevel = activityLevel;
        this.goal = goal;
        totalIntakeCal = 0.0;
        totalIntakeFat = 0.0;
        totalIntakeSatFat = 0.0;
        totalIntakeCarbs = 0.0;
        totalIntakeSugars = 0.0;
        totalIntakeFibre = 0.0;
        totalIntakeProtein = 0.0;
        totalIntakeSalt = 0.0;
    }


    public ProfileObject(String username, int age, Gender gender, ActivityLevel activityLevel, Goal goal) {
        this.age = age;
        this.username = username;
        this.gender = gender;
        this.currentHour = currentHour;
        this.activityLevel = activityLevel;
        this.goal = goal;


    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getCurrentHour() {
        return currentHour;
    }

    public void setCurrentHour(int currentHour) {
        this.currentHour = currentHour;
    }

    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public double getTotalIntakeCal() {
        return totalIntakeCal;
    }

    public void setTotalIntakeCal(double totalIntakeCal) {
        this.totalIntakeCal = totalIntakeCal;
    }

    public double getTotalIntakeFat() {
        return totalIntakeFat;
    }

    public void setTotalIntakeFat(double totalIntakeFat) {
        this.totalIntakeFat = totalIntakeFat;
    }

    public double getTotalIntakeSatFat() {
        return totalIntakeSatFat;
    }

    public void setTotalIntakeSatFat(double totalIntakeSatFat) {
        this.totalIntakeSatFat = totalIntakeSatFat;
    }

    public double getTotalIntakeCarbs() {
        return totalIntakeCarbs;
    }

    public void setTotalIntakeCarbs(double totalIntakeCarbs) {
        this.totalIntakeCarbs = totalIntakeCarbs;
    }

    public double getTotalIntakeSugars() {
        return totalIntakeSugars;
    }

    public void setTotalIntakeSugars(double totalIntakeSugars) {
        this.totalIntakeSugars = totalIntakeSugars;
    }

    public double getTotalIntakeFibre() {
        return totalIntakeFibre;
    }

    public void setTotalIntakeFibre(double totalIntakeFibre) {
        this.totalIntakeFibre = totalIntakeFibre;
    }

    public double getTotalIntakeProtein() {
        return totalIntakeProtein;
    }

    public void setTotalIntakeProtein(double totalIntakeProtein) {
        this.totalIntakeProtein = totalIntakeProtein;
    }

    public double getTotalIntakeSalt() {
        return totalIntakeSalt;
    }

    public void setTotalIntakeSalt(double totalIntakeSalt) {
        this.totalIntakeSalt = totalIntakeSalt;
    }

    public void sumTotalIntakeCals(double amount) {
        totalIntakeCal += amount;
    }
}
