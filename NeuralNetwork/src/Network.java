import java.util.ArrayList;
import java.util.Vector;

public class Network {
    //Class variables
    private static double[][] inputVals2;
    private static double[] outputVals;
    private static double[] targetVals;
    private ArrayList<Layer> m_layers2 = new ArrayList<>(); //m_layers[layerNum][neuronNum]
    static int[] topology; //Assuming you only want a 3 layered network
    private double m_error; //Holds the error
    private double recentAverageError;
    private double recentAverageSmoothingFactor;
    private static int trainingPassesMade = 0;
    //General variables
    private int rowCounter;
    private int inputLayerSize;

    //Constructor that creates the network of neurons
    public Network(int[] topology) {
        inputLayerSize = topology[0]-1; //-1 to remove the bias node
        rowCounter = 0;
        //Initialising the arrays
        inputVals2 = new double[topology[0]][2];
        outputVals = new double[topology[topology.length-1]];
        int numOfLayers = topology.length; //Stores layer number in a variable
        for (int layerNum = 0; layerNum < numOfLayers; layerNum++) {
            m_layers2.add(new Layer()); //Creates a new layer then adds to the m_layers structure
            int numbOfOutputs = (layerNum == topology.length -1) ? 0 : topology[layerNum+1]; //If the layer is the output layer, 0 output connects, otherwise number of neurons in the next layer over
            //Inner loops goes through and adds the individual neurons to that layer
            //Add a bias neuron to the layer (that's why <= adds one extra bias neuron)
            for (int neuronNum = 0; neuronNum <= topology[layerNum]; neuronNum++) {
                //m_layers.lastElement().add(new Neuron(numbOfOutputs));
                m_layers2.get(m_layers2.size()-1).add(new Neuron(numbOfOutputs, neuronNum));
                System.out.println("Made a neuron!");
            }
        }

        System.out.println((m_layers2.get(0).get(1)).getClass().getName());
    }

    //Feeds forward the activations throughout the network
    private void feedForward(double[][] inputVals2) {
        //Check that the number of elements in input vals matches number of input nodes
        //Gets the first layer from the m_layers vector, and removes 1 from the size because of the bias neuron
        if (inputVals2.length == m_layers2.get(0).size()-1) {
            //Take the values from inputVals and latch them into input neuron
            for (int input = 0; input < inputLayerSize; input++) {
                m_layers2.get(0).get(input).setOutput(inputVals2[rowCounter][input]); //Sets the output of that neuron
            }

            //Feed forward
            for (int layerNum = 1; layerNum < m_layers2.size(); layerNum++) {
                Layer prevLayer = m_layers2.get(layerNum-1); //Reference to the previous layer
                for (int neuron = 0; neuron < m_layers2.get(layerNum).size()-1; neuron++) {
                    m_layers2.get(layerNum).get(neuron).feedForward(prevLayer);
                }
            }
        }
        rowCounter++; //Increment row counter to access inputVals structure
    }

    //Adjusts the weights of the connections dependent on the error attributed
    public void backPropagate(double[] targetVals) {
        //Calculate overall net error (Root mean square error of output neuron errors)
        Layer myOutputLayer = m_layers2.get(m_layers2.size()-1); //For convenience in accessing output layer
        m_error = 0.0;
        for (int neuron = 0; neuron < myOutputLayer.size()-1; neuron++) {
            double errorDelta = targetVals[neuron] - myOutputLayer.get(neuron).getOutputVal(); //Target value - actual value
            m_error += errorDelta * errorDelta; //After the loop m_error will be the sum of the errors
        }
        m_error /= myOutputLayer.size() -1; //Average error of the output layer squared (-1 to remove bias neuron)
        m_error = Math.sqrt(m_error); // Root mean square error

        //Recent error calculation code for feedback on networks performance
        recentAverageError = (recentAverageError * recentAverageSmoothingFactor + m_error) /
                (recentAverageSmoothingFactor + 1.0);

        //Calculate output layer gradients
        for (int neuron = 0; neuron < myOutputLayer.size()-1; neuron++) {
            myOutputLayer.get(neuron).calculateOutputGrads(targetVals[neuron]);
        }

        //Calculate gradients on hidden layers
        for (int layerNum = m_layers2.size()-2; layerNum > 0; layerNum--) {
            Layer myHiddenLayer = m_layers2.get(layerNum); //Convenience variable
            Layer myNextLayer = m_layers2.get(layerNum+1); //Convenience variable
            for (int neuron = 0; neuron < myHiddenLayer.size(); neuron++) {
                myHiddenLayer.get(neuron).calculateHiddenGrads(myNextLayer);
            }
        }

        //For all the layers from output to first hidden layer
        //update connection weights
        //Loop all layers
        for (int layerNum = m_layers2.size()-1; layerNum > 0; layerNum--) {
            Layer myLayer = m_layers2.get(layerNum);
            Layer myPrevLayer = m_layers2.get(layerNum-1);

            //Loop each neuron
            for (int neuron = 0; neuron < myLayer.size()-1; neuron++) {
                myLayer.get(neuron).updateInputWeights(myPrevLayer);
            }
        }

    }

    //API that will output the results
    //private ArrayList<Double> getResults(ArrayList<Double> resultsVals) {
    private double[] getResults(double[] outputVals) {
        //Clears results
        for (double row: outputVals) {
            row = 0.0;
        }
        Layer myOutputLayer = m_layers2.get(m_layers2.size()-1); //For convenience in accessing output layer
        for (int neuron = 0; neuron < myOutputLayer.size()-1; neuron++) {
            //resultsVals.add(myOutputLayer.get(neuron).getOutputVal());
            outputVals[0] = myOutputLayer.get(neuron).getOutputVal();
        }

        return outputVals;
    }

    //Overloaded for 2D array
    private static void showMatrixValues(String variableLabel, double[][] input) {
        //Method that prints out the matrix containers
        for (double[] row: input) {
            System.out.print(variableLabel + ": ");
            for (double y: row) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }

    //Overloaded for normal array
    private static void showMatrixValues(String variableLabel, double[] input) {
        //Method that prints out the matrix containers
        for (double row: input) {
            System.out.print(variableLabel + " ");
            System.out.print(row);
            System.out.println();
        }
    }

    private double getRecentAverageError() {
        return recentAverageError;
    }

    public static void main(String[] args) {
        //Testing
        topology = new int[]{3,2,1};
        Network myNet = new Network(topology);

        //Creation of new training data object
        TrainingData myTrainingData = new TrainingData("C:/Users/C Bridle W4P52/Desktop/trainingData.txr");

        //Some pseudo on how the loop of training is meant to go
        //While training data is not at the end of the file
        while (!myTrainingData.isEndOfFile()) {
            trainingPassesMade++; //Increment how many passes made
            System.out.println("Pass:" + trainingPassesMade);
            //Get the next two input layer
            showMatrixValues("Inputs: ", inputVals2);
            myNet.feedForward(inputVals2);
            //Collect the networks actual results
            myNet.getResults(outputVals);
            showMatrixValues("Outputs: ", outputVals);
            //Train the net on what the outputs should have been
            myTrainingData.getTargetOutputValues(targetVals);
            showMatrixValues("Targets: ", targetVals);
            if (targetVals.length == topology[topology.length-1]) {
                myNet.backPropagate(targetVals);
            }
            //Report how well the training is working, average over recent samples
            System.out.println("Net recent average error: " + myNet.getRecentAverageError());
        }
        System.out.println("Done");

    }
}
