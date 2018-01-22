import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Neuron extends Layer{
    private double outputVal;
    private Vector<Connection> outputWeights = new Vector<>();
    private ArrayList<Connection> outputWeights2 = new ArrayList<>();
    private int m_myIndex;
    private double gradient;
    private static double learningRate = 0.15; //[0.0....1.0] overall training weight of the neural network
    private static double alpha = 0.5; //[0.0...n] Multiplier of last weight change (momentum)

    //Constructor
    public Neuron(int numOfOutputs, int myIndex) {
        for (int connection = 0; connection < numOfOutputs; connection++) {
            outputWeights2.add(new Connection()); //Adds a new connection
            outputWeights2.get(outputWeights2.size()-1).setWeight(randomWeight());//Sets the weight held on that connection to a random number
        }
        m_myIndex = myIndex;
    }

    //Function that generates a random double value between two values
    private double randomWeight() {
        double randomNumber;
        float min = -1; float max = 1;
        Random rand = new Random();
        randomNumber = rand.nextFloat() * (max-min) + min;
        return randomNumber;
    }

    public double getOutputVal() {
        return outputVal;
    }

    public void setOutput(double input) {
        outputVal = input;
    }

    public void feedForward(Layer previousLayer) {
        double summedInputs = 0.0;
        //Sum the previous layers outputs which are our inputs
        for (int neuron = 0; neuron < previousLayer.size(); neuron++) {
            summedInputs += previousLayer.get(neuron).getOutputVal() *
                previousLayer.get(neuron).outputWeights2.get(m_myIndex).weight; //Adds the output value from current neuron to sum
        }

        outputVal = tanhActivation(summedInputs);
    }

    private static double reLUActivation(double summedInputs) {

        return summedInputs;
    }

    private static double reLUActivationDerivative(double summedInputs) {

        return summedInputs;
    }

    private static double tanhActivation(double summedInputs) {
        return Math.tanh(summedInputs);
    }

    private static double tanhDerivative(double summedInputs) {
        return 1.0 - summedInputs * summedInputs;
    }

    private double sumDOW(Layer nextLayer) {
        double sum = 0.0;

        //Sum of all the contributions that we make to the errors at the nodes that we feed in the next layer
        //Loop through all the neurons in the next layer
        for (int neuron = 0; neuron < nextLayer.size()-1; neuron++) {
            sum += outputWeights2.get(neuron).weight * nextLayer.get(neuron).gradient; //Weight that goes from our neuron to the other neuron
        }
        return sum;
    }

    public void calculateOutputGrads(double targetVal) {
        double errorDelta = targetVal - outputVal;
        gradient = errorDelta * tanhDerivative(outputVal);
    }
    public void calculateHiddenGrads(Layer nextLayer) {
        double dow = sumDOW(nextLayer);
        gradient = dow * tanhDerivative(outputVal);
    }

    public void updateInputWeights(Layer previousLayer) {
        //The weights to be updated are in the connection container
        //in the neurons preceding layer
        //May need to think about passing valuevsref here as he passes and updates the weights here.
        for (int neuron = 0; neuron < previousLayer.size(); neuron++) {
            Neuron previousLayerNeuron = previousLayer.get(neuron);
            double oldDeltaWeight = previousLayerNeuron.outputWeights2.get(m_myIndex).deltaWeight;
            //Forms the new change in weight that we apply to the connection
            double newDeltaWeight = learningRate * previousLayerNeuron.getOutputVal() * gradient
                    //also add momentum = a fraction of the previous delta weight
                    + alpha * oldDeltaWeight;

            previousLayerNeuron.outputWeights2.get(m_myIndex).deltaWeight = newDeltaWeight; //sets the delta weight
            previousLayerNeuron.outputWeights2.get(m_myIndex).weight += newDeltaWeight; //change the weight by adding the delta weight

        }
    }

}
