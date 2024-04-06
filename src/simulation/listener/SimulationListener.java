package simulation.listener;

import simulation.CommandsSimulation;

public interface SimulationListener {

	/**
	 * Called at the beginning of the simulation
	 * 
	 * @param t
	 */
	void notifyInit(int t, final CommandsSimulation simulation);
	
	/**
	 * Called at each step, updater all updates
	 * @param t
	 */
	void notifyStepDone(final int t, final CommandsSimulation simulation);

	void notifyEnd(final CommandsSimulation simulation);
}
