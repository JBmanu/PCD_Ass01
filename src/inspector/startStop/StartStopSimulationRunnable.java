package inspector.startStop;

public interface StartStopSimulationRunnable {

    void play();
    void play(final int nSteps);

    void pause();
}
