package car.worker;

import car.command.InvokerCommand;
import inspector.startStop.StartStopMonitor;
import inspector.startStop.StartStopSimulationRunnable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class BaseWorker extends Thread implements CarWorker {
    private final CyclicBarrier barrier;
    private final List<InvokerCommand> invokerCarCommandQueue;
    private final StartStopMonitor startStopMonitor;
    private StartStopMonitor startStopMonitorInTail;

    protected BaseWorker(final CyclicBarrier barrier) {
        this.invokerCarCommandQueue = new ArrayList<>();
        this.startStopMonitor = new StartStopMonitor();
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
    public StartStopMonitor startStopSimulation() {
        return this.startStopMonitor;
    }
    @Override
    public void addInvokerCarInvoker(final InvokerCommand invokerCommand) {
        this.invokerCarCommandQueue.add(invokerCommand);
    }
    @Override
    public void setStartStopInTail(final StartStopMonitor startStopMonitor) {
        this.startStopMonitorInTail = startStopMonitor;
    }


}
