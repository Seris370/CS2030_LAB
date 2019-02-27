import java.util.*;

class Server {
  // mode is false when the server is free and true if the server is working
  public boolean mode;
  public double waitingTime = 0;
  public int numOfServed = 0;
  public double nextServeTime;

  // waited is true if there is a wating customer and false if there is no waiting customer
  public boolean waited;

  public Server(boolean mode) {
    this.mode = mode;
  }

  public void change() {
    this.mode = !this.mode;
  }
}

class Event implements Comparable<Event> {
  private double time;
  private int name;
  public int action;

  // constructor
  public Event(double time, int name, int action) {
    this.time = time;
    this.name = name;
    this.action = action;
  }

  // getters
  public double getTime() {
    return this.time;
  }
  public int getName() {
    return this.name;
  }

  public void print() {
    if (action == 1) {
      System.out.printf("%.3f %d arrives\n", this.time, this.name);
    } else if (action == 2) {
      System.out.printf("%.3f %d served\n", this.time, this.name);
    } else if (action == 3){
      System.out.printf("%.3f %d leaves\n", this.time, this.name);
    } else if (action == 4) {
      System.out.printf("%.3f %d done\n", this.time, this.name);
    } else {
      System.out.printf("%.3f %d waits\n", this.time, this.name);
    }
  }

  @Override
  public int compareTo(Event e2) {
    if (this.time < e2.getTime()) {
      return -1;
    } else if (this.time > e2.getTime()) {
      return 1;
    } else if (this.name < e2.getName()) {
      return -1;
    } else if (this.name > e2.getName()) {
      return 1;
    } else {
      return 0;
    }
  }
}


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

  // getter of name
  public int getName() {
    return this.name;
  }

  // this customer arrives
  public void arrives() {
    System.out.printf("%.3f %d arrives\n", this.arrivalTime, this.name);
  }

  // this customer is served
  public void served() {
    System.out.printf("%.3f %d served\n", this.arrivalTime, this.name);
  }

  // this customer leaves
  public void leaves() {
    System.out.printf("%.3f %d leaves\n", this.arrivalTime, this.name);
  }
}


class Schedule {
  private LinkedList<Customer> customers;
  public PriorityQueue<Event> events;

  // constructor
  public Schedule(LinkedList<Customer> customers, PriorityQueue<Event> events) {
    this.customers = customers;
    this.events = events;
  }

  // adding arrivals
  public void printArrivals() {
    System.out.println("# Adding arrivals");
    for (Customer customer : this.customers) {
      customer.arrives();
    }
  }

  // print getting next event
  public void printNextEvent() {
    Event event = this.events.poll();
    event.print();
  }

  // removes the first event
  public void deleteEvent() {
    this.events.poll();
  }

  // add an event
  public void addEvent(double t, int n, int a) {
    Event e = new Event(t, n, a);
    this.events.add(e);
  }
}


class Main {
  public static final int ARRIVES = 1;
  public static final int SERVED = 2;
  public static final int LEAVES = 3;
  public static final int DONE = 4;
  public static final int WAITS = 5;

  public static void main(String[] args) {
    // create a scanner
    Scanner sc = new Scanner(System.in);

    // create a server
    Server server = new Server(false);

    // previousArrivalTime keeps track of the former newCustomer's arrival time in the while loop
    double previousArrivalTime = 0;

    // create a list of customers
    LinkedList<Customer> customers = new LinkedList<>();
    int numOfCustomers = 0;
    while (sc.hasNext()) {
      numOfCustomers++;
      double t = sc.nextDouble();
      Customer newCustomer = new Customer(t, numOfCustomers);
      customers.add(newCustomer);
    }

    // create a list of arrival events
    PriorityQueue<Event> events = new PriorityQueue<>();
    for (Customer customer : customers) {
      events.add(new Event(customer.getArrivalTime(), customer.getName(), 1));
    }

    // create a schedule
    Schedule schedule = new Schedule(customers, events);

    // next events
    while (!schedule.events.isEmpty()) {
      Event newEvent = schedule.events.peek();
      if (newEvent.action == 1) { // a customer arrives
        if (server.mode && server.waited) { // server is working and waited
          schedule.addEvent(newEvent.getTime(), newEvent.getName(), 3);
        } else if (server.mode) { // server is working but not waited
          schedule.addEvent(newEvent.getTime(), newEvent.getName(), 5);
        } else { // server is free
          schedule.addEvent(newEvent.getTime(), newEvent.getName(), 2);
        }
        schedule.printNextEvent();
      } else if (newEvent.action == 2) { // a custmer is served
        server.numOfServed++;
        server.nextServeTime = newEvent.getTime() + 1;
        schedule.addEvent(newEvent.getTime() + 1, newEvent.getName(), 4);
        server.change();
        schedule.printNextEvent();
        server.waited = false;
      } else if (newEvent.action == 3) { // a customer leaves
        schedule.printNextEvent();
      } else if (newEvent.action == 4) { // a service is done
        schedule.printNextEvent();
        server.change();
      } else { // a customer waits
        server.waited = true;
        schedule.addEvent(server.nextServeTime, newEvent.getName(), 2);
        server.waitingTime = server.waitingTime - newEvent.getTime() + server.nextServeTime;
        schedule.printNextEvent();
      }
    }

    // print the number of customers
    System.out.printf("[%.3f %d %d]\n", server.waitingTime / server.numOfServed, server.numOfServed, numOfCustomers - server.numOfServed);
  }
}
