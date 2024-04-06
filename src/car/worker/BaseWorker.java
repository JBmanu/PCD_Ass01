package car.worker;

import car.command.InvokerCommand;
import monitor.StartStopMonitorImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class BaseWorker extends Thread implements CarWorker {
    private final CyclicBarrier barrier;
    private final List<InvokerCommand> invokerCarCommandQueue;
    private final StartStopMonitorImpl startStopMonitor;
    private StartStopMonitorImpl startStopMonitorInTail;

    protected BaseWorker(final CyclicBarrier barrier) {
        this.invokerCarCommandQueue = new ArrayList<>();
        this.startStopMonitor = new StartStopMonitorImpl();
        this.barrier = barrier;
    }

    protected CyclicBarrier barrier() {
        return this.barrier;
    }

    protected LinkedBlockingQueue<InvokerCommand> invokerCarCommandIterator() {
        return new LinkedBlockingQueue<>(this.invokerCarCommandQueue);
    }

    protected void runLastWorker() {
        this.startStopMonitorInTail.play();
    }

    @Override
    public StartStopMonitorImpl startStopSimulation() {
        return this.startStopMonitor;
    }
    @Override
    public void addInvokerCarInvoker(final InvokerCommand invokerCommand) {
        this.invokerCarCommandQueue.add(invokerCommand);
    }
    @Override
    public void setStartStopInTail(final StartStopMonitorImpl startStopMonitor) {
        this.startStopMonitorInTail = startStopMonitor;
    }


}
