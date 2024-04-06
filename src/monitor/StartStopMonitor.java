package monitor;

public interface StartStopMonitor {
    void play();

    void pause();

    void waitUntilPlay();

    void pauseAndWaitUntilPlay();
}
