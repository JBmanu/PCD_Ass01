package simengineseq;

import commands.Stepper;
import commands.TimeStatistics;

import java.util.List;

public interface SimulationListener {

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
						final AbstractEnvironment env,
						final Stepper stepper,
						final TimeStatistics timeStatistics);
}
