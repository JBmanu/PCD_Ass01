package simulation.listener;

import car.AbstractAgent;
import road.AbstractEnvironment;
import simulation.AbstractSimulation;

import java.util.List;

public interface SimulationListener {

	/**
	 * Called at the beginning of the simulation
	 * 
	 * @param t
	 */
	void notifyInit(int t, final AbstractSimulation simulation);
	
	/**
	 * Called at each step, updater all updates
	 * @param t
	 */
	void notifyStepDone(final int t, final AbstractSimulation simulation);

	void notifyEnd(final AbstractSimulation simulation);
}
