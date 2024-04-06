package simulation;

import car.AbstractAgent;
import car.barrier.CarBarrier3Worker;
import car.barrier.AgentBarrierLogic;
import inspector.road.RoadSimStatistics;
import monitor.StartStopMonitorImpl;
import inspector.stepper.Stepper;
import inspector.timeStatistics.TimeStatistics;
import road.AbstractEnvironment;
import simulation.listener.ModelSimulationListener;
import simulation.listener.ViewSimulationListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for defining concrete simulations
 */
public abstract class AbstractSimulation extends Thread implements CommandsSimulation {

    /* environment of the simulation */
    private AbstractEnvironment env;

    /* list of the agents */
    private final List<AbstractAgent> agents;

    /* logical time step */
    private int dt;

    /* initial logical time */
    private int t0;

    /* in the case of sync with wall-time */
    private boolean toBeInSyncWithWallTime;
    private int nStepsPerSec;

    /* simulation listeners */
    private final List<ModelSimulationListener> modelListeners;
    private final List<ViewSimulationListener> viewListeners;

    // CarBarrier
    private final AgentBarrierLogic agentBarrierLogic;

    // Model
    private final RoadSimStatistics roadStatistics;
    private final StartStopMonitorImpl startStopMonitor;
    private final TimeStatistics timeStatistics;
    private final Stepper stepper;


    protected AbstractSimulation() {
        this.agents = new ArrayList<>();
        this.modelListeners = new ArrayList<>();
        this.viewListeners = new ArrayList<>();

        this.startStopMonitor = new StartStopMonitorImpl();
        this.roadStatistics = new RoadSimStatistics();
        this.timeStatistics = new TimeStatistics();
        this.stepper = new Stepper();

        this.agentBarrierLogic = new CarBarrier3Worker(this);

        this.toBeInSyncWithWallTime = false;
        this.setupModelListener();
        this.start();
    }

    /**
     * Method used to configure the simulation, specifying env and agents
     */
    protected abstract void setup();

    private void setupModelListener() {
        this.addModelListener(this.roadStatistics);
    }

    @Override
    public AbstractEnvironment environment() {
        return this.env;
    }
    @Override
    public List<AbstractAgent> agents() {
        return this.agents;
    }
    @Override
    public Stepper stepper() {
        return this.stepper;
    }
    @Override
    public StartStopMonitorImpl startStopMonitor() {
        return this.startStopMonitor;
    }
    @Override
    public TimeStatistics timeStatistics() {
        return this.timeStatistics;
    }
    @Override
    public RoadSimStatistics roadStatistics() {
        return this.roadStatistics;
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

        this.notifyReset(t);

        long timePerStep = 0;

        while (this.stepper.hasMoreSteps()) {
            this.startStopMonitor.waitUntilRunning();
            this.timeStatistics.setCurrentWallTime(System.currentTimeMillis());

            /* make a step */
            this.env.step(this.dt);
            this.agentBarrierLogic.execute(this.dt);

            this.agents.forEach(agent -> agent.step(this.dt));
            this.agentBarrierLogic.execute(this.dt);
            this.startStopMonitor.pause();
            this.startStopMonitor.waitUntilRunning();

            t += this.dt;

            this.notifyStepDone(t);

            this.stepper.increaseStep();
            timePerStep += System.currentTimeMillis() - this.timeStatistics.currentWallTime();

            if (this.toBeInSyncWithWallTime) {
                this.syncWithWallTime();
            }
        }

        this.timeStatistics.setEndWallTime(System.currentTimeMillis());
        this.timeStatistics.setAverageTimeForStep((double) timePerStep / this.stepper.totalStep());

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
        this.agentBarrierLogic.addInvokerCommand(agent.invokerCommand());
    }

    // listener
    // adders
    public void addModelListener(final ModelSimulationListener listener) {
        this.modelListeners.add(listener);
    }
    public void addViewListener(final ViewSimulationListener listener) {
        this.viewListeners.add(listener);
    }
    // actions
    private void notifyReset(final int t0) {
        // Model
        for (final var listener : this.modelListeners) {
            listener.notifyInit(t0, this);
        }
        // View
        for (final var listener : this.viewListeners) {
            listener.notifyInit(t0, this);
        }
    }
    private void notifyStepDone(final int t) {
        // Model
        for (final var listener : this.modelListeners) {
            listener.notifyStepDone(t, this);
        }
        // View
        for (final var listener : this.viewListeners) {
            listener.notifyStepDone(t, this);
        }
    }
    private void notifyEnd() {
        // Model
        for (final var listener : this.modelListeners) {
            listener.notifyEnd(this);
        }
        // View
        for (final var listener : this.viewListeners) {
            listener.notifyEnd(this);
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
