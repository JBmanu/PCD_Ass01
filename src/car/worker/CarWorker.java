package car.worker;

import car.command.InvokerCommand;
import inspector.startStop.StartStopMonitor;

public interface CarWorker {

    void start();
    StartStopMonitor startStopSimulation();
    void setStartStopInTail(StartStopMonitor startStopMonitor);
    void addInvokerCarInvoker(InvokerCommand invokerCommand);

}
