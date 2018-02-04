import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
    private static ArrayList<ArrayList<Double>> listOfInputs = new ArrayList<>(); //AL to hold all the input tuples
    private static ArrayList<ArrayList<Double>> listOfTargets = new ArrayList<>(); //AL to hold all the target tuples
    private static ArrayList<ArrayList<Double>> listOfOutputs = new ArrayList<>(); //AL to hold all the output tuples
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
    private void feedForward(ArrayList<ArrayList<Double>> listOfInputs, int counter) {
        //Check that the number of elements in input vals matches number of input nodes
        //Gets the first layer from the m_layers vector, and removes 1 from the size because of the bias neuron
        System.out.println("Inputs: " + listOfInputs.get(counter));
        if (listOfInputs.get(counter).size() == m_layers2.get(0).size()-2) {
            //Take the values from listOfInputs and latch them into input neuron
            for (int input = 0; input < inputLayerSize; input++) {
                m_layers2.get(0).get(input).setOutput(listOfInputs.get(counter).get(input));
            }

            //Feed forward
            for (int layerNum = 1; layerNum < m_layers2.size(); layerNum++) {
                Layer prevLayer = m_layers2.get(layerNum-1); //Reference to the previous layer
                for (int neuron = 0; neuron < m_layers2.get(layerNum).size()-1; neuron++) {
                    m_layers2.get(layerNum).get(neuron).feedForward(prevLayer);
                }
            }
        }


//        if (inputVals2.length == m_layers2.get(0).size()-1) {
//            //Take the values from inputVals and latch them into input neuron
//            for (int input = 0; input < inputLayerSize; input++) {
//                m_layers2.get(0).get(input).setOutput(inputVals2[rowCounter][input]); //Sets the output of that neuron
//            }
//
//            //Feed forward
//            for (int layerNum = 1; layerNum < m_layers2.size(); layerNum++) {
//                Layer prevLayer = m_layers2.get(layerNum-1); //Reference to the previous layer
//                for (int neuron = 0; neuron < m_layers2.get(layerNum).size()-1; neuron++) {
//                    m_layers2.get(layerNum).get(neuron).feedForward(prevLayer);
//                }
//            }
//        }
//        rowCounter++; //Increment row counter to access inputVals structure
    }

    //Adjusts the weights of the connections dependent on the error attributed
    public void backPropagate(ArrayList<ArrayList<Double>> listOfTargets, int index) {
        //Calculate overall net error (Root mean square error of output neuron errors)
        Layer myOutputLayer = m_layers2.get(m_layers2.size()-1); //For convenience in accessing output layer
        m_error = 0.0;
        for (int neuron = 0; neuron < myOutputLayer.size()-1; neuron++) {
            System.out.println("Outputs: " + myOutputLayer.get(neuron).getOutputVal());
            double errorDelta = listOfTargets.get(index).get(neuron) - myOutputLayer.get(neuron).getOutputVal();
            m_error += errorDelta * errorDelta; //After the loop m_error will be the sum of the errors
        }
        m_error /= myOutputLayer.size() -1; //Average error of the output layer squared (-1 to remove bias neuron)
        m_error = Math.sqrt(m_error); // Root mean square error

        //Recent error calculation code for feedback on networks performance
        recentAverageError = (recentAverageError * recentAverageSmoothingFactor + m_error) /
                (recentAverageSmoothingFactor + 1.0);

        //Calculate the output layer gradients
        for (int neuron = 0; neuron < myOutputLayer.size()-1; neuron++) {
            myOutputLayer.get(neuron).calculateOutputGrads(listOfTargets.get(index).get(neuron));
        }

        //Calculate the gradients on hidden layers
        for (int layerNum = m_layers2.size()-2; layerNum > 0; layerNum--) {
            Layer myHiddenLayer = m_layers2.get(layerNum); //Variable for convenience
            Layer myNextLayer = m_layers2.get(layerNum+1); //Variable for convenience
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

        //OLD CODE
//        for (int neuron = 0; neuron < myOutputLayer.size()-1; neuron++) {
//            double errorDelta = targetVals[neuron] - myOutputLayer.get(neuron).getOutputVal(); //Target value - actual value
//            m_error += errorDelta * errorDelta; //After the loop m_error will be the sum of the errors
//        }
////        m_error /= myOutputLayer.size() -1; //Average error of the output layer squared (-1 to remove bias neuron)
////        m_error = Math.sqrt(m_error); // Root mean square error
//
////        //Recent error calculation code for feedback on networks performance
////        recentAverageError = (recentAverageError * recentAverageSmoothingFactor + m_error) /
////                (recentAverageSmoothingFactor + 1.0);
//
//        //Calculate output layer gradients
//        for (int neuron = 0; neuron < myOutputLayer.size()-1; neuron++) {
//            myOutputLayer.get(neuron).calculateOutputGrads(targetVals[neuron]);
//        }
//
//        //Calculate gradients on hidden layers
//        for (int layerNum = m_layers2.size()-2; layerNum > 0; layerNum--) {
//            Layer myHiddenLayer = m_layers2.get(layerNum); //Convenience variable
//            Layer myNextLayer = m_layers2.get(layerNum+1); //Convenience variable
//            for (int neuron = 0; neuron < myHiddenLayer.size(); neuron++) {
//                myHiddenLayer.get(neuron).calculateHiddenGrads(myNextLayer);
//            }
//        }
//
//        //For all the layers from output to first hidden layer
//        //update connection weights
//        //Loop all layers
//        for (int layerNum = m_layers2.size()-1; layerNum > 0; layerNum--) {
//            Layer myLayer = m_layers2.get(layerNum);
//            Layer myPrevLayer = m_layers2.get(layerNum-1);
//
//            //Loop each neuron
//            for (int neuron = 0; neuron < myLayer.size()-1; neuron++) {
//                myLayer.get(neuron).updateInputWeights(myPrevLayer);
//            }
//        }

    }

    //API that will output the results
    //private ArrayList<Double> getResults(ArrayList<Double> resultsVals) {
    private ArrayList<ArrayList<Double>> getResults(ArrayList<ArrayList<Double>> listOfOutputs) {
        //Clears results
        //This goes through all the values in the output lists and sets to 0 (not sure if that's what i want)
//        for (ArrayList<Double> row: listOfOutputs) {
//            for (double value: row) {
//                value = 0.0;
//            }
//        }
        
        
        ArrayList<Double> outputInnerArray = new ArrayList<>();
        Layer myOutputLayer = m_layers2.get(m_layers2.size()-1); //For convenience in accessing output layer
        for (int neuron = 0; neuron < myOutputLayer.size()-1; neuron++) {
            //resultsVals.add(myOutputLayer.get(neuron).getOutputVal());
            //outputVals[0] = myOutputLayer.get(neuron).getOutputVal();
            outputInnerArray.add(myOutputLayer.get(neuron).getOutputVal());
        }
        listOfOutputs.add(outputInnerArray);

        return listOfOutputs;
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

    //Function populates both the input and output arrays from a text file
    //Required format for text file is as follows
    //Inputs formatted on single line as "in: in1 in2 inN ..."
    //Outputs formatted on a single line as "out: out2 out2 outN ..."
    //Example text file:
    //in: 1.0 0.5 0.3 0.2
    //out: 1.0
    private static void populateInputAndOutputValues() {
        String temp;
        try {
            Scanner s = new Scanner(new File("src/trainingData.txt"));
            while (s.hasNextLine()) {
                //Input values
                ArrayList<Double> inputRow = new ArrayList<>();
                temp = s.nextLine();
                if (temp.contains("in:")) {
                    temp = temp.replaceAll("in: ",""); //Removes 'in:'
                    String[] tempArray = temp.split(" ");
                    //Loops through the array and adds each value to the return AL
                    for (int i = 0; i < tempArray.length; i++) {
                        inputRow.add(Double.parseDouble(tempArray[i]));
                    }
                    listOfInputs.add(inputRow);
                } else if (temp.contains("out:")) {
                    //Output values
                    ArrayList<Double> outputRow = new ArrayList<>();
                    temp = temp.replaceAll("out: ", "");
                    String[] tempArray2 = temp.split(" ");
                    //Loop through the array and add each value to the return AL
                    for (int i = 0; i < tempArray2.length; i++) {
                        outputRow.add(Double.parseDouble(tempArray2[i]));
                    }
                    listOfTargets.add(outputRow); //Adds the current output row to the outer AL container

                }
            }
        }catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        //Testing
        topology = new int[]{3,2,1};
        Network myNet = new Network(topology);

        //Populate input and output array lists with values from file
        populateInputAndOutputValues();
        System.out.println(listOfInputs);
        System.out.println(listOfTargets);

        //Main training loop
        while (trainingPassesMade< 10) {
            trainingPassesMade++;
            System.out.println("Pass: " + trainingPassesMade);
            //for loop that goes over every pair in the input values
            for (int i = 0; i < listOfInputs.size(); i++) {
                System.out.println("Training data input: " + i);
                myNet.feedForward(listOfInputs, i); //Get the next two inputs
                //It might not be a good idea here to have an arraylist of 4000000 values (2000inputs*2000passes)
                myNet.getResults(listOfOutputs);//Collect the networks actual results
                //If the size of the set of target values is equal to number of input nodes
                if (listOfTargets.get(i).size() == topology[topology.length-1]) {
                    myNet.backPropagate(listOfTargets, i);
                }
                System.out.println("Net recent average error: " + myNet.getRecentAverageError());
            }
        }

        //Creation of new training data object
        //TrainingData myTrainingData = new TrainingData("C:/Users/C Bridle W4P52/Desktop/trainingData.txr");

        //Some pseudo on how the loop of training is meant to go
        //While training data is not at the end of the file
//        while (!myTrainingData.isEndOfFile()) {
//            trainingPassesMade++; //Increment how many passes made
//            System.out.println("Pass:" + trainingPassesMade);
//            //Get the next two input layer
//            showMatrixValues("Inputs: ", inputVals2);
//            myNet.feedForward(inputVals2);
//            //Collect the networks actual results
//            myNet.getResults(outputVals);
//            showMatrixValues("Outputs: ", outputVals);
//            //Train the net on what the outputs should have been
//            myTrainingData.getTargetOutputValues(targetVals);
//            showMatrixValues("Targets: ", targetVals);
//            if (targetVals.length == topology[topology.length-1]) {
//                myNet.backPropagate(targetVals);
//            }
//            //Report how well the training is working, average over recent samples
//            System.out.println("Net recent average error: " + myNet.getRecentAverageError());
//        }
        System.out.println("Done");

    }
}
