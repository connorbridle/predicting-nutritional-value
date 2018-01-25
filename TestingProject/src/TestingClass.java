import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TestingClass {
    public static int[][] myArray = new int[2][2];

    public static void main(String[] args) {
        String temp;
        ArrayList<ArrayList<Double>> listOfInputs = new ArrayList<>();
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
            ArrayList<Double> inputPair = new ArrayList<>();
            temp = s.nextLine();
            if (temp.contains("in:")) {
                temp = temp.replaceAll("in: ",""); //Removes 'in:'
                String[] tempArray = temp.split(" ");
                inputPair.add(Double.parseDouble(tempArray[0]));
                inputPair.add(Double.parseDouble(tempArray[1]));
                listOfInputs.add(inputPair);
                System.out.println(Arrays.toString(tempArray));
            }
            System.out.println(temp);
            System.out.println(listOfInputs);
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
