package car.worker;

import car.command.InvokerCommand;

import java.util.Iterator;
import java.util.concurrent.CyclicBarrier;

public class ActionWorker extends BaseWorker implements CarWorker {

    public ActionWorker(final CyclicBarrier barrier) {
        super(barrier);
    }

    @Override
    public void run() {
        while (true) {
            this.startStopSimulation().pause();
            this.startStopSimulation().waitUntilRunning();
            final var queue = this.invokerCarCommandIterator();
            System.out.println("RUN ACTION CAR: " + queue.remainingCapacity());
            while (!queue.isEmpty()) {
                try {
                    queue.take().sense();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("END ACTION CAR: ");
            this.runLastWorker();
        }
    }

}
