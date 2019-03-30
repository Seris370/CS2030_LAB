package cs2030.simulator;

/**
 * Encapsulates a server.
 * the server can have three states
 * mode == false : server is idle
 * mode == true and waited == false : server is serving but no customer is waiting
 * mode == true and waited == true : server is serving and one customer is already waiting
 */
public class Server {
    public boolean mode;
    public double waitingTime = 0;
    public int numOfServed = 0;
    public double nextServeTime;
    public boolean waited;

    public Server(boolean mode) {
        this.mode = mode;
    }

    /**
     * Changes the server's current mode.
     */
    public void change() {
        this.mode = !this.mode;
    }
}
