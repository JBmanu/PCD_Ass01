package car;

import road.AbstractEnvironment;
import simengineseq.*;
import road.Road;
import road.RoadsEnv;

import java.util.Optional;

/**
 * 
 * Base class modeling the skeleton of an agent modeling a car in the traffic environment
 * 
 */
public abstract class CarAgent extends AbstractAgent {
	
	/* car model */
	protected double maxSpeed;		
	protected double currentSpeed;  
	protected double acceleration;
	protected double deceleration;

	/* percept and action retrieved and submitted at each step */
	private int dt;
	protected CarPercept currentPercept;
	protected Optional<Action> selectedAction;
	
	
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
	}

	protected int getDt() {
		return this.dt;
	}

	/**
	 * 
	 * Basic behaviour of a car agent structured into a sense/decide/act structure 
	 * 
	 */
	@Override
	public void step(final int dt) {
		this.dt = dt;

		/* sense */
		final AbstractEnvironment env = this.getEnv();
        this.currentPercept = (CarPercept) env.getCurrentPercepts(this.getId());

		/* decide */
        this.selectedAction = Optional.empty();
        this.decide();
		
		/* act */
		if (this.selectedAction.isPresent()) {
			env.doAction(this.getId(), this.selectedAction.get());
		}
	}

	public void sense() {
		final AbstractEnvironment env = this.getEnv();
		this.currentPercept = (CarPercept) env.getCurrentPercepts(this.getId());
	}
	
	/**
	 * 
	 * Base method to define the behaviour strategy of the car
	 */
	public abstract void decide();

	public void action() {
		if (this.selectedAction.isPresent()) {
			this.getEnv().doAction(this.getId(), this.selectedAction.get());
		}
	}
	
	public double getCurrentSpeed() {
		return this.currentSpeed;
	}
	
	protected void log(final String msg) {
		System.out.println("[CAR " + this.getId() + "] " + msg);
	}

	
}
