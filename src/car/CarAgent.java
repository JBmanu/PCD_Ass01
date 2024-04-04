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
	protected int dt;

	/* percept and action retrieved and submitted at each step */
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

	@Override
	public void setDt(final int dt) {
		this.dt = dt;
	}

	/**
	 * 
	 * Basic behaviour of a car agent structured into a sense/decide/act structure 
	 * 
	 */
	@Override
	public void step() {

		/* sense */

		final AbstractEnvironment env = this.getEnv();
        this.currentPercept = (CarPercept) env.getCurrentPercepts(this.getMyId());

		/* decide */

        this.selectedAction = Optional.empty();

        this.decide(this.dt);
		
		/* act */
		
		if (this.selectedAction.isPresent()) {
			env.doAction(this.getMyId(), this.selectedAction.get());
		}
	}
	
	/**
	 * 
	 * Base method to define the behaviour strategy of the car
	 */
	protected abstract void decide(final int dt);
	
	public double getCurrentSpeed() {
		return this.currentSpeed;
	}
	
	protected void log(final String msg) {
		System.out.println("[CAR " + this.getId() + "] " + msg);
	}

	
}
