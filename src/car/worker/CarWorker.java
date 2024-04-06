package car.worker;

import car.command.InvokerCommand;
import monitor.StartStopMonitorImpl;

public interface CarWorker {

    void start();
    StartStopMonitorImpl startStopSimulation();
    void setStartStopInTail(StartStopMonitorImpl startStopMonitor);
    void addInvokerCarInvoker(InvokerCommand invokerCommand);

}
