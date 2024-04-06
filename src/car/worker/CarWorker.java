package car.worker;

import car.command.InvokerCommand;
import monitor.StartStopMonitor;
import monitor.StartStopMonitorImpl;

public interface CarWorker {

    void start();
    StartStopMonitorImpl startStopSimulation();
    void setStartStopInTail(StartStopMonitor startStopMonitor);
    void addInvokerCarInvoker(InvokerCommand invokerCommand);

}
