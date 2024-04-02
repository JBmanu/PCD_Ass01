package simtrafficexamples;

import view.RoadSimView;

/**
 * 
 * Main class to create and run a simulation
 * 
 */
public class RunTrafficSimulation {

	public static void main(final String[] args) {

//		final var simulation = new TrafficSimulationSingleRoadTwoCars();
		 var simulation = new TrafficSimulationSingleRoadSeveralCars();
		// var simulation = new TrafficSimulationSingleRoadWithTrafficLightTwoCars();
		// var simulation = new TrafficSimulationWithCrossRoads();
		simulation.setup();
		
		final RoadSimStatistics stat = new RoadSimStatistics();
		final RoadSimView view = new RoadSimView();
		view.display();
		
		simulation.addSimulationListener(stat);
		simulation.addSimulationListener(view);		
		simulation.run(10000);
	}
}
