package simtrafficexamples;

import commands.stepper.Stepper;
import simengineseq.AbstractAgent;
import simengineseq.AbstractEnvironment;
import simengineseq.SimulationListener;
import simtrafficbase.*;

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
	
	@Override
	public void notifyInit(final int t, final List<AbstractAgent> agents, final AbstractEnvironment env) {
		// TODO Auto-generated method stub
		// log("reset: " + t);
        this.averageSpeed = 0;
	}

	@Override
	public void notifyStepDone(final int t, final List<AbstractAgent> agents, final AbstractEnvironment env, Stepper stepper) {
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
	
	public double getAverageSpeed() {
		return this.averageSpeed;
	}

	public double getMinSpeed() {
		return this.minSpeed;
	}
	
	public double getMaxSpeed() {
		return this.maxSpeed;
	}
	
	
	private void log(final String msg) {
		System.out.println("[STAT] " + msg);
	}

}
