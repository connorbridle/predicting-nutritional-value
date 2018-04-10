package sample;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OutputControllerTest {
    OutputController myOutput = new OutputController();
    List<List<String>> maleRDA;
    String maleRDAFilePath = "/Users/connorbridle/Desktop/Third-Year-Project/typ/LoginFx/src/sample/Males_Datastructure.csv";
    List<List<String>> femaleRDA;
    String femaleRDAFilePath = "/Users/connorbridle/Desktop/Third-Year-Project/typ/LoginFx/src/sample/Females_Datastructure.csv";
    List<String> currentRow;
    int rowIndex;
    ProfileObject person;

    public void temp(int age) {
        maleRDA = myOutput.csvParser(maleRDAFilePath);
        femaleRDA = myOutput.csvParser(femaleRDAFilePath);
        rowIndex = myOutput.returnRowIndex(22); //TODO age input
        //

    }

    @Test
    public void loadFoodItem() throws Exception {

    }

    @Test
    public void caloriesScoreTest() {
        //Variables to be passed to the method
        double calInput = 100.0;
        person = new ProfileObject("Test", 22, Gender.MALE, ActivityLevel.LOW, Goal.MAINTAIN);
        Gender inputtedGender = person.getGender();
        temp(person.getAge());
        if (inputtedGender == Gender.MALE) {
            currentRow = maleRDA.get(rowIndex);
        } else if (inputtedGender == Gender.FEMALE) {
            currentRow = femaleRDA.get(rowIndex);
        } else {
            currentRow = maleRDA.get(rowIndex);
        }
        int myActualOutput = myOutput.calculateCaloriesScore(calInput,currentRow, person);

        assertEquals(10,myActualOutput);
    }

    @Test
    public void fatScoreTest() {

    }

    @Test
    public void satFatScoreTest() {

    }

    @Test
    public void carbsScoreTest() {

    }

    @Test
    public void sugarsScoreTest() {

    }

    @Test
    public void fibreScoreTest() {

    }

    @Test
    public void proteinScoreTest() {

    }

    @Test
    public void saltScoreTest() {

    }
}