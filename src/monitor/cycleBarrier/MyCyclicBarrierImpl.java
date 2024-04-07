package monitor.cycleBarrier;

import java.util.concurrent.atomic.AtomicInteger;

import monitor.startStop.StartStopMonitor;

public class MyCyclicBarrierImpl implements MyCyclicBarrier {
    final StartStopMonitor startStopMonitor;
    private final AtomicInteger countWorker;
    private int totalWorker;

    public MyCyclicBarrierImpl(final StartStopMonitor startStopMonitor) {
        this.startStopMonitor = startStopMonitor;
        this.countWorker = new AtomicInteger(0);
    }


    @Override
    public void setup(final int parties) {
        this.totalWorker = parties;
    }

    @Override
    public void awaitThatBroken() {
        System.out.print("INCREMENT ");
        if (this.countWorker.incrementAndGet() == this.totalWorker) {
            this.countWorker.set(0);
            System.out.println();
            System.out.println("RESET BARRIER: play next command");
            this.startStopMonitor.play();
        }
    }
}
