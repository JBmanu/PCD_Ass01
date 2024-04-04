package simulation.listener;

import car.AbstractAgent;
import road.AbstractEnvironment;
import simulation.AbstractSimulation;

import java.util.List;

public interface ModelSimulationListener {
    /**
     * Called at the beginning of the simulation
     *
     * @param t
     * @param agents
     * @param env
     */
    void notifyInit(int t, List<AbstractAgent> agents, AbstractEnvironment env);

    /**
     * Called at each step, updater all updates
     * @param t
     * @param agents
     * @param env
     */
    void notifyStepDone(final int t,
                        final List<AbstractAgent> agents,
                        final AbstractEnvironment env);

    void notifyEnd(final AbstractSimulation simulation);
}
