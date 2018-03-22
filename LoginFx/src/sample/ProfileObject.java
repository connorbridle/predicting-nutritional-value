package sample;

public class ProfileObject {

    private int age;
    private String username;
    private Gender gender;
    private int currentHour; //TODO Current hour will be set in here manually using gregorian calender
    private ActivityLevel activityLevel;
    private Goal goal;

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
}
