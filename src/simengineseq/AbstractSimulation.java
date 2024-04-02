package simengineseq;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for defining concrete simulations
 *  
 */
public abstract class AbstractSimulation {

	/* environment of the simulation */
	private AbstractEnvironment env;
	
	/* list of the agents */
	private final List<AbstractAgent> agents;
	
	/* simulation listeners */
	private final List<SimulationListener> listeners;

	/* logical time step */
	private int dt;
	
	/* initial logical time */
	private int t0;

	/* in the case of sync with wall-time */
	private boolean toBeInSyncWithWallTime;
	private int nStepsPerSec;
	
	/* for time statistics*/
	private long currentWallTime;
	private long startWallTime;
	private long endWallTime;
	private long averageTimePerStep;


	protected AbstractSimulation() {
        this.agents = new ArrayList<AbstractAgent>();
        this.listeners = new ArrayList<SimulationListener>();
        this.toBeInSyncWithWallTime = false;
	}
	
	/**
	 * 
	 * Method used to configure the simulation, specifying env and agents
	 * 
	 */
	protected abstract void setup();
	
	/**
	 * Method running the simulation for a number of steps,
	 * using a sequential approach
	 * 
	 * @param numSteps
	 */
	public void run(final int numSteps) {

        this.startWallTime = System.currentTimeMillis();

		/* initialize the env and the agents inside */
		int t = this.t0;

        this.env.init();
		for (final var a: this.agents) {
			a.init(this.env);
		}

		this.notifyReset(t, this.agents, this.env);
		
		long timePerStep = 0;
		int nSteps = 0;
		
		while (nSteps < numSteps) {

            this.currentWallTime = System.currentTimeMillis();
		
			/* make a step */

            this.env.step(this.dt);
			for (final var agent: this.agents) {
				agent.step(this.dt);
			}
			t += this.dt;

            this.notifyNewStep(t, this.agents, this.env);

			nSteps++;			
			timePerStep += System.currentTimeMillis() - this.currentWallTime;
			
			if (this.toBeInSyncWithWallTime) {
                this.syncWithWallTime();
			}
		}

        this.endWallTime = System.currentTimeMillis();
		this.averageTimePerStep = timePerStep / numSteps;
		
	}
	
	public long getSimulationDuration() {
		return this.endWallTime - this.startWallTime;
	}
	
	public long getAverageTimePerCycle() {
		return this.averageTimePerStep;
	}
	
	/* methods for configuring the simulation */
	
	protected void setupTimings(final int t0, final int dt) {
		this.dt = dt;
		this.t0 = t0;
	}
	
	protected void syncWithTime(final int nCyclesPerSec) {
		this.toBeInSyncWithWallTime = true;
		this.nStepsPerSec = nCyclesPerSec;
	}
		
	protected void setupEnvironment(final AbstractEnvironment env) {
		this.env = env;
	}

	protected void addAgent(final AbstractAgent agent) {
        this.agents.add(agent);
	}
	
	/* methods for listeners */
	
	public void addSimulationListener(final SimulationListener l) {
		this.listeners.add(l);
	}
	
	private void notifyReset(final int t0, final List<AbstractAgent> agents, final AbstractEnvironment env) {
		for (final var l: this.listeners) {
			l.notifyInit(t0, agents, env);
		}
	}

	private void notifyNewStep(final int t, final List<AbstractAgent> agents, final AbstractEnvironment env) {
		for (final var l: this.listeners) {
			l.notifyStepDone(t, agents, env);
		}
	}

	/* method to sync with wall time at a specified step rate */
	
	private void syncWithWallTime() {
		try {
			final long newWallTime = System.currentTimeMillis();
			final long delay = 1000 / this.nStepsPerSec;
			final long wallTimeDT = newWallTime - this.currentWallTime;
			if (wallTimeDT < delay) {
				Thread.sleep(delay - wallTimeDT);
			}
		} catch (final Exception ex) {}
	}

	// start
	public void start(final int step) {
		this.run(step);
	}

	// stop
	public void stop() {
		// TODO Auto-generated method stub
	}
}
