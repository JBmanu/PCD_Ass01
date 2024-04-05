package car.worker;

import car.command.InvokerCarCommand;

public interface CarWorker extends Runnable {

    void addInvokerCarInvoker(InvokerCarCommand invokerCarCommand);
}
