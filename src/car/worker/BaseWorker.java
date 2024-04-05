package car.worker;

import car.command.InvokerCarCommand;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.SynchronousQueue;

public abstract class BaseWorker {
    private final BlockingQueue<InvokerCarCommand> invokerCarCommandQueue;
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

    protected InvokerCarCommand takeInvokerCarCommand() {
        try {
            return this.invokerCarCommandQueue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void addInvokerCarInvoker(final InvokerCarCommand invokerCarCommand) {
        this.invokerCarCommandQueue.add(invokerCarCommand);
    }

}
