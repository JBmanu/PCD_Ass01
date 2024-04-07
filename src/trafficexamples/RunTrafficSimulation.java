package trafficexamples;

import simulation.RoadSimView;

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
		 var simulation = new TrafficSimulationWithCrossRoads();
		simulation.setup();

		final RoadSimView view = new RoadSimView();

		simulation.addViewListener(view);
		view.setupCommandsSimulation(simulation);
	}
}
