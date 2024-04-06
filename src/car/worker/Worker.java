package car.worker;

import car.command.CarCommand;
import monitor.StartStopMonitor;

public interface Worker {

    void play(CarCommand command);

    void pauseAndWaitUntilPlay();

//    void pause();

    void terminate();

    void addStartStopMonitorInTail(StartStopMonitor starStopMonitorSimulation);
}
