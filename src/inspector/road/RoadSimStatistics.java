package inspector.road;

import car.CarAgent;
import inspector.stepper.Stepper;
import inspector.timeStatistics.TimeStatistics;
import car.AbstractAgent;
import road.AbstractEnvironment;
import simulation.SimulationListener;

import java.util.List;

/**
 * Simple class keeping track of some statistics about a traffic simulation
 * - average number of cars
 * - min speed
 * - max speed
 */
public class RoadSimStatistics implements SimulationListener {

	private double averageSpeed;
	private double minSpeed;
	private double maxSpeed;
	
	public RoadSimStatistics() {
	}


	public double averageSpeed() {
		return this.averageSpeed;
	}

	public double minSpeed() {
		return this.minSpeed;
	}

	public double maxSpeed() {
		return this.maxSpeed;
	}
	
	@Override
	public void notifyInit(final int t, final List<AbstractAgent> agents, final AbstractEnvironment env) {
		// TODO Auto-generated method stub
		// log("reset: " + t);
        this.averageSpeed = 0;
	}

	@Override
	public void notifyStepDone(final int t, final List<AbstractAgent> agents, final AbstractEnvironment env, Stepper stepper, final TimeStatistics timeStatistics) {
		double avSpeed = 0;

        this.maxSpeed = -1;
        this.minSpeed = Double.MAX_VALUE;
		for (final var agent: agents) {
			final CarAgent car = (CarAgent) agent;
			final double currSpeed = car.getCurrentSpeed();
			avSpeed += currSpeed;			
			if (currSpeed > this.maxSpeed) {
                this.maxSpeed = currSpeed;
			} else if (currSpeed < this.minSpeed) {
                this.minSpeed = currSpeed;
			}
		}
		
		if (agents.size() > 0) {
			avSpeed /= agents.size();
		}
        this.log("average speed: " + avSpeed);
	}

	@Override
	public void notifyEnd(Stepper stepper, TimeStatistics timeStatistics) {

	}

	private void log(final String msg) {
		System.out.println("[STAT] " + msg);
	}

}
