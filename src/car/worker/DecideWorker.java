package car.worker;

import car.command.InvokerCommand;

import java.util.Iterator;
import java.util.concurrent.CyclicBarrier;

public class DecideWorker extends BaseWorker implements CarWorker {

    public DecideWorker(final CyclicBarrier barrier) {
        super(barrier);
    }

    @Override
    public void run() {
        while (true) {
            this.startStopSimulation().pause();
            this.startStopSimulation().waitUntilRunning();
            final var queue = this.invokerCarCommandIterator();
            System.out.println("RUN DECIDE CAR: " + queue.remainingCapacity());
            while (!queue.isEmpty()) {
                try {
                    queue.take().sense();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("END DECIDE CAR: ");
            this.runLastWorker();
        }
    }


}
