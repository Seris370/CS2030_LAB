import java.util.*;

class Customer {
  private double arrivalTime;

  public Customer(double arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public double getArrivalTime() {
    return this.arrivalTime;
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

    // check if there is a next customer
    while (sc.hasNext()) {
      numOfCustomers ++;
      Customer newCustomer = new Customer(sc.nextDouble());
      customers.add(newCustomer);
      System.out.printf(numOfCustomers + " arrives at %.3f\n", newCustomer.getArrivalTime());
    }

    // print the number of customers
    System.out.println("Number of customers: " + numOfCustomers);
  }
}
