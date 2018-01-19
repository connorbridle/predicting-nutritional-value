import java.util.ArrayList;
import java.util.Vector;

public class Network {
    //Class variables
    private ArrayList<Double> inputVals;
    private ArrayList<Double> targetVals;
    private Vector<Layer> m_layers = new Vector<>(); //m_layers[layerNum][neuronNum]
    Vector<Neuron> Layer;
    ArrayList<Double> resultsVals;
    static int[] topology; //Assuming you only want a 3 layered network

    //Constructor that creates the network of neurons
    public Network(int[] topology) {
        //Initialising the arrays
        inputVals = new ArrayList<>();
        targetVals = new ArrayList<>();
        int numOfLayers = topology.length;
        for (int layerNum = 0; layerNum < numOfLayers; layerNum++) {
            m_layers.add(new Layer()); //Creates a new layer then adds to the m_layers structure
            //Inner loops goes through and adds the individual neurons to that layer
            //Add a bias neuron to the layer (that's why <= adds one extra bias neuron)
            for (int neuronNum = 0; neuronNum <= topology[layerNum]; neuronNum++) {
                m_layers.lastElement().add(new Neuron());
                System.out.println("Made a neuron!");
            }
        }
    }

    //Feeds forward the activations throughout the network
    private void feedForward(ArrayList<Double> inputVals) {

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
