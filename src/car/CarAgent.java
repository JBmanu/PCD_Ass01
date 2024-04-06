package car;

import car.command.InvokerCarCommandImpl;
import car.command.InvokerCommand;
import road.Road;
import road.RoadsEnv;
import simengineseq.Action;

import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Base class modeling the skeleton of an agent modeling a car in the traffic environment
 */
public abstract class CarAgent extends AbstractAgent {
    /* car model */
    protected double maxSpeed;
    protected double currentSpeed;
    protected double acceleration;
    protected double deceleration;

    /* percept and action retrieved and submitted at each step */
    private int timeDt;
    protected CarPercept currentPercept;
    protected Optional<Action> selectedAction;
    private final InvokerCommand invokerCarCommand;

    public CarAgent(final String id, final RoadsEnv env, final Road road,
                    final double initialPos,
                    final double acc,
                    final double dec,
                    final double vmax) {
        super(id);
        this.acceleration = acc;
        this.deceleration = dec;
        this.maxSpeed = vmax;
        env.registerNewCar(this, road, initialPos);
        this.invokerCarCommand = new InvokerCarCommandImpl(this);
    }

    @Override
    public InvokerCommand invokerCommand() {
        return this.invokerCarCommand;
    }

    protected int timeDt() {
        return this.timeDt;
    }

    public Optional<Action> selectedAction() {
        return this.selectedAction;
    }

    public double getCurrentSpeed() {
        return this.currentSpeed;
    }

    public void setTimeDt(final int dt) {
        this.timeDt = dt;
    }

    public void setCurrentPercept(final CarPercept percept) {
        this.currentPercept = percept;
    }

    public void setSelectedAction(final Optional<Action> action) {
        this.selectedAction = action;
    }

    /**
     * Basic behaviour of a car agent structured into a sense/decide/act structure
     */
    @Override
    public void step(final int dt) {
        this.timeDt = dt;
		this.invokerCarCommand.sense();
		this.invokerCarCommand.decide();
		this.invokerCarCommand.action();
    }

    /**
     * Base method to define the behaviour strategy of the car
     */
    public abstract void decide();

    protected void log(final String msg) {
        System.out.println("[CAR " + this.getId() + "] " + msg);
    }


}
