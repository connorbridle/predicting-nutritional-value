import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TestingClass {
    public static int[][] myArray = new int[2][2];

    public static void main(String[] args) {
        double test  = randomWeight();
        System.out.println(test);
    }
    private static void showMatrixValues(String variableLabel, int[][] input) {
        //Method that prints out the matrix containers
        for (int[] row: myArray) {
            System.out.print(variableLabel + ": ");
            for (int y: row) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }

    private static double randomWeight() {
        double randomNumber;
        float min = -1; float max = 1;
        Random rand = new Random();
        randomNumber = rand.nextFloat() * (max-min) + min;
        return randomNumber;
    }
}
