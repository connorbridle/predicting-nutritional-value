import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class TestingClass {
    public static int[][] myArray = new int[2][2];

    public static void main(String[] args) {
//        ArrayList<double[]> myArrayList = readRDADataStructure("/Users/connorbridle/Desktop/Third-Year-Project/typ/LoginFx/src/sample/Females_Datastructure.csv");
        csvParser();
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

    //Function that reads from the csv datastructures that contain government RDA
    private static ArrayList<double[]> readRDADataStructure(String fileName) {
        int rowSize = 9; //Age,Cal,Fat,Satfat,carbs,sugar,fibre,protein,salt
        ArrayList<double[]> myArrayList = new ArrayList<>();
        Scanner scanner = null; //Initialise scanner
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            scanner = new Scanner(br);
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                double[] row = new double[rowSize]; //Creates new row
                //For each number in the row
                for (int i = 0; i <rowSize; i++) {
                    row[i] = scanner.nextDouble(); //Get the next int

                }
                myArrayList.add(row); //Add the row to the array list

                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
        } catch (FileNotFoundException fnf) {
            fnf.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InputMismatchException im) {
            im.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return myArrayList;
    }

    private static double[][] readRDADataStructure2(String fileName) {
        int rowSize = 9; //Age,Cal,Fat,Satfat,carbs,sugar,fibre,protein,salt
        double[][] matrix = new double[83][9];
        System.out.println(matrix.length);
        System.out.println(matrix[1].length);
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
            scanner.useDelimiter(Pattern.compile(",|\r\n"));
            for (int i = 0; i < matrix.length; i++) {
                for (int item = 0; item < matrix[i].length; i++) {
                    matrix[i][item] = scanner.nextDouble();
                }
            }

        } catch (FileNotFoundException fnf) {
            fnf.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InputMismatchException im) {
            im.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return matrix;
    }

    //TODO finish off calling this method to fill the rda lists defined above
    public static List<List<String>> csvParser() {
        String filePath = "/Users/connorbridle/Desktop/Third-Year-Project/typ/LoginFx/src/sample/Females_Datastructure.csv";
        File file= new File(filePath);

        // this gives you a 2-dimensional array of strings
        List<List<String>> lines = new ArrayList<>();
        Scanner inputStream;

        try {
            inputStream = new Scanner(file);

            while(inputStream.hasNext()){
                String line= inputStream.next();
                String[] values = line.split(",");
                // this adds the currently parsed line to the 2-dimensional string array
                lines.add(Arrays.asList(values));
            }

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        //Loop through arraylist
//        for (int i = 0; i < lines.size(); i++) {
//            for (int j = 0; i < lines.get(i).size(); i++) {
//                //Do some stuff
//            }
//        }

        return lines;
    }

}
