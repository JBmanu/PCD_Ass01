package car.worker;

import car.command.InvokerCommand;

public interface CarWorker extends Runnable {

    void addInvokerCarInvoker(InvokerCommand invokerCarCommand);
}
