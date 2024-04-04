package car;

import road.AbstractEnvironment;

/**
 * Base  class for defining types of agents taking part to the simulation
 */
public abstract class AbstractAgent extends Thread {

    private final String myId;
    private AbstractEnvironment env;

    /**
     * Each agent has an identifier
     *
     * @param id
     */
    protected AbstractAgent(final String id) {
        this.myId = id;
    }

    /**
     * This method is called at the beginning of the simulation
     *
     * @param env
     */
    public void init(final AbstractEnvironment env) {
        this.env = env;
    }

    /**
     * This method is called at each step of the simulation
     */
    abstract protected void step();

    abstract public void setDt(int dt);

    @Override
    public void run() {
        this.step();
    }

    public String getMyId() {
        return this.myId;
    }

    protected AbstractEnvironment getEnv() {
        return this.env;
    }
}
