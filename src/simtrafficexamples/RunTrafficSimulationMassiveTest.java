package simtrafficexamples;

public class RunTrafficSimulationMassiveTest {

	public static void main(final String[] args) {

		final int numCars = 5000;
		final int nSteps = 100;
		
		final var simulation = new TrafficSimulationSingleRoadMassiveNumberOfCars(numCars);
		simulation.setup();
		
		log("Running the simulation: " + numCars + " cars, for " + nSteps + " steps ...");

		simulation.play(nSteps);

//		final long d = simulation.getSimulationDuration();
//		log("Completed in " + d + " ms - average time per step: " + simulation.getAverageTimePerCycle() + " ms");
	}
	
	private static void log(final String msg) {
		System.out.println("[ SIMULATION ] " + msg);
	}
}
