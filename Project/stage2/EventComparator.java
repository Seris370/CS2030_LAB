package cs2030.simulator;

import java.util.Comparator;

/**
 * Implements a comparator for Event.
 * first compares events' time
 * if the time of two events are the same, compare the events' customers
 */
public class EventComparator implements Comparator<Event> {
    /**
     * Overrides the compare function of interface Comparator.
     */
    public int compare(Event e1, Event e2) {
        if (e1.getTime() < e2.getTime()) {
            return -1;
        } else if (e1.getTime() > e2.getTime()) {
            return 1;
        } else if (e1.getName() < e2.getName()) {
            return -1;
        } else if (e1.getName() > e2.getName()) {
            return 1;
        } else {
            return 0;
        }
    }
}
