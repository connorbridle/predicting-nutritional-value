import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//---------------------------------------------------------------//
//Class that gains the training data from a text file and places it

public class TrainingData {

    public int trainingPassesMade = 0;

    public TrainingData(String fileLocation) {
        try {
            File file = new File(fileLocation);
            Scanner myScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public boolean isEndOfFile() {

        return false;
    }

    public void getTopology() {

    }

    public void getNextInputValues() {

    }

    public void getTargetOutputValues() {

    }
}
