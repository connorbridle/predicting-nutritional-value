import java.util.ArrayList;
import java.util.Vector;

public class Network {
    //Class variables
    private Vector<Double> inputVals;
    private double[] inputVals2;
    //private Vector<Double> targetVals;
    private double[] targetVals;
    //private Vector<Layer> m_layers2 = new Vector<>(); //m_layers[layerNum][neuronNum]
    private ArrayList<Layer> m_layers2 = new ArrayList<>();
    static int[] topology; //Assuming you only want a 3 layered network
    private double m_error; //Holds the error
    private double recentAverageError;
    private double recentAverageSmoothingFactor;

    //Constructor that creates the network of neurons
    public Network(int[] topology) {
        //Initialising the arrays
        inputVals = new Vector<>();
        //targetVals =
        inputVals2 = new double[topology[0]];
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
    private void feedForward(double[] inputVals2) {
        //Check that the number of elements in input vals matches number of input nodes
        //Gets the first layer from the m_layers vector, and removes 1 from the size because of the bias neuron
        if (inputVals.size() == m_layers2.get(0).size()-1) {
            //Take the values from inputVals and latch them into input neuron
            for (int input = 0; input < inputVals.size(); input++) {
                m_layers2.get(0).get(input).setOutput(inputVals2[input]); //Sets the output of that neuron
            }

            //Feed forward
            for (int layerNum = 1; layerNum < m_layers2.size(); layerNum++) {
                Layer prevLayer = m_layers2.get(layerNum-1); //Reference to the previous layer
                for (int neuron = 0; neuron < m_layers2.get(layerNum).size()-1; neuron++) {
                    m_layers2.get(layerNum).get(neuron).feedForward(prevLayer);
                }
            }
        }
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
    private ArrayList<Double> getResults(ArrayList<Double> resultsVals) {
        resultsVals.clear(); //Clears results
        Layer myOutputLayer = m_layers2.get(m_layers2.size()-1); //For convenience in accessing output layer
        for (int neuron = 0; neuron < myOutputLayer.size()-1; neuron++) {
            resultsVals.add(myOutputLayer.get(neuron).getOutputVal());
        }

        return resultsVals;
    }

    public static void main(String[] args) {
        //Testing
        topology = new int[]{3,2,1};
        Network myNet = new Network(topology);

        //Some pseudo on how the loop of training is meant to go
        //While training data is not at the end of the file
            //Get the next two input layer
            //Collect the networks actual results
            //Train the net on what the outputs should have been
            //Report how well the training is working, average over recent samples

    }
}
