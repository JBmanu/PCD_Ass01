package car.worker;

import java.util.concurrent.CyclicBarrier;

public class DecideWorker extends BaseWorker implements CarWorker {

    public DecideWorker(final CyclicBarrier barrier) {
        super(barrier);
    }

    @Override
    public void run() {
        while (!this.isEmptyInvokerCarCommandQueue()) {
            try {
                this.takeInvokerCarCommand().decide();
                this.barrier().await();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }


}
