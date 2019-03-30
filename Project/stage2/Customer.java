package cs2030.simulator;

/**
 * Encapsulates a Customer that has an initial arrival time and a name.
 */
public class Customer {

    private double arrivalTime;
    private int name;

    public Customer(double arrivalTime, int name) {
        this.arrivalTime = arrivalTime;
        this.name = name;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    public int getName() {
        return this.name;
    }

    public void arrives() {
        System.out.printf("%.3f %d arrives\n", this.arrivalTime, this.name);
    }

    public void served() {
        System.out.printf("%.3f %d served\n", this.arrivalTime, this.name);
    }

    public void leaves() {
        System.out.printf("%.3f %d leaves\n", this.arrivalTime, this.name);
    }
}
