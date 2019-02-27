import java.util.*;

class Customer {
  // arrival time and number of this customer
  private double arrivalTime;
  private int name;

  // constructor
  public Customer(double arrivalTime, int name) {
    this.arrivalTime = arrivalTime;
    this.name = name;
  }

  // getter of arrivaltime
  public double getArrivalTime() {
    return this.arrivalTime;
  }

  // this customer arrives
  public void arrives() {
    System.out.printf(name + " arrives at %.3f\n", this.arrivalTime);
  }

  // this customer is served
  public void served() {
    System.out.printf("Customer served; next service @ %.3f\n", this.arrivalTime + 1);
  }

  // this customer leaves
  public void leaves() {
    System.out.println("Customer leaves");
  }
}


class Main {
  public static void main(String[] args) {
    // create a scanner
    Scanner sc = new Scanner(System.in);

    // create a linkedlist of customers
    LinkedList<Customer> customers = new LinkedList<>();

    // numOfCustomers keep track of the number of customers
    int numOfCustomers = 0;

    // previousArrivalTime keeps track of the former newCustomer's arrival time in the while loop
    double previousArrivalTime = 0;

    // check if there is a next customer
    while (sc.hasNext()) {
      // create a new Customer
      numOfCustomers++;
      double thisArrivalTime = sc.nextDouble();
      Customer newCustomer = new Customer(thisArrivalTime, numOfCustomers);
      customers.add(newCustomer);

      // actions of this new customer
      newCustomer.arrives();
      if (previousArrivalTime == 0) { // newCustomer is the first customer
        newCustomer.served();
        previousArrivalTime = thisArrivalTime;
      } else  if (thisArrivalTime - previousArrivalTime < 1) { // newCustomer leaves
        newCustomer.leaves();
      } else { //newCustomer is served
        newCustomer.served();
        previousArrivalTime = thisArrivalTime;
      }
    }

    // print the number of customers
    System.out.println("Number of customers: " + numOfCustomers);
  }
}
