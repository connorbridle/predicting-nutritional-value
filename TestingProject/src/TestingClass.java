import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TestingClass {
    public static int[][] myArray = new int[2][2];

    public static void main(String[] args) {
        myArray[0][0] = 15;
        myArray[0][1] = 20;
        myArray[1][0] = 1;
        myArray[1][1] = 11;
        showMatrixValues("Input", myArray);
        String temp;
        int counter1 = 0;
        int counter2 = 0;
        double[][] returnArray = new double[2000][2000];
        String mys = "in: 0.0 0.0\n" +
                "out: 0.0\n" +
                "in: 1.0 0.0\n" +
                "out: 1.0\n" +
                "in: 1.0 0.0\n" +
                "out: 1.0\n" +
                "in: 0.0 1.0\n" +
                "out: 1.0\n" +
                "in: 1.0 1.0 \n" +
                "out: 0.0\n" +
                "in: 0.0 1.0\n" +
                "out: 1.0\n" +
                "in: 1.0 0.0";
        Scanner s = new Scanner(mys);
        while (s.hasNextLine()) {
            temp = s.nextLine();
            if (temp.contains("in:")) {
                System.out.println("It does!");
                temp = temp.replaceAll("in: ",""); //Removes 'in:'
                String[] tempArray = temp.split(" ");
                System.out.println(Arrays.toString(tempArray));
                //Take values
                //Add it to double array at variable
            }
            System.out.println(temp);
        }

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
}
