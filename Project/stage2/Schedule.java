package cs2030.simulator;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Encapsulates a Schedule.
 * maintains a lsit of customers and a queue of events
 * supports deleting and adding events to the queue according to their time
 */
public class Schedule {
    private LinkedList<Customer> customers;
    public PriorityQueue<Event> events;

    public Schedule(LinkedList<Customer> customers, PriorityQueue<Event> events) {
        this.customers = customers;
        this.events = events;
    }

    /**
     * Prints all arrivals of all customers at the beginning of the output.
     */
    public void printArrivals() {
        System.out.println("# Adding arrivals");
        for (Customer customer : this.customers) {
            customer.arrives();
        }
    }

    /**
     * Prints and deletes the head event in the priorityqueue.
     */
    public void printNextEvent() {
        Event event = this.events.poll();
        event.print();
    }

    /**
     * Deletes the head event in the priorityqueue.
     */
    public void deleteEvent() {
        this.events.poll();
    }

    /**
     * Adds an event to the priority queue according to its happening time.
     * @param t time of the event
     * @param n name of the customer
     * @param a action of the customer; ARRIVES = 1; SERVED = 2; LEAVES = 3; DONE = 4; WAITS = 5;
     * @param i ID of the event
     */
    public void addEvent(double t, int n, int a, int i) {
        Event e = new Event(t, n, a, i);
        this.events.add(e);
    }
}
