package simulation;

import trafficexamples.TrafficSimulationSingleRoadSeveralCars;

public class SimulationSingleton {

    public static AbstractSimulation simulation = new TrafficSimulationSingleRoadSeveralCars();
    public static RoadSimView simulationView = new RoadSimView();
}
