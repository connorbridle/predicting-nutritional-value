import java.util.ArrayList;
import java.util.Vector;

public class Network {
    //Class variables
    private Vector<Double> inputVals;
    private Vector<Double> targetVals;
    //private Vector<Layer> m_layers2 = new Vector<>(); //m_layers[layerNum][neuronNum]
    private ArrayList<Layer> m_layers2 = new ArrayList<>();
    Vector<Neuron> Layer;
    ArrayList<Double> resultsVals;
    static int[] topology; //Assuming you only want a 3 layered network

    //Constructor that creates the network of neurons
    public Network(int[] topology) {
        //Initialising the arrays
        inputVals = new Vector<>();
        targetVals = new Vector<>();
        int numOfLayers = topology.length; //Stores layer number in a variable
        for (int layerNum = 0; layerNum < numOfLayers; layerNum++) {
            m_layers2.add(new Layer()); //Creates a new layer then adds to the m_layers structure
            int numbOfOutputs = (layerNum == topology.length -1) ? 0 : topology[layerNum+1]; //If the layer is the output layer, 0 output connects, otherwise number of neurons in the next layer over
            //Inner loops goes through and adds the individual neurons to that layer
            //Add a bias neuron to the layer (that's why <= adds one extra bias neuron)
            for (int neuronNum = 0; neuronNum <= topology[layerNum]; neuronNum++) {
                //m_layers.lastElement().add(new Neuron(numbOfOutputs));
                m_layers2.get(m_layers2.size()-1).add(new Neuron(numbOfOutputs));
                System.out.println("Made a neuron!");
            }
        }
//        Neuron myN = m_layers2.get(0).get(0);
        for (Layer in: m_layers2)
            System.out.println(in);
        System.out.println(m_layers2.get(0).get(0));
        System.out.println((m_layers2.get(0).get(1)).getClass().getName());
        System.out.println((m_layers2.get(0).get(2)).setOutput());
        System.out.println((m_layers2.get(0).get(2)));
    }

    //Feeds forward the activations throughout the network
    private void feedForward(ArrayList<Double> inputVals) {
        //Check that the number of elements in input vals matches number of input nodes
        //Gets the first layer from the m_layers vector, and removes 1 from the size because of the bias node
        if (inputVals.size() == m_layers2.get(0).size()-1) {
            //Take the values from inputVals and latch them into input neuron
            for (int input = 0; input < inputVals.size(); input++) {
                //m_layers2.get(0).get(input).setOutput(inputVals.get(input));
                System.out.println(m_layers2.get(0).get(input));
            }

            //Feed forward
            for (int layerNum = 1; layerNum < m_layers2.size(); layerNum++) {
                Layer prevLayer = m_layers2.get(layerNum-1); //Reference to the previous layer
                for (int neuron = 0; neuron < m_layers2.get(layerNum).size()-1; neuron++) {
                    //m_layers2.get(layerNum).get(neuron).feedForward(prevLayer);
                    System.out.println(m_layers2.get(layerNum).get(neuron));
                }
            }
        }
    }

    //Adjusts the weights of the connections dependent on the error attributed
    private void backPropagate(ArrayList<Double> targetVals) {

    }

    //API that will output the results
    private ArrayList<Double> getResults(ArrayList<Double> resultsVals) {

        return resultsVals;
    }

    public static void main(String[] args) {
        //Testing
        topology = new int[]{3,2,1};
        Network myNet = new Network(topology);

    }
}
