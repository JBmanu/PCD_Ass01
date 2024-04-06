package monitor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StartStopMonitorImpl implements StartStopMonitor {
    private final Lock mutex;
    private final Condition conditionRunning;
    private boolean isRunning;

    public StartStopMonitorImpl() {
        this.mutex = new ReentrantLock();
        this.conditionRunning = this.mutex.newCondition();
    }

    @Override
    public void waitUntilPlay() {
        try {
            this.mutex.lock();
            while (!this.isRunning) {
                try {
//                    System.out.println("Waiting for running");
                    this.conditionRunning.await();
                } catch (InterruptedException ex) {
                    System.out.println("Interrupted while waiting for running");
                }
            }
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public void play() {
        try {
            this.mutex.lock();
            this.isRunning = true;
            this.conditionRunning.signal();
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public void pause() {
        try {
            this.mutex.lock();
            this.isRunning = false;
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public void pauseAndWaitUntilPlay() {
        try {
            this.mutex.lock();
            this.isRunning = false;
            while (!this.isRunning) {
                try {
                    this.conditionRunning.await();
                } catch (InterruptedException ex) {
                    System.out.println("Interrupted while waiting for running");
                }
            }
        } finally {
            this.mutex.unlock();
        }
    }

}
