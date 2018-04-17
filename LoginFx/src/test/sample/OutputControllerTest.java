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
        rowIndex = myOutput.returnRowIndex(age); //TODO age input
        //

    }

    //================================================================================
    // Unit testing section 1
    //================================================================================
    @Test
    public void caloriesScoreTest() {
        //Variables to be passed to the method
        double calInput = 500.0;
        int expectedScore = 100;
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
        int myActualOutput = myOutput.calculateCaloriesScore(calInput, currentRow, person);

        assertEquals(expectedScore, myActualOutput);

    }

    @Test
    public void fatScoreTest() {
        //Variables to be passed to the method.
        double fatInput = 90;
        int expectedResult = 100;
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
        int myActualOutput = myOutput.calculateFatScore(fatInput, currentRow, person);

        //Test statement
        assertEquals(expectedResult, myActualOutput);
    }

    @Test
    public void satFatScoreTest() {
        double satFatInput = 30.0;
        int expectedScore = 100;
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
        int myActualOutput = myOutput.calculateSatFatScore(satFatInput, currentRow, person);

        //Testing statement
        assertEquals(expectedScore, myActualOutput);
    }

    @Test
    public void carbsScoreTest() {
        //Testing for a pass score
        double carbsInput = 95.0;
        int expectedScore = 100;
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
        int myActualOutput = myOutput.calculateCarbsScore(carbsInput, currentRow, person);

        //Testing statement
        assertEquals(expectedScore, myActualOutput);
    }

    @Test
    public void sugarsScoreTest() {
        //Testing for a pass score
        double sugarsInput = 50.0;
        int expectedScore = 100;
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
        int myActualOutput = myOutput.calculateSugarsScore(sugarsInput, currentRow, person);

        //Testing statement
        assertEquals(expectedScore, myActualOutput);
    }

    @Test
    public void fibreScoreTest() {
        //Testing for a pass score
        double fibreInput = 30.0;
        int expectedScore = 100;
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
        int myActualOutput = myOutput.calculateFibreScore(fibreInput, currentRow, person);

        //Testing statement
        assertEquals(expectedScore, myActualOutput);
    }

    @Test
    public void proteinScoreTest() {
        //Testing for a pass score
        double proteinInput = 25.0;
        int expectedScore = 100;
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
        int myActualOutput = myOutput.calculateProteinScore(proteinInput, currentRow, person);

        //Testing statement
        assertEquals(expectedScore, myActualOutput);
    }

    @Test
    public void saltScoreTest() {
        //Testing for a pass score
        double saltInput = 4.0;
        int expectedScore = 100;
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
        int myActualOutput = myOutput.calculateSaltScore(saltInput, currentRow, person);

        //Testing statement
        assertEquals(expectedScore, myActualOutput);
    }
}