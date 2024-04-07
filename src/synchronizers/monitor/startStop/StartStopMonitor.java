package synchronizers.monitor.startStop;

public interface StartStopMonitor {
    void play();

    void pause();

    void waitUntilPlay();

    void pauseAndWaitUntilPlay();
}
