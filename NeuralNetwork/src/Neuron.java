import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Neuron extends Layer{
    private double outputVal;
    private Vector<Connection> outputWeights = new Vector<>();
    //Constructor
    public Neuron(int numOfOutputs) {
        for (int connection = 0; connection < numOfOutputs; connection++) {
            outputWeights.add(new Connection()); //Adds a new connection
            outputWeights.lastElement().setWeight(randomWeight());//Sets the weight held on that connection to a random number
        }
    }

    //Function that generates a random double value between two values
    private double randomWeight() {
        double randomNumber;
        float min = -1; float max = 1;
        Random rand = new Random();
        randomNumber = rand.nextFloat() * (max-min) + min;
        return randomNumber;
    }

    public int setOutput() {
        return 5;
    }

    public static void feedForward(Layer previousLayer) {

    }

}
