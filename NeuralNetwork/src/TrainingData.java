import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

//---------------------------------------------------------------//
//Class that gains the training data from a text file and places it

public class TrainingData {


    public TrainingData(String fileLocation) {
        Scanner in;
        try {
            Scanner s = new Scanner(new File(fileLocation));
            ArrayList<String> list=new ArrayList<>();
            int rows = s.nextInt();
            int columns = s.nextInt();
            int returnMatrix[][] = new int[rows][columns];
            while (s.hasNextLine()) {
                s.next(Pattern.compile("in:........"));
            }
        } catch (FileNotFoundException i) {
            System.out.println("File not found!");
        }
    }

    public boolean isEndOfFile() {

        return false;
    }

    public void getTopology() {

    }

    //Differed from this now as we have a row counter so maybe just get input values
    public void getNextInputValues() {

    }

    public void getTargetOutputValues(double[] targetVals) {

    }
}
