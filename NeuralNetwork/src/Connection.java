public class Connection {

    double weight = 0;
    double deltaWeight = 0; //Change in weight

    public Connection() {

    }

    public void setDeltaWeight(double deltaWeight) {
        this.deltaWeight = deltaWeight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getDeltaWeight() {
        return deltaWeight;
    }

    public double getWeight() {
        return weight;
    }
}
