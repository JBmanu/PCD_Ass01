package worker.slave;

import car.command.CarCommand;
import monitor.startStop.StartStopMonitor;

public interface Worker {

    void play(CarCommand command);

    void pauseAndWaitUntilPlay();

//    void pause();

    void terminate();

    void addStartStopMonitorInTail(StartStopMonitor starStopMonitorSimulation);
}
