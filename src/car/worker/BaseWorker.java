package car.worker;

import car.command.InvokerCommand;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.SynchronousQueue;

public abstract class BaseWorker {
    private final BlockingQueue<InvokerCommand> invokerCarCommandQueue;
    private final CyclicBarrier barrier;

    protected BaseWorker(final CyclicBarrier barrier) {
        this.invokerCarCommandQueue = new SynchronousQueue<>();
        this.barrier = barrier;
    }

    protected CyclicBarrier barrier() {
        return this.barrier;
    }

    protected boolean isEmptyInvokerCarCommandQueue() {
        return this.invokerCarCommandQueue.isEmpty();
    }

    protected InvokerCommand takeInvokerCarCommand() {
        try {
            return this.invokerCarCommandQueue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void addInvokerCarInvoker(final InvokerCommand invokerCarCommand) {
        this.invokerCarCommandQueue.add(invokerCarCommand);
    }

}
