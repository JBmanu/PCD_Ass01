package worker.slave;

import monitor.startStop.StartStopMonitor;
import monitor.startStop.StartStopMonitorImpl;

public abstract class BaseWorker extends Thread implements StartStopMonitor {
    private boolean isRunning;
    private final StartStopMonitor startStopMonitor;

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
            this.startStopMonitor.waitUntilPlay();
            this.execute();
            this.startStopMonitor.pause();
        }
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
    public void waitUntilPlay() {
        this.startStopMonitor.waitUntilPlay();
    }
    @Override
    public void pauseAndWaitUntilPlay() {
        this.startStopMonitor.pauseAndWaitUntilPlay();
    }

    public void terminate() {
        this.isRunning = false;
    }



}
