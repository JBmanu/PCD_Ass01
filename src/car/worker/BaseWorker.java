package car.worker;

import car.command.InvokerCarCommand;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public abstract class BaseWorker {
    private final BlockingQueue<InvokerCarCommand> invokerCarCommandQueue;

    protected BaseWorker() {
        this.invokerCarCommandQueue = new SynchronousQueue<>();
    }

    public void addInvokerCarInvoker(final InvokerCarCommand invokerCarCommand) {
        this.invokerCarCommandQueue.add(invokerCarCommand);
    }
}
