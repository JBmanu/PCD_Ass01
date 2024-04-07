package simulation;

import car.AbstractAgent;
import inspector.road.RoadSimStatistics;
import inspector.stepper.Stepper;
import inspector.timeStatistics.TimeStatistics;
import monitor.startStop.StartStopMonitor;
import road.AbstractEnvironment;

import java.util.List;

public interface CommandsSimulation {

    public Stepper stepper();

    public StartStopMonitor startStopMonitor();

    public TimeStatistics timeStatistics();

    public RoadSimStatistics roadStatistics();

    public AbstractEnvironment environment();

    public List<AbstractAgent> agents();


}
