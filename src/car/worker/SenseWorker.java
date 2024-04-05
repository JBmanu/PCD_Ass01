package car.worker;

import java.util.concurrent.CyclicBarrier;

public class SenseWorker extends BaseWorker implements CarWorker {

    public SenseWorker(final CyclicBarrier barrier) {
        super(barrier);
    }

    @Override
    public void run() {
        while (!this.isEmptyInvokerCarCommandQueue()) {
            try {
                this.takeInvokerCarCommand().sense();
                this.barrier().await();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }


}
