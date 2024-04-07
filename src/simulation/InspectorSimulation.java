package simulation;

import car.AbstractAgent;
import inspector.road.RoadSimStatistics;
import inspector.stepper.Stepper;
import inspector.timeStatistics.TimeStatistics;
import synchronizers.monitor.startStop.StartStopMonitor;
import road.AbstractEnvironment;
import synchronizers.worker.master.MasterWorker;

import java.util.List;

public interface InspectorSimulation {

    public Stepper stepper();

    public StartStopMonitor startStopMonitor();

    public TimeStatistics timeStatistics();

    public RoadSimStatistics roadStatistics();

    public AbstractEnvironment environment();

    public List<AbstractAgent> agents();

    void setMasterWorker(final MasterWorker masterWorker);

    void setup();
}
