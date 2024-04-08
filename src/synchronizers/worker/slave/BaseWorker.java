package synchronizers.worker.slave;

import synchronizers.monitor.startStop.StartStopMonitor;
import synchronizers.monitor.startStop.StartStopMonitorImpl;

public abstract class BaseWorker extends Thread implements StartStopMonitor {
    private final StartStopMonitor startStopMonitor;
    private boolean isRunning;

    protected BaseWorker() {
        this.startStopMonitor = new StartStopMonitorImpl();
        this.isRunning = true;
        this.start();
    }

    protected abstract void execute();

    @Override
    public void run() {
        this.pause();
        while (this.isRunning) {
            this.startStopMonitor.awaitUntilPlay();
            this.execute();
            this.startStopMonitor.pause();
        }
        System.out.println("Worker terminated");
    }

    @Override
    public void play() {
        this.startStopMonitor.play();
    }
    @Override
    public void pause() {
        this.startStopMonitor.pause();
    }
    @Override
    public void awaitUntilPlay() {
        this.startStopMonitor.awaitUntilPlay();
    }
    @Override
    public void pauseAndWaitUntilPlay() {
        this.startStopMonitor.pauseAndWaitUntilPlay();
    }

    public void terminate() {
        this.play();
        this.isRunning = false;
    }



}
