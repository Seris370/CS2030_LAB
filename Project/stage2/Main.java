import java.util.LinkedList;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import cs2030.simulator.Customer;
import cs2030.simulator.Event;
import cs2030.simulator.Schedule;
import cs2030.simulator.Server;
import cs2030.simulator.EventComparator;
import cs2030.simulator.RandomCustomers;

public class Main {
    public static final int ARRIVES = 1;
    public static final int SERVED = 2;
    public static final int LEAVES = 3;
    public static final int DONE = 4;
    public static final int WAITS = 5;

    /**
     * checks if the server is idle.
     * @param servers a comprehensive list of servers, each in their current state
     * @return the id of the first idle server if there are idle servers, otherwise 0
     */
    static int idle(ArrayList<Server> servers) {
        int id = 1;
        int result = 0;
        for (Server server : servers) {
            if (!server.mode) {
                result = id;
                break;
            }
            id++;
        }
        return result;
    }

    /**
     * checks if the server can accept one waiting customer.
     * @param servers a comprehensive list of servers, each in their current state
     * @return the id of the first waitable server if there are waitable servers, otherwise 0
     */
    static int waitable(ArrayList<Server> servers) {
        int id = 1;
        int result = 0;
        for (Server server : servers) {
            if (!server.waited) {
                result = id;
                break;
            }
            id++;
        }
        return result;
    }

    /**
     * the most important part is the while loop when processing current events.
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int seed = sc.nextInt();
        int numOfServers = sc.nextInt();
        int numOfCustomers = sc.nextInt();
        double lambda = sc.nextDouble();
        double mu = sc.nextDouble();

        RandomCustomers rc = new RandomCustomers(numOfCustomers, seed, lambda, mu);

        ArrayList<Server> servers = new ArrayList<>();
        for (int i = 0; i < numOfServers; i++) {
            Server newServer = new Server(false);
            servers.add(newServer);
        }

        LinkedList<Customer> customers = rc.customers;
        double[] serviceTime = rc.serviceTime;

        PriorityQueue<Event> events = new PriorityQueue<Event>(new EventComparator());
        for (Customer customer : customers) {
            events.add(new Event(customer.getArrivalTime(), customer.getName(), 1, 0));
        }

        Schedule schedule = new Schedule(customers, events);
        int countServiceTime = 0;

        while (!schedule.events.isEmpty()) {
            Event newEvent = schedule.events.peek();
            if (newEvent.action == 1) { // a customer arrives
                if (idle(servers) == 0) { // all servers are working
                    if (waitable(servers) == 0) { // all servers are waited
                        schedule.addEvent(newEvent.getTime(), newEvent.getName(), 3, 0);
                    } else { // waits at the first waitable server
                        schedule.addEvent(
                            newEvent.getTime(), newEvent.getName(), 5, waitable(servers));
                    }
                } else { // find the first free server
                    schedule.addEvent(newEvent.getTime(), newEvent.getName(), 2, idle(servers));
                }
                schedule.printNextEvent();
            } else if (newEvent.action == 2) { // a custmer is served
                Server server = servers.get(newEvent.id - 1);
                server.numOfServed++;
                double nextServe = newEvent.getTime() + serviceTime[countServiceTime++];
                server.nextServeTime = nextServe;
                schedule.addEvent(nextServe, newEvent.getName(), 4, newEvent.id);
                server.change();
                schedule.printNextEvent();
                server.waited = false;
            } else if (newEvent.action == 3) { // a customer leaves
                schedule.printNextEvent();
            } else if (newEvent.action == 4) { // a service is done
                Server server = servers.get(newEvent.id - 1);
                schedule.printNextEvent();
                server.change();
            } else { // a customer waits
                Server server = servers.get(newEvent.id - 1);
                server.waited = true;
                schedule.addEvent(server.nextServeTime, newEvent.getName(), 2, newEvent.id);
                server.waitingTime = server.waitingTime - newEvent.getTime() + server.nextServeTime;
                schedule.printNextEvent();
            }
        }

        double sumOfWatingTime = 0;
        for (Server server : servers) {
            sumOfWatingTime += server.waitingTime;
        }
        int sumOfServed = 0;
        for (Server server : servers) {
            sumOfServed += server.numOfServed;
        }
        double averageWaitingTime = sumOfWatingTime / sumOfServed;

        System.out.printf("[%.3f %d %d]\n",
            averageWaitingTime, sumOfServed, numOfCustomers - sumOfServed);
    }
}
