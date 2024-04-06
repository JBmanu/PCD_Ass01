package car.worker;

import java.util.concurrent.CyclicBarrier;

public class SenseWorker extends BaseWorker implements CarWorker {

    public SenseWorker(final CyclicBarrier barrier) {
        super(barrier);
    }

    @Override
    public void run() {
        while (true) {
            this.startStopSimulation().pause();
            this.startStopSimulation().waitUntilRunning();
            final var queue = this.invokerCarCommandIterator();
            System.out.println("RUN SENSE CAR: " + queue.remainingCapacity());
            while (!queue.isEmpty()) {
                try {
                    queue.take().sense();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("END SENSE CAR: ");
            this.runLastWorker();
        }
    }


}
