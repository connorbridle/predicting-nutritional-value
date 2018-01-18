import java.util.Random;


public class Perceptron {
    //Holds the weights for each of the inputs
    float[] weights;
    float learningRate = 0.01F;

    //Constructor
    public Perceptron(int n) {
        weights = new float[n];
        float min = -1; float max = 1;
        Random rand = new Random();
        for (int i = 0; i < weights.length; i++) {
            weights[i] = rand.nextFloat() * (max-min) + min;
        }
    }

    public float feedForwardInputs(float[] inputs) {
        float weightedSum = 0;
        for (int i = 0; i < weights.length; i++) {
            weightedSum += inputs[i]*weights[i];
        }
        return sigmoidActivation(weightedSum);
    }

    //Sigmoid activation function
    public float sigmoidActivation(float sum) {
        float activation = (int)Math.tanh(sum);
        return activation;
    }

    //ReLU activation function (Fixes the dying neuron problem
    public float reLUActivation(float sum) {
        float activation = 0;
        if (sum >= 0)
            activation = sum;
        else if (sum < 0)
            activation = 0;
        return activation;
    }

    //Leaky ReLU activation function
    public float leakyReLUActivation(float sum) {
        float activation = 0;
        if (sum >= 0)
            activation = sum;
        else if (sum < 0)
            activation = 0.01F*sum;

        return activation;
    }

    //Method that trains the network to produce the correct answer
    public void train (float[] inputs, int desired) {
        float guess = feedForwardInputs(inputs); //Guess according to those inputs
        float error = desired - guess; //Compute the error
        for (int i = 0; i < weights.length; i++) {
            weights[i] += learningRate * error * inputs[i]; //Adjusting weights according to the error and constraint
        }
    }

    public static void main(String[] args) {
        Perceptron myp = new Perceptron(2);
        float[] inputtedValues = {12, 42};
        float result = myp.feedForwardInputs(inputtedValues);
        System.out.println(result);

    }
}
