package simulation;

import car.AbstractAgent;
import inspector.startStop.StartStopMonitor;
import inspector.stepper.Stepper;
import inspector.timeStatistics.TimeStatistics;
import road.AbstractEnvironment;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for defining concrete simulations
 */
public abstract class AbstractSimulation extends Thread {

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

    private final StartStopMonitor startStopMonitor;
    private final TimeStatistics timeStatistics;
    private final Stepper stepper;

    protected AbstractSimulation() {
        this.agents = new ArrayList<AbstractAgent>();
        this.listeners = new ArrayList<SimulationListener>();
        this.toBeInSyncWithWallTime = false;
        this.startStopMonitor = new StartStopMonitor();
        this.timeStatistics = new TimeStatistics();
        this.stepper = new Stepper();
        this.start();
    }

    /**
     * Method used to configure the simulation, specifying env and agents
     */
    protected abstract void setup();


    public void play(final int nStep) {
        this.stepper.setTotalStep(nStep);
        this.startStopMonitor.play();
    }

    public void pause() {
        this.startStopMonitor.pause();
    }

    /**
     * Method running the simulation for a number of steps,
     * using a sequential approach
     */
    @Override
    public void run() {
        this.startStopMonitor.waitUntilRunning();
        this.timeStatistics.setStartWallTime();

        /* initialize the env and the agents inside */
        int t = this.t0;

        this.env.init();
        for (final var a : this.agents) {
            a.init(this.env);
        }

        this.notifyReset(t, this.agents, this.env);

        long timePerStep = 0;

        while (this.stepper.hasMoreSteps()) {
            this.startStopMonitor.waitUntilRunning();
            this.timeStatistics.setCurrentWallTime(System.currentTimeMillis());

            /* make a step */
            this.env.step(this.dt);
            for (final var agent : this.agents) {
                agent.step(this.dt);
            }
            t += this.dt;

            this.notifyNewStep(t, this.agents, this.env);

            this.stepper.increaseStep();
            timePerStep += System.currentTimeMillis() - this.timeStatistics.currentWallTime();

            if (this.toBeInSyncWithWallTime) {
                this.syncWithWallTime();
            }
        }

        this.timeStatistics.setEndWallTime(System.currentTimeMillis());
        this.timeStatistics.setAverageTimeForStep((double)timePerStep / this.stepper.totalStep());

        this.notifyEnd();
    }

    public long getSimulationDuration() {
        return this.timeStatistics.totalWallTime();
    }

    public double getAverageTimePerCycle() {
        return this.timeStatistics.averageTimeForStep();
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
        for (final var l : this.listeners) {
            l.notifyInit(t0, agents, env);
        }
    }

    private void notifyNewStep(final int t, final List<AbstractAgent> agents, final AbstractEnvironment env) {
        for (final var l : this.listeners) {
            l.notifyStepDone(t, agents, env, this.stepper, this.timeStatistics);
        }
    }

    private void notifyEnd() {
        for (final var l : this.listeners) {
            l.notifyEnd(this.stepper, this.timeStatistics);
        }
    }

    /* method to sync with wall time at a specified step rate */

    private void syncWithWallTime() {
        try {
            final long newWallTime = System.currentTimeMillis();
            final long delay = 1000 / this.nStepsPerSec;
            final long wallTimeDT = newWallTime - this.timeStatistics.currentWallTime();
            if (wallTimeDT < delay) {
                Thread.sleep(delay - wallTimeDT);
            }
        } catch (final Exception ex) {
        }
    }


}
