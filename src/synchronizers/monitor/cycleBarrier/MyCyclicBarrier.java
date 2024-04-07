package synchronizers.monitor.cycleBarrier;

public interface MyCyclicBarrier {

    void setup(final int parties);

    void awaitThatBroken();

}
