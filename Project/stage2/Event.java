package cs2030.simulator;

import cs2030.simulator.EventComparator;

/**
 * Encapsulates an Event.
 * events can be compared, 'larger' events have later time
 * supports printing of an event
 */
public class Event /*implements Comparable<Event>*/ {
    private double time;
    private int name;
    public int action;
    public int id;

    /**
     * Constructs class Event.
     * @param time time of event
     * @param name name of customer
     * @param action action of customer
     * @param id id of event
     */
    public Event(double time, int name, int action, int id) {
        this.time = time;
        this.name = name;
        this.action = action;
        this.id = id;
    }

    public double getTime() {
        return this.time;
    }

    public int getName() {
        return this.name;
    }

    /**
     * prints the event in the format of "time name action".
     */
    public void print() {
        if (action == 1) {
            System.out.printf("%.3f %d arrives\n", this.time, this.name);
        } else if (action == 2) {
            System.out.printf("%.3f %d served by %d\n", this.time, this.name, this.id);
        } else if (action == 3) {
            System.out.printf("%.3f %d leaves\n", this.time, this.name);
        } else if (action == 4) {
            System.out.printf("%.3f %d done serving by %d\n", this.time, this.name, this.id);
        } else {
            System.out.printf("%.3f %d waits to be served by %d\n", this.time, this.name, this.id);
        }
    }
}
