public class Trainer {

    float[] inputs;
    int answer;

    public Trainer(float input1, float input2, int a) {
        inputs = new float[3];
        inputs[0] = input1;
        inputs[1] = input2;
        inputs[2] = 1;
        answer = a;

    }
}
