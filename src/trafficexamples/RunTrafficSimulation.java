package trafficexamples;

import simulation.SimulationView;
import simulation.SimulationSingleton;

/**
 * 
 * Main class to create and run a simulation
 * 
 */
public class RunTrafficSimulation {

	public static void main(final String[] args) {

//		final var simulation = new TrafficSimulationSingleRoadTwoCars();
//		 var simulation = new TrafficSimulationSingleRoadSeveralCars();
//		 var simulation = new TrafficSimulationSingleRoadWithTrafficLightTwoCars();
//		 var simulation = new TrafficSimulationWithCrossRoads();

		final SimulationView view = SimulationSingleton.simulationView;

		SimulationSingleton.simulation.addViewListener(view);
		view.setupCommandsSimulation(SimulationSingleton.simulation);
	}
}
